package org.kpagan.clash.clashserver.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerWrapperInfo<T> {
	private String name;
	private String tag;
	private T model;
}
