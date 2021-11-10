package org.fairysoftw.fairyhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FairyHRApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairyHRApplication.class, args);
	}

}
