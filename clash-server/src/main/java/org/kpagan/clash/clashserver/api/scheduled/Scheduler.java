package org.kpagan.clash.clashserver.api.scheduled;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberDonationsService;
import org.kpagan.clash.clashserver.config.ClashConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Scheduler {
	
	@Value(value = "${clash.api.clanTag}")
	private String clanTag;
	
	@Autowired
	private ClanMemberDonationsService donationService;
	
	@Autowired
	private CacheManager cacheManager;
	
	// will run every hour
	@Scheduled(cron= "0 0 * * * *", zone = ClashConfig.TIMEZONE_ID)
    public void updateClanMemberDonations() {
		log.info("Updating clan member donation statistics");
		// clear the caches prior getting statistics in order to get fresh data
		cacheManager.getCache("player_details").clear();
		donationService.getClanMemberDonations(clanTag);
		log.info("Clan member donation statistics finished");
    }
	
}
