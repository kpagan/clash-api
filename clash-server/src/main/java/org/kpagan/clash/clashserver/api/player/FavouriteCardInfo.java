package org.kpagan.clash.clashserver.api.player;

import org.kpagan.clash.clashserver.api.common.IconUrlInfo;

import lombok.Data;

@Data
public class FavouriteCardInfo {
	private String name;
	private Integer id;
	private Integer maxLevel;
	private IconUrlInfo iconUrls;
}
