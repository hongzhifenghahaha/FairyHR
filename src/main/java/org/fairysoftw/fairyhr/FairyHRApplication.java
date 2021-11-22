package org.fairysoftw.fairyhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("org.fairysoftw.fairyhr.mapper")
public class FairyHRApplication {

    public static void main(String[] args) {
        SpringApplication.run(FairyHRApplication.class, args);
    }

}
