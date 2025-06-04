// src/main/java/com/homework3/config/CorsConfig.java
package com.homework3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")                 // 放行全部路径
        .allowedOriginPatterns("*")
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(false)           // 若需要带 Cookie 改为 true
        .maxAge(3600);
  }
}
