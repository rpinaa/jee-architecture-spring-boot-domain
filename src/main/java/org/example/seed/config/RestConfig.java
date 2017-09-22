package org.example.seed.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

import javax.servlet.Filter;

/**
 * Created by Ricardo Pina Arellano on 25/11/2016.
 */
@Configuration
@EnableWebFlux
public class RestConfig extends WebFluxConfigurationSupport {

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
