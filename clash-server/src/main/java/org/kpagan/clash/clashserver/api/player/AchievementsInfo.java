package org.kpagan.clash.clashserver.api.player;

import lombok.Data;

@Data
public class AchievementsInfo {
	private String name;
	private Integer stars;
	private Integer value;
	private Integer target;
	private String info;
}
