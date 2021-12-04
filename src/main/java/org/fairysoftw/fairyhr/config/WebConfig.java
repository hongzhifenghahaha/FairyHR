package org.fairysoftw.fairyhr.config;

import org.fairysoftw.fairyhr.interceptor.LoginInterceptor;
import org.fairysoftw.fairyhr.interceptor.ManagerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    private final LoginInterceptor loginInterceptor;
    private final ManagerInterceptor managerInterceptor;

    @Autowired
    public WebConfig(LoginInterceptor loginInterceptor, ManagerInterceptor managerInterceptor) {
        this.loginInterceptor = loginInterceptor;
        this.managerInterceptor = managerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        registry.addInterceptor(managerInterceptor).addPathPatterns("/department/**");
    }
}
