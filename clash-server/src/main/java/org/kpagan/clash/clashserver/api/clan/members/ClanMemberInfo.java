package org.kpagan.clash.clashserver.api.clan.members;

import org.kpagan.clash.clashserver.api.common.ArenaInfo;

import lombok.Data;

@Data
public class ClanMemberInfo {
	private String tag;
	private String name;
	private String role;
	private String lastSeen;
	private Integer expLevel;
	private Integer trophies;
	private ArenaInfo arena;
	private Integer clanRank;
	private Integer previousClanRank;
	private Integer donations;
	private Integer donationsReceived;
	private Integer clanChestPoints;
}
