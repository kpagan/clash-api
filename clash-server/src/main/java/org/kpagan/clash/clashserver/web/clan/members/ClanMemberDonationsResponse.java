package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;

import org.kpagan.clash.clashserver.web.QueryResponse;

import lombok.Data;

@Data
public class ClanMemberDonationsResponse implements QueryResponse {
	private List<ClanMemberInfo> members;
}
