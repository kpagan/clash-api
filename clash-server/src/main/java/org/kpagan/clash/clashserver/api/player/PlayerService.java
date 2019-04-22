package org.kpagan.clash.clashserver.api.player;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.util.ClashUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PlayerService extends BaseService<PlayerDetailsInfo> {

	protected PlayerService() {
		super(PlayerDetailsInfo.class);
	}

	@Cacheable(value = "player_details", key = "#playerTag")
	public PlayerDetailsInfo getPlayer(String playerTag) {
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("players", ClashUtils.getTag(playerTag)).build();
		return getInfo(u.toUri());
	}

}
