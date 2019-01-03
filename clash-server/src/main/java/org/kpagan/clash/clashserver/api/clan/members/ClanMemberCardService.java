package org.kpagan.clash.clashserver.api.clan.members;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public List<PlayerDetailsInfo> getMemberPlayersCards(String clanTag, String requestedCard, Integer count) {
		List<PlayerDetailsInfo> eligiblePlayers = new ArrayList<>();
		ClanMemberListInfo clanMembers = clanMemberListService.getClanMembers(clanTag);
		int totalMembers = clanMembers.getItems().size();
		int current = 0;
		for (ClanMemberInfo member : clanMembers.getItems()) {
			double progress = ((double) ++current / totalMembers) * 100;
			log.info("Looking for member {}. Progress: {}%", member.getName(), percentageFormatter.format(progress));

			if (member.getDonations() == 0) {
				PlayerDetailsInfo player = playerService.getPlayer(member.getTag());
				Optional<CardsInfo> wantedCard = player.getCards().stream()
						.filter(card -> card.getName().equals(requestedCard) && card.getCount() >= count).findFirst();
				if (wantedCard.isPresent()) {
					player.getCards().forEach(CardsInfo::correctLevels);
					eligiblePlayers.add(player);
				}
			}
		}
		return eligiblePlayers;
	}
}
