package com.fabiorapanelo;

import org.springframework.data.repository.Repository;

public interface CustomerRepository extends Repository<Customer, Long> {

	void save(Customer customer);
	Iterable<Customer> findAll();

}
