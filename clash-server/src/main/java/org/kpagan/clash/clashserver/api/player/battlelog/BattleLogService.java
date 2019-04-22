package org.kpagan.clash.clashserver.api.player.battlelog;

import java.util.List;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.util.ClashUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BattleLogService extends BaseService<BattleLogInfo> {

	protected BattleLogService() {
		super(BattleLogInfo.class);
	}

	@Cacheable(value = "player_battlelog", key = "#playerTag")
	public List<BattleLogInfo> getBattleLog(String playerTag) {
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("players", ClashUtils.getTag(playerTag), "battlelog").build();
		return getInfoList(u.toUri());
	}

	@Override
	protected ParameterizedTypeReference<List<BattleLogInfo>> getListType() {
		return new ParameterizedTypeReference<List<BattleLogInfo>>(){};
	}
}
