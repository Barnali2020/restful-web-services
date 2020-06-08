package com.barnali.rest.webservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barnali.rest.webservices.beans.FilterBean;

@RestController
public class FilterController {
	
	@GetMapping("/filtering")
	public FilterBean retrieveFilterBean() {
		return new FilterBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<FilterBean> retrieveAllFilterBean() {
		return Arrays.asList(new FilterBean("value11", "value22", "value33"), new FilterBean("value44", "value55", "value66"));
	}
	

}
