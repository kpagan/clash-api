package org.kpagan.clash.clashserver.web.cards;

import java.util.Map;
import java.util.Optional;

import org.kpagan.clash.clashserver.api.cards.CardListInfo;
import org.kpagan.clash.clashserver.api.cards.CardsService;
import org.kpagan.clash.clashserver.web.QueryHandler;
import org.kpagan.clash.clashserver.web.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(GameCardsHandler.CODE)
public class GameCardsHandler implements QueryHandler {

	public static final String CODE = "cards";

	@Autowired
	private CardsService cardService;

	@Override
	public QueryResponse handle(Optional<String> query, Map<String, String> params) {
		GameCardsResponse response = new GameCardsResponse();
		CardListInfo cards = cardService.getCards();
		response.setInfo(cards);
		return response;
	}
}
