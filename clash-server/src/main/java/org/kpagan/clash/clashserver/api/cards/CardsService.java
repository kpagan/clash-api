package org.kpagan.clash.clashserver.api.cards;

import org.kpagan.clash.clashserver.api.BaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CardsService extends BaseService<CardListInfo> {

	protected CardsService() {
		super(CardListInfo.class);
	}

	@Cacheable(value = "cards")
	public CardListInfo getCards() {
		UriComponents u = UriComponentsBuilder.fromHttpUrl(baseUrl).pathSegment("cards").build();
		return getInfo(u.toUri());
	}
}
