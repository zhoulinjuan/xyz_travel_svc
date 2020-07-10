package com.xyz.service.config;

import com.xyz.service.filter.ClientDataFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<ClientDataFilter> clientDataFilterRegistration() {
    FilterRegistrationBean<ClientDataFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new ClientDataFilter());
    registration.addUrlPatterns("/*");
    registration.setName("clientDataFilter");
    registration.setOrder(20);
    return registration;
  }
}
