package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;
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
	
	@Autowired
	private ClanMemberCardService carService;
	
	@Override
	public QueryResponse handle(Optional<String> query) {
		ClanMemberCardResponse response = new ClanMemberCardResponse();
		if (query.isPresent()) {
			List<PlayerDetailsInfo> players = carService.getMemberPlayersCards(query.get());
			response.setPlayers(players);
		}
		return response;
	}

}
