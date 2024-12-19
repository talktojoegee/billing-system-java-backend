package com.billing.system.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  public WebConfig(){}
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String FRONTEND_URL = "http://localhost:4200";
    registry.addMapping("/**")
      .allowedOrigins(FRONTEND_URL)
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true);
  }

}
