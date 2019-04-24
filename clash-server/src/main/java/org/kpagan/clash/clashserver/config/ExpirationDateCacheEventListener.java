package org.kpagan.clash.clashserver.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * This implementation of {@link CacheEventListener} sets the expiration time
 * until midnight in order to evict elements in cache so the
 * 
 * @author paganelis
 *
 */
public class ExpirationDateCacheEventListener implements CacheEventListener {

	@Override
	public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {

	}

	@Override
	public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
		handleExpiration(cache, element);
	}

	@Override
	public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
		handleExpiration(cache, element);
	}

	private void handleExpiration(Ehcache cache, Element element) {
		ZonedDateTime expirationDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(element.getExpirationTime()), ClashConfig.ATHENS);
		LocalDate today = LocalDate.now(ClashConfig.ATHENS);
		LocalTime midnight = LocalTime.MIDNIGHT;
		ZonedDateTime todayMidnight = ZonedDateTime.of(today, midnight, ClashConfig.ATHENS);

		if (expirationDateTime.isAfter(todayMidnight)) {
			element.setTimeToLive(secondsBetween(ZonedDateTime.now(ClashConfig.ATHENS), todayMidnight));
		}
	}

	private int secondsBetween(ZonedDateTime a, ZonedDateTime b) {
		return Math.abs((int) (a.toEpochSecond() - b.toEpochSecond()));
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public void notifyElementExpired(Ehcache cache, Element element) {

	}

	@Override
	public void notifyElementEvicted(Ehcache cache, Element element) {

	}

	@Override
	public void notifyRemoveAll(Ehcache cache) {

	}

	@Override
	public void dispose() {

	}

}
