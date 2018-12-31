package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;

import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.web.QueryResponse;

import lombok.Data;

@Data
public class ClanMemberCardResponse implements QueryResponse {
	private List<PlayerDetailsInfo> players;
}
