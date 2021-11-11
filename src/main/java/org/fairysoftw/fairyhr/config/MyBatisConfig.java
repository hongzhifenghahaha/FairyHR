package org.fairysoftw.fairyhr.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("org.fairysoftw.fairyhr.mapper")
public class MyBatisConfig {
}
