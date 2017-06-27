package org.example.seed.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ricardo Pina Arellano on 25/11/2016.
 */
@Configuration
@EnableWebMvc
public class APIConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
}
