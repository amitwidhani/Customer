package com.ba.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ba.domain.customer.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private static List<Customer> allCustomers = new ArrayList<Customer>();
	
	static {
		allCustomers.add(new Customer("1","Amit","Widhani"));
		allCustomers.add(new Customer("2","Resham","Dhillon"));
	}

	@RequestMapping( method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> retrieveAllCustomers(@RequestParam Map<String,String> params) {
		
		List<Customer> customers = null;
		
		String firstName = params.get("firstName");
		
		if (firstName != null) {
			customers = allCustomers.stream()
									.parallel()
									.filter(p -> p.getFirstName().equalsIgnoreCase(firstName))
									.sequential()
									.collect(Collectors.toCollection(ArrayList::new));
		} else {
			customers = allCustomers;
		}
		
		return customers;
	}

	
	
}
