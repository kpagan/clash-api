/**
 * 
 */
package org.kpagan.clash.clashserver.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

/**
 * @author paganelis
 *
 */
@Entity
@Table(name = "clan_member")
@Getter @Setter
public class ClanMember {

	@Id
	@NaturalId
	private String tag;

	private String name;
	
	@Column(name = "clan_tag")
	private String clanTag;

	@Column(name = "member_since")
	private LocalDate memberSince;

	@Column(name = "left_clan")
	private LocalDate leftClan;

	@Column(name = "donated_from_join")
	private Integer donatedFromJoinDay;
	
	@Column(name = "received_from_join")
	private Integer receivedFromJoinDay;

	@Column(name = "average_week_donations")
	private Integer averageWeeklyDonations;
	
	@Column(name = "average_week_donations_count")
	private Integer averageWeeklyDonationsCount;
	
	@Column(name = "week_donations_so_far")
	private Integer weekDonationsSoFar;
	
	@Column(name = "week_donations_rcv_so_far")
	private Integer weekDonationsReceivedSoFar;
	
	@Column(name = "rejoined_times")
	private Integer timesRejoined;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClanMember)) return false;
        ClanMember member = (ClanMember) o;
        return Objects.equals(getTag(), member.getTag());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTag());
    }
	
    public void increaseTimesRejoined() {
    	if (timesRejoined == null) {
    		timesRejoined = 0;
    	}
    	++timesRejoined;
    }
}
