package org.kpagan.clash.clashserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClashConfig {

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}
