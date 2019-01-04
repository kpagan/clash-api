package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberCardService;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(ClanMemberCardHandler.CODE)
public class ClanMemberCardHandler implements QueryHandler {

	public static final String CODE = "clan-member-cards";
	
	private static final String WANTED_CARD_NAME = "card";

	private static final String WANTED_CARD_COUNT = "no";

	@Autowired
	private ClanMemberCardService cardService;
	
	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		ClanMemberCardResponse response = new ClanMemberCardResponse();

		if (query.isPresent() && params.containsKey(WANTED_CARD_NAME) && params.containsKey(WANTED_CARD_COUNT)) {
			String requestedCard = params.get(WANTED_CARD_NAME);
			Integer count = Integer.parseInt(params.get(WANTED_CARD_COUNT));
			List<PlayerDetailsInfo> players = cardService.cleanUp(cardService.getMemberPlayersCards(query.get(), requestedCard, count));
			response.setPlayers(players);
		}
		return response;
	}

}
