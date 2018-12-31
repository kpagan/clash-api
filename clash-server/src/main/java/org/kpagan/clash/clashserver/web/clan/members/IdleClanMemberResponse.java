package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberInfo;
import org.kpagan.clash.clashserver.web.QueryResponse;

import lombok.Data;

@Data
public class IdleClanMemberResponse implements QueryResponse {
	private List<ClanMemberInfo> idlePlayers;
}
