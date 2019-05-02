package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.kpagan.clash.clashserver.api.clan.war.ClanWarlogService;
import org.kpagan.clash.clashserver.api.clan.war.WarlogInfo;
import org.kpagan.clash.clashserver.domain.ClanMember;
import org.kpagan.clash.clashserver.domain.ClanMemberRepository;
import org.kpagan.clash.clashserver.util.ClashUtils;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component(ClanMemberDonationsHandler.CODE)
@Log4j2
public class ClanMemberDonationsHandler implements QueryHandler {

	public static final String CODE = "clan-member-donations";

	@Autowired
	private ClanMemberRepository repo;

	@Autowired
	private ClanWarlogService warlogService;
	
	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		ClanMemberDonationsResponse response = new ClanMemberDonationsResponse();

		if (query.isPresent()) {
			Future<WarlogInfo> warlogAsync = warlogService.getWarlogAsync(query.get());
			List<ClanMember> members = repo.findByClanTag(ClashUtils.getTag(query.get()));
			Map<String, ClanMemberInfo> collect = members.stream().map(m -> {
				ClanMemberInfo info = new ClanMemberInfo();
				BeanUtils.copyProperties(m, info);
				return info;
			}).collect(Collectors.toMap(ClanMemberInfo::getTag, v -> v));
			try {
				WarlogInfo warlogInfo = warlogAsync.get();
				warlogInfo.getItems().stream().flatMap(warinfo -> warinfo.getParticipants().stream()).forEach(participant -> {
					ClanMemberInfo memberInfo = collect.get(participant.getTag());
					if (memberInfo != null) {
								Integer totalWarDayWins = memberInfo.getTotalWarDayWins() == null
										? participant.getWins()
										: memberInfo.getTotalWarDayWins() + participant.getWins();
						memberInfo.setTotalWarDayWins(totalWarDayWins);
					}
				});
			} catch (Exception e) {
				log.error("Error while getting warlog for clan {}", query.get(), e);
			}
			
			response.setMembers(collect.values().stream().collect(Collectors.toList()));
		}
		return response;
	}
}
