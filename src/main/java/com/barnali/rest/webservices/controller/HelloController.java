package com.barnali.rest.webservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barnali.rest.webservices.beans.HelloWorldBean;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello-world-bean");
	}

	//consumer will pass locale in header, if header is not passed will be us locale
	//'required=false' : any value of "Accept-Language" apart from fr and nl will pick the value from message.properties
	@GetMapping(path = "/hello-world-internalized")
	public String helloWorldInternalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {      
		
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
}
