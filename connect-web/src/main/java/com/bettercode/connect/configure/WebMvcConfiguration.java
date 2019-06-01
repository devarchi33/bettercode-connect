package com.bettercode.connect.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private String[] allowOrigins;

    private String[] allowMethods;

    @Autowired
    @Qualifier("getAllowOrigins")
    public void setAllowOrigins(String[] allowOrigins) {
        this.allowOrigins = allowOrigins;
    }

    @Autowired
    @Qualifier("getAllowMethods")
    public void setAllowMethods(String[] allowMethods) {
        this.allowMethods = allowMethods;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowOrigins)
                .allowedMethods(allowMethods);
    }
}