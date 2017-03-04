package com.fabiorapanelo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

	private CustomerRepository customerRepository;
	
	public CustomerRestController(@Autowired CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomers(@PathVariable("customerId") long customerId){
		return customerRepository.findById(customerId);
	}
	
	@GetMapping("/customers/search")
	public List<Customer> searchCustomers(@RequestParam("name") String name){
		return customerRepository.findAll()
				.stream()
				.filter(customer -> customer.getName().contains(name))
				.collect(Collectors.toList());
	}
}
