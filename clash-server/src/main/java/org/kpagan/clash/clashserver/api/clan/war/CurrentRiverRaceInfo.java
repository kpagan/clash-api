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
public class CurrentRiverRaceInfo {

	private String state;
	private RiverRaceClanInfo clan;
	private List<RiverRaceClanInfo> clans;
	private String collectionEndTime;
	private String warEndTime;
	private Integer sectionIndex;
}
