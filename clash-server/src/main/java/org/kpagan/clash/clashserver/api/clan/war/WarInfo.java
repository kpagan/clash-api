package org.kpagan.clash.clashserver.api.clan.war;

import java.util.List;

import lombok.Data;

@Data
public class WarInfo {
	private Integer seasonId;
	private String createdDate;
	private List<WarParticipantInfo> participants;
}
