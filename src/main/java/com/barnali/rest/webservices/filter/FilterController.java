package com.barnali.rest.webservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barnali.rest.webservices.beans.FilterBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {
	
	//filter field2 and allow field1, field3
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveFilterBean() {
		FilterBean bean = new FilterBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterId", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	//filter field1 and allow field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveAllFilterBean() {
		
		List<FilterBean> list = Arrays.asList(new FilterBean("value11", "value22", "value33"),
				new FilterBean("value44", "value55", "value66"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterId", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
	

}
