package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;

import org.kpagan.clash.clashserver.domain.ClanMember;
import org.kpagan.clash.clashserver.web.QueryResponse;

import lombok.Data;

@Data
public class ClanMemberDonationsResponse implements QueryResponse {
	private List<ClanMember> members;
}
