package com.barnali.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;
	}
	
	//Bean to read message.properties file
	@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}
}
