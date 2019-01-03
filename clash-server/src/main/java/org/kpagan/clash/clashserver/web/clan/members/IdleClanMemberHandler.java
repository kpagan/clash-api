package org.kpagan.clash.clashserver.web.clan.members;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberInfo;
import org.kpagan.clash.clashserver.api.clan.members.IdleClanMemberService;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(IdleClanMemberHandler.CODE)
public class IdleClanMemberHandler implements QueryHandler {

	public static final String CODE = "idle-clan-member";

	@Autowired
	private IdleClanMemberService idleService;
	
	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		IdleClanMemberResponse response = new IdleClanMemberResponse();
		if (query.isPresent()) {
			List<ClanMemberInfo> idlePlayers = idleService.getIdlePlayers(query.get());
			response.setIdlePlayers(idlePlayers);
		}
		return response;
	}

}
