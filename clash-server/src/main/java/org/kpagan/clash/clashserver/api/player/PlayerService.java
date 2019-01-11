package org.kpagan.clash.clashserver.api.player;

import java.time.LocalDateTime;

import org.kpagan.clash.clashserver.api.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PlayerService extends BaseService<PlayerDetailsInfo> {

	@Autowired
	private PlayerRepository repository;
	
	protected PlayerService() {
		super(PlayerDetailsInfo.class);
	}

	public PlayerDetailsInfo getPlayer(String playerTag) {
		String tag = playerTag.replaceAll("#", "");
		PlayerDetailsInfo playerDetailsInfo = repository.findByTagAndTimestampAfter(playerTag, LocalDateTime.now().minusDays(1));
		if (playerDetailsInfo != null) {
			return playerDetailsInfo;
		} else {
			UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("players", "#".concat(tag)).build();
			playerDetailsInfo = getInfo(u.toUri());
			playerDetailsInfo.setTimestamp(LocalDateTime.now());
			repository.save(playerDetailsInfo);
			return playerDetailsInfo;
		}
	}

}
