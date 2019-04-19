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
	
	@Column(name = "last_day_donations")
	private Integer lastDayDonations;
	
	@Column(name = "last_day_donations_rcv")
	private Integer lastDayDonationsReceived;
	
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
	
}
