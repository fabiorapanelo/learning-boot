package com.fabiorapanelo;

@FunctionalInterface
public interface CustomerFactory {
	
	public Customer create(Long id, String name, boolean flag);
	
	default void print(String test){
		System.out.println(test);
	}

}
