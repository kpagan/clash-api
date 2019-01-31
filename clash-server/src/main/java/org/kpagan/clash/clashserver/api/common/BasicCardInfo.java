package org.kpagan.clash.clashserver.api.common;

import lombok.Data;

@Data
public class BasicCardInfo {
	protected String name;
	private Integer id;
	protected Integer maxLevel;
	protected IconUrlInfo iconUrls;
}
