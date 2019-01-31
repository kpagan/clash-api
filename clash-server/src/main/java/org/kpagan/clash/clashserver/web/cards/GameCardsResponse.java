package org.kpagan.clash.clashserver.web.cards;

import org.kpagan.clash.clashserver.api.cards.CardListInfo;
import org.kpagan.clash.clashserver.web.QueryResponse;

import lombok.Data;

@Data
public class GameCardsResponse implements QueryResponse {
	private CardListInfo info;
}
