package org.kpagan.clash.clashserver;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class ClashConfig {

	@Value("${FIXIE_URL:#{null}}")
	private String fixieUrl;
	
	@Bean
	public RestTemplate createRestTemplate() throws Exception {
		if (fixieUrl != null) {
	        String[] fixieValues = fixieUrl.split("[/(:\\/@)/]+");
	        String fixieUser = fixieValues[1];
	        String fixiePassword = fixieValues[2];
	        String fixieHost = fixieValues[3];
	        int fixiePort = Integer.parseInt(fixieValues[4]);
	        
	        CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials( 
	                new AuthScope(fixieHost, fixiePort), 
	                new UsernamePasswordCredentials(fixieUser, fixiePassword));

	        HttpHost myProxy = new HttpHost(fixieHost, fixiePort);
	        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

	        clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).disableCookieManagement();

	        HttpClient httpClient = clientBuilder.build();
	        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	        factory.setHttpClient(httpClient);

	        return new RestTemplate(factory);
		} else {
			return new RestTemplate();
		}
    }
	
}
