package com.barnali.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barnali.rest.webservices.beans.Name;
import com.barnali.rest.webservices.beans.PersonV1;
import com.barnali.rest.webservices.beans.PersonV2;

@RestController
public class VersioningController {
	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
