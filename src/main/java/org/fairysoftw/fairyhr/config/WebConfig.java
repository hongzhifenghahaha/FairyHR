package org.fairysoftw.fairyhr.config;

import org.fairysoftw.fairyhr.interceptor.LoginInterceptor;
import org.fairysoftw.fairyhr.interceptor.ManagerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
/**
 * 应用配置类
 *
 * @version 1.0
 */
public class WebConfig implements WebMvcConfigurer{

    private final LoginInterceptor loginInterceptor;
    private final ManagerInterceptor managerInterceptor;

    /**
     * 构造函数，使用Spring实现自动装配。
     */
    @Autowired
    public WebConfig(LoginInterceptor loginInterceptor, ManagerInterceptor managerInterceptor) {
        this.loginInterceptor = loginInterceptor;
        this.managerInterceptor = managerInterceptor;
    }

    /**
     * 配置拦截器
     * @param registry Helps with configuring a list of mapped interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/assets/**");
        registry.addInterceptor(managerInterceptor)
                .addPathPatterns("/department/**")
                .excludePathPatterns("/assets/**");
    }
}
