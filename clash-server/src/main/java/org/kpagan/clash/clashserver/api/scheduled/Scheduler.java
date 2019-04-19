package org.kpagan.clash.clashserver.api.scheduled;

import org.kpagan.clash.clashserver.api.clan.members.ClanMemberDonationsService;
import org.kpagan.clash.clashserver.config.ClashConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	// will run at 23:55 every day
	@Scheduled(cron= "0 55 23 * * *", zone = ClashConfig.TIMEZONE_ID)
    public void updateClanMemberDonations() {
		log.info("Updating clan member donation statistics");
		donationService.getClanMemberDetails(clanTag);
		log.info("Clan member donation statistics finished");
    }
}
