package com.fabiorapanelo;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Long> {

	void save(Customer customer);
	List<Customer> findAll();
	Customer findById(Long id);

}
