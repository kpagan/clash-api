package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.kpagan.clash.clashserver.domain.ClanMember;
import org.kpagan.clash.clashserver.domain.ClanMemberRepository;
import org.kpagan.clash.clashserver.util.ClashUtils;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(ClanMemberDonationsHandler.CODE)
public class ClanMemberDonationsHandler implements QueryHandler {

	public static final String CODE = "clan-member-donations";

	@Autowired
	private ClanMemberRepository repo;

	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		ClanMemberDonationsResponse response = new ClanMemberDonationsResponse();

		if (query.isPresent()) {
			List<ClanMember> members = repo.findByClanTag(ClashUtils.getTag(query.get()));
			List<ClanMemberInfo> collect = members.stream().map(m -> {
				ClanMemberInfo info = new ClanMemberInfo();
				BeanUtils.copyProperties(m, info);
				return info;
			}).collect(Collectors.toList());
			response.setMembers(collect);
		}
		return response;
	}
}
