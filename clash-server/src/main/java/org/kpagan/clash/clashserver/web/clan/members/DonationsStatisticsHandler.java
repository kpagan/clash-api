package org.kpagan.clash.clashserver.web.clan.members;

import java.util.Map;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberDonationsService;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/** used only for debugging reasons to invoke manually the donations statistics service */
@Component(DonationsStatisticsHandler.CODE)
public class DonationsStatisticsHandler implements QueryHandler {

	public static final String CODE = "donations-statistics";

	@Autowired
	private ClanMemberDonationsService donationService;
	
	@Autowired
	private CacheManager cacheManager;

	
	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		ClanMemberDonationsResponse response = new ClanMemberDonationsResponse();
		if (query.isPresent()) {
			cacheManager.getCache("player_details").clear();
			donationService.updateClanMemberDonations(query.get());
		}
		return response;
	}
}
