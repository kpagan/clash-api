/**
 * 
 */
package org.kpagan.clash.clashserver.api.clan.war;

import java.util.List;

import lombok.Data;

/**
 * @author paganelis
 *
 */
@Data
public class RiverRaceClanInfo {

	private Integer badgeId;
	private String tag;
	private Integer clanScore;
	private String name;
	private Integer fame;
	private Integer repairPoints;
	private String finishTime;
	private List<RiverRaceParticipantInfo> participants;
}
