package org.fairysoftw.fairyhr;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("org.fairysoftw.fairyhr.mapper")
public class FairyHRApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(FairyHRApplication.class, args);
		log.info("\n----------------------------------------------------------\n\t" +
				"Application  is running! Access URLs:\n\t" +
				"Local访问网址: http://localhost:" + 8080 + "\n" +
				"----------------------------------------------------------");
	}

}
