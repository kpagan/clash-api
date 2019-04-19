package org.kpagan.clash.clashserver.web.clan.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberDonationsService;
import org.kpagan.clash.clashserver.domain.ClanMember;
import org.kpagan.clash.clashserver.domain.ClanMemberRepository;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(ClanMemberDonationsHandler.CODE)
public class ClanMemberDonationsHandler implements QueryHandler {

	public static final String CODE = "clan-member-donations";
	
	@Autowired
	private ClanMemberDonationsService donationService;
	
	@Autowired
	private ClanMemberRepository repo;
	
	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		ClanMemberDonationsResponse response = new ClanMemberDonationsResponse();
		
		if (query.isPresent()) {
			donationService.getClanMemberDetails(query.get());
			List<ClanMember> members = repo.findAll();
			response.setMembers(detach(members));
		}
		return response;
	}
	
	private List<ClanMember> detach(List<ClanMember> attached) {
		List<ClanMember> detached = new ArrayList<>();
		for (ClanMember a : attached) {
			ClanMember d = new ClanMember();
			BeanUtils.copyProperties(a, d);
			detached.add(d);
		}
		return detached;
	}
}
