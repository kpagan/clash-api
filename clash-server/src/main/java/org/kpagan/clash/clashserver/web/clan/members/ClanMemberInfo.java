/**
 * 
 */
package org.kpagan.clash.clashserver.web.clan.members;

import java.time.LocalDate;

import lombok.Data;


/**
 * @author paganelis
 *
 */
@Data
public class ClanMemberInfo {

	private String tag;
	private String name;
	private String clanTag;
	private LocalDate memberSince;
	private LocalDate leftClan;
	private Integer donatedFromJoinDay;
	private Integer receivedFromJoinDay;
	private Integer averageWeeklyDonations;
	private Integer weekDonationsSoFar;
	private Integer weekDonationsReceivedSoFar;
	private Integer timesRejoined;
	private String remarks;
	private Integer totalWarDayWins;
	
	public Double getDonatedReceivedFromJoinDayRatio() {
		return receivedFromJoinDay == 0 && donatedFromJoinDay == 0 ? 0 : donatedFromJoinDay == 0 ? Double.MAX_VALUE : (double) receivedFromJoinDay / donatedFromJoinDay;
	}

	public Double getDonatedReceivedSoFarRatio() {
		return weekDonationsReceivedSoFar == 0 && weekDonationsSoFar == 0 ? 0 : weekDonationsSoFar == 0 ? Double.MAX_VALUE : (double) weekDonationsReceivedSoFar / weekDonationsSoFar;
	}
	
}
