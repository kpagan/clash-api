package org.kpagan.clash.clashserver.api.clan.war;

import lombok.Data;

@Data
public class WarParticipantInfo {
	private String tag;
	private String name;
	private Integer cardsEarned;
	private Integer battlesPlayed;
	private Integer wins;
	private Integer collectionDayBattlesPlayed;
}
