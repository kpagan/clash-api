package org.kpagan.clash.clashserver.api.clan.members;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.kpagan.clash.clashserver.api.common.CardsInfo;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.api.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClanMemberCardService {

	private static final DecimalFormat percentageFormatter = new DecimalFormat("#");

	@Autowired
	private ClanMemberListService clanMemberListService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private ExecutorService executor;

	public List<PlayerDetailsInfo> getMemberPlayersCards(String clanTag, String requestedCard, Integer count) {
		List<PlayerDetailsInfo> eligiblePlayers = new ArrayList<>();
		ClanMemberListInfo clanMembers = clanMemberListService.getClanMembers(clanTag);
		List<Future<PlayerDetailsInfo>> futures = new ArrayList<>();
		
		for (ClanMemberInfo member : clanMembers.getItems()) {
			Future<PlayerDetailsInfo> future = executor.submit(() -> {
				log.info("Looking for member {}", member.getName());
				return playerService.getPlayer(member.getTag());
			});
			futures.add(future);
		}

		int totalMembers = futures.size();
		int current = 0;
		
		for (Future<PlayerDetailsInfo> future : futures) {
			double progress = ((double) ++current / totalMembers) * 100;
			try {
				PlayerDetailsInfo player = future.get();
				log.info("Got response for member {}. Progress: {}%", player.getName(), percentageFormatter.format(progress));
				Optional<CardsInfo> wantedCard = player.getCards().stream()
						.filter(card -> card.getName().equals(requestedCard) && card.getCount() >= count).findFirst();
				if (wantedCard.isPresent()) {
					eligiblePlayers.add(player);
				}
			} catch (Exception e) {
				log.error("Error while getting details info for player", e);
			}
			
		}
		return eligiblePlayers;
	}

	/**
	 * Keeps only the data that is interesting for the "Trade Cards" functionality<br/>
	 * the properties kept are:
	 * <ul>
	 * 	<li>tag</tag>
	 * 	<li>name</li>
	 * 	<li>currentDeck</li>
	 * 	<li>currentFavouriteCard</li>
	 * </ul>
	 * @param players
	 * @return
	 */
	public List<PlayerDetailsInfo> cleanUp(List<PlayerDetailsInfo> players) {
		List<PlayerDetailsInfo> cleaned = new ArrayList<>();
		for (PlayerDetailsInfo player : players) {
			PlayerDetailsInfo clean = new PlayerDetailsInfo();
			clean.setTag(player.getTag());
			clean.setName(player.getName());
			clean.setCurrentDeck(player.getCurrentDeck());
			clean.getCurrentDeck().forEach(CardsInfo::correctLevels);
			clean.setCurrentFavouriteCard(player.getCurrentFavouriteCard());
			cleaned.add(clean);
		}
		return cleaned;
	}
}
