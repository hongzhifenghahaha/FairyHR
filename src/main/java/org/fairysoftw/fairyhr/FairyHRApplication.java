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

/**
 * 网上考勤系统的引导类，基于Spring Boot，可以实现自动配置和自动组件扫描，并且有Security支持
 *
 * @version 1.0
 */
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("org.fairysoftw.fairyhr.mapper")
public class FairyHRApplication {

	/**
	 * main函数，执行应用的引导过程，创建Spring的应用上下文。
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(FairyHRApplication.class, args);
		log.info("\n----------------------------------------------------------\n\t" +
				"Application  is running! Access URLs:\n\t" +
				"Local访问网址: http://localhost:" + 8080 + "\n" +
				"----------------------------------------------------------");
	}

}
