package org.kpagan.clash.clashserver.api.clan.members;

import org.kpagan.clash.clashserver.api.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClanMemberListService extends BaseService<ClanMemberListInfo> {

	protected ClanMemberListService() {
		super(ClanMemberListInfo.class);
	}

	public ClanMemberListInfo getClanMembers(String clanTag) {
		String tag = clanTag.replaceAll("#", "");
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("clans", "#".concat(tag), "members").build();
		return getInfo(u.toUri());
	}
}
