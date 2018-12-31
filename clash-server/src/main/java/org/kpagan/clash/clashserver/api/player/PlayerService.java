package org.kpagan.clash.clashserver.api.player;

import org.kpagan.clash.clashserver.api.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PlayerService extends BaseService<PlayerDetailsInfo> {

	protected PlayerService() {
		super(PlayerDetailsInfo.class);
	}

	public PlayerDetailsInfo getPlayer(String playerTag) {
		String tag = playerTag.replaceAll("#", "");
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("players", "#".concat(tag)).build();
		return getInfo(u.toUri());
	}

}
