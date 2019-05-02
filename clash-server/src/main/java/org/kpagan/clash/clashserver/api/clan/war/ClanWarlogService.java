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
public class ClanWarlogService extends BaseService<WarlogInfo> {

	@Autowired
	private ExecutorService executor;

	protected ClanWarlogService() {
		super(WarlogInfo.class);
	}
	
	@Cacheable(value = "clan_warlog", key = "#clanTag")
	public WarlogInfo getWarlog(String clanTag) {
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("clans", ClashUtils.getTag(clanTag), "warlog").build();
		return getInfo(u.toUri());
	}
	
	public Future<WarlogInfo> getWarlogAsync(String clanTag) {
		Future<WarlogInfo> future = executor.submit(() -> {
			log.info("Looking for warlog for clan {}", clanTag);
			return getWarlog(clanTag);
		});
		return future;
	}

}
