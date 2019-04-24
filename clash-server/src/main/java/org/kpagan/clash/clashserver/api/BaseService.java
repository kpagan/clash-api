package org.kpagan.clash.clashserver.api;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

/**
 * Base service class 
 *
 * @param <T> the type of the response DTO
 */
@Log4j2
public abstract class BaseService<T> {
	
	public static final DecimalFormat percentageFormatter = new DecimalFormat("#");
	
	private final Class<T> infoClass;
	
	protected BaseService(Class<T> infoClass) {
		this.infoClass = infoClass;
	}

	@Value(value = "${clash.api.base.url}")
	protected String baseUrl;

	@Value(value = "${clash.api.token}")
	protected String applicationToken;

	@Autowired
	protected RestTemplate template;
	
	protected T getInfo(URI uri) {
		HttpEntity<?> requestEntity = getHeaders();
		log.info("Establishing connection to {}", uri.toString());
		ResponseEntity<T> exchange = template.exchange(uri, HttpMethod.GET, requestEntity, infoClass);
		return exchange.getBody();
	}

	protected List<T> getInfoList(URI uri) {
		HttpEntity<?> requestEntity = getHeaders();
		log.info("Establishing connection to {}", uri.toString());
		ResponseEntity<List<T>> exchange = template.exchange(uri, HttpMethod.GET, requestEntity, getListType());
		return exchange.getBody();
	}

	private HttpEntity<?> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("authorization", "Bearer ".concat(applicationToken));
		return new HttpEntity<>(headers);
	}
	
	protected ParameterizedTypeReference<List<T>> getListType() {
		return new ParameterizedTypeReference<List<T>>(){};
	}
}
