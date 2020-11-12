package org.kpagan.clash.clashserver.api.clan.war;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.util.ClashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CurrentRiverRaceService extends BaseService<CurrentRiverRaceInfo> {

	@Autowired
	private ExecutorService executor;

	protected CurrentRiverRaceService() {
		super(CurrentRiverRaceInfo.class);
	}
	
	@Cacheable(value = "currentriverrace", key = "#clanTag")
	public CurrentRiverRaceInfo getWarlog(String clanTag) {
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("clans", ClashUtils.getTag(clanTag), "currentriverrace").build();
		return getInfo(u.toUri());
	}

	@Cacheable(value = "currentriverrace", key = "#clanTag")
	public Future<CurrentRiverRaceInfo> getWarlogAsync(String clanTag) {
		Future<CurrentRiverRaceInfo> future = executor.submit(() -> {
			log.info("Looking for current river race clan [{}]", clanTag);
			return getWarlog(clanTag);
		});
		return future;
	}

}
