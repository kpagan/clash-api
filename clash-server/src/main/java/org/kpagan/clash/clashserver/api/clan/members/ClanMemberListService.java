package org.kpagan.clash.clashserver.api.clan.members;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.kpagan.clash.clashserver.api.BaseService;
import org.kpagan.clash.clashserver.api.player.PlayerDetailsInfo;
import org.kpagan.clash.clashserver.api.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClanMemberListService extends BaseService<ClanMemberListInfo> {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private ExecutorService executor;

	protected ClanMemberListService() {
		super(ClanMemberListInfo.class);
	}

	public ClanMemberListInfo getClanMembers(String clanTag) {
		String tag = clanTag.trim().replaceAll("#", "");
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("clans", "#".concat(tag), "members").build();
		return getInfo(u.toUri());
	}
	
	public List<Future<PlayerDetailsInfo>> getClanMembersAsync(String clanTag) {
		ClanMemberListInfo clanMembers = getClanMembers(clanTag);
		List<Future<PlayerDetailsInfo>> futures = new ArrayList<>();
		
		for (ClanMemberInfo member : clanMembers.getItems()) {
			Future<PlayerDetailsInfo> future = executor.submit(() -> {
				log.info("Looking for member {}", member.getName());
				return playerService.getPlayer(member.getTag());
			});
			futures.add(future);
		}
		return futures;
	}
	
}
