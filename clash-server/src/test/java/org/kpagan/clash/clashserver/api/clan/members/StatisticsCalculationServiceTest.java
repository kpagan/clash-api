package org.kpagan.clash.clashserver.api.clan.members;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.FileReader;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.kpagan.clash.clashserver.api.common.ClanBaseInfo;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.config.ClashConfig;
import org.kpagan.clash.clashserver.domain.ClanMember;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class StatisticsCalculationServiceTest {

	private static final String CLAN_PLAYERS_CSV = "src/test/resources/clan_members.csv";
	private static final String EXPECTED_DATABASE_PLAYERS_CSV = "src/test/resources/expected_database_players.csv";
	private static final String DATABASE_PLAYERS_CSV = "src/test/resources/database_players.csv";
	private static final int threads = 20;

	private static Map<String, ClanMember> databasePlayers;
	private static Map<String, ClanMember> expectedDatabasePlayers;
	private static Map<String, PlayerDetailsInfo> clanMembers;

//	@Mock(answer=Answers.CALLS_REAL_METHODS)
	private StatisticsCalculationService service;
	
	
	private ExecutorService executor;
	
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	public enum DatabasePlayersHeaders {
		tag, name, clan_tag, member_since, left_clan, donated_from_join, received_from_join, average_week_donations, week_donations_so_far, week_donations_rcv_so_far, rejoined_times, remarks;
	}

	public enum ClanMembersHeaders {
		tag, name, role, lastSeen, expLevel, trophies, arena_id, arena_name, clanRank, previousClanRank, donations, donationsReceived, clanChestPoints;
	}

	@BeforeClass
	public static void beforeClass() {
		databasePlayers = mapDatabasePlayers(loadCsv(DATABASE_PLAYERS_CSV, DatabasePlayersHeaders.class)).stream()
				.collect(Collectors.toMap(e -> e.getTag(), e -> e));
		expectedDatabasePlayers = mapDatabasePlayers(loadCsv(EXPECTED_DATABASE_PLAYERS_CSV, DatabasePlayersHeaders.class)).stream()
				.collect(Collectors.toMap(e -> e.getTag(), e -> e));
		clanMembers = mapClanMembers(loadCsv(CLAN_PLAYERS_CSV, ClanMembersHeaders.class)).stream()
				.collect(Collectors.toMap(e -> e.getTag(), e -> e));
		
	}

	@Before
	public void setup() {
		service = new StatisticsCalculationService();
//		when(service.isWeekReset()).thenReturn(true);
		service.setClock(Clock.fixed(Instant.parse("2019-05-05T21:00:00.01Z"), ClashConfig.ATHENS));
		executor = Executors.newFixedThreadPool(threads);
	}

	@Test
	public void testCalculate() {
		Collection<Future<ClanMember>> futures = new ArrayList<>(clanMembers.values().size());
		CountDownLatch latch = new CountDownLatch(1);

		for (PlayerDetailsInfo player : clanMembers.values()) {
			Optional<ClanMember> member = Optional.ofNullable(databasePlayers.get(player.getTag()));

			futures.add(executor.submit(() -> {
				latch.await();
				return service.calculate(player, member);
			}));
		}
		
		latch.countDown();

		Set<ClanMember> members = new HashSet<>();
		Set<String> membersTags = new HashSet<>();
		for (Future<ClanMember> f : futures) {
			try {
				ClanMember member = f.get();
				members.add(member);
				membersTags.add(member.getTag());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		List<ClanMember> leftMembers = new ArrayList<>(databasePlayers.values());
		leftMembers.removeIf(member -> membersTags.contains(member.getTag()));
		service.calculateLeftMembersStats(leftMembers);
		members.addAll(leftMembers);
		
		for (ClanMember member : members) {
			ClanMember expectedMember = expectedDatabasePlayers.get(member.getTag());
			assertReflectionEquals(expectedMember, member);
		}
		
	}

	private static List<CSVRecord> loadCsv(String filename, Class<? extends Enum<?>> headers) {
		try (FileReader reader = new FileReader(filename);
				CSVParser csv = new CSVParser(reader,
						CSVFormat.EXCEL.withHeader(headers).withDelimiter(',').withFirstRecordAsHeader())) {
			return csv.getRecords();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static List<ClanMember> mapDatabasePlayers(List<CSVRecord> records) {
		return records.stream().map(record -> {
			ClanMember member = new ClanMember();
			member.setTag(record.get(DatabasePlayersHeaders.tag));
			member.setName(record.get(DatabasePlayersHeaders.name));
			member.setClanTag(record.get(DatabasePlayersHeaders.clan_tag));
			member.setMemberSince(parseDate(record.get(DatabasePlayersHeaders.member_since)));
			member.setLeftClan(parseDate(record.get(DatabasePlayersHeaders.left_clan)));
			member.setDonatedFromJoinDay(parseInt(record.get(DatabasePlayersHeaders.donated_from_join)));
			member.setReceivedFromJoinDay(parseInt(record.get(DatabasePlayersHeaders.received_from_join)));
			member.setAverageWeeklyDonations(parseInt(record.get(DatabasePlayersHeaders.average_week_donations)));
			member.setWeekDonationsSoFar(parseInt(record.get(DatabasePlayersHeaders.week_donations_so_far)));
			member.setWeekDonationsReceivedSoFar(
					parseInt(record.get(DatabasePlayersHeaders.week_donations_rcv_so_far)));
			member.setTimesRejoined(parseInt(record.get(DatabasePlayersHeaders.rejoined_times)));
			return member;
		}).collect(Collectors.toList());
	}

	private static List<PlayerDetailsInfo> mapClanMembers(List<CSVRecord> records) {
		return records.stream().map(record -> {
			PlayerDetailsInfo player = new PlayerDetailsInfo();
			player.setTag(record.get(ClanMembersHeaders.tag));
			player.setName(record.get(ClanMembersHeaders.name));
			player.setRole(record.get(ClanMembersHeaders.role));
			player.setExpLevel(parseInt(record.get(ClanMembersHeaders.expLevel)));
			player.setTrophies(parseInt(record.get(ClanMembersHeaders.trophies)));
			player.setDonations(parseInt(record.get(ClanMembersHeaders.donations)));
			player.setDonationsReceived(parseInt(record.get(ClanMembersHeaders.donationsReceived)));
			ClanBaseInfo clan = new ClanBaseInfo();
			clan.setTag("#P9R9282L");
			player.setClan(clan);
			return player;
		}).collect(Collectors.toList());
	}

	private static Integer parseInt(String s) {
		if (s == null || s.trim().isEmpty()) {
			return null;
		}
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Invalid input %s", s), e);
		}
	}

	private static LocalDate parseDate(String s) {
		if (s == null || s.trim().isEmpty()) {
			return null;
		}
		try {
			return LocalDate.parse(s);
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Invalid input %s", s), e);
		}
	}
}
