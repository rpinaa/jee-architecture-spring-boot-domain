package org.example.seed.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;

/**
 * Created by Ricardo Pina Arellano on 25/11/2016.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"org.example.seed.rest"})
public class RestConfig {

  @Bean
  public FilterRegistrationBean<Filter> filterRegistrationBean() {
    final FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

    filterRegistrationBean.setFilter(this.etagFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setName("etagFilter");
    filterRegistrationBean.setOrder(1);

    return filterRegistrationBean;
  }

  @Bean(name = "etagFilter")
  public Filter etagFilter() {
    return new ShallowEtagHeaderFilter();
  }
}
