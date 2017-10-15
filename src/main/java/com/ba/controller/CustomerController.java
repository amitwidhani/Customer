package com.ba.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ba.domain.account.Account;
import com.ba.domain.booking.Booking;
import com.ba.domain.customer.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private static final String ACCOUNT_ENDPOINT_KEY = "ACCOUNT_SERVICE_EP";
	
	private RestTemplate restTemplate;
	
	private static List<Customer> allCustomers = new ArrayList<Customer>();
	
	
	static {
		allCustomers.add(new Customer("1","Amit","Widhani"));
		allCustomers.add(new Customer("2","Resham","Dhillon"));
	}
	
	public CustomerController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
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

	@RequestMapping( method = RequestMethod.GET, value = "/{customerId}")
	@ResponseBody
	public Customer retrieveCustomer(@PathVariable long customerId) {
		Optional<Customer> customer = allCustomers.stream().filter(p -> p.getId().equalsIgnoreCase(String.valueOf(customerId))).findFirst();
		
		Customer cust = customer.orElse(new Customer(String.valueOf(customerId),null,null));
		
		cust.setAccount(getAccount(String.valueOf(customerId)));	
		
		cust.setBookings(getBookings());
		
		return cust;
	}	
	
	@SuppressWarnings("unchecked")
	private List<Booking> getBookings() {
		
		List<Booking> bookings = new ArrayList<Booking>();
		try {
			bookings = restTemplate.getForObject(
					Utils.getCustomerEndpoint("/bookings"), ArrayList.class);
			
			System.out.println("Environment variable based lookup response" + bookings.toString());
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		
		return bookings;
	}
	
	private Account getAccount(String customerId) {
		
		Account account = null;
		
		try {
			System.out.println("Env Variable is " + System.getenv(ACCOUNT_ENDPOINT_KEY));
			account = restTemplate.getForObject(
					"http://" + System.getenv(ACCOUNT_ENDPOINT_KEY) + "/accounts", Account.class);
			
			System.out.println("Namespace based Lookup response" + account.toString());
		} catch(Exception aexp) {
			aexp.printStackTrace();
		}

		return account;
	}	
	
}
