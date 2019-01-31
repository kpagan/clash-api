package org.kpagan.clash.clashserver.api.cards;

import java.util.List;

import org.kpagan.clash.clashserver.api.common.BasicCardInfo;

import lombok.Data;

@Data
public class CardListInfo {
	List<BasicCardInfo> items;
}
