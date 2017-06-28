package org.example.seed.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Created by Ricardo Pina Arellano on 25/11/2016.
 */
@Configuration
@EnableWebMvc
public class RestConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

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
