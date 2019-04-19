package org.kpagan.clash.clashserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClashServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClashServerApplication.class, args);
	}

}
