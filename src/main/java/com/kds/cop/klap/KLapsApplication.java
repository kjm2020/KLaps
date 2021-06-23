
package com.kds.cop.klap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kds.cop.klap"})
public class KLapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KLapsApplication.class, args);
	}

}
