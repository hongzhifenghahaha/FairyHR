package org.fairysoftw.fairyhr.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis的配置类
 *
 * @version 1.0
 */
@Configuration
@EntityScan("org.fairysoftw.fairyhr.mapper")
public class MyBatisConfig {
}
