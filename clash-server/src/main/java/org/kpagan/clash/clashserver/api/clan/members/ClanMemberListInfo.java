package org.kpagan.clash.clashserver.api.clan.members;

import java.util.List;

import lombok.Data;

@Data
public class ClanMemberListInfo {
	private List<ClanMemberInfo> items;
}
