package org.kpagan.clash.clashserver.api.common;

import lombok.Data;

@Data
public class CardsInfo {
	private String name;
	private Integer level;
	private Integer maxLevel;
	private Integer count;
	private IconUrlInfo iconUrls;
}
