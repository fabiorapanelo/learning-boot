package com.fabiorapanelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LearningBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningBootApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner run(@Value("${com.fabiorapanelo.property}") String value){
		return args -> {
			System.out.println(value);
		};
	}
	
	@Bean
	public CommandLineRunner run2(@Autowired Environment environment){
		return args -> {
			System.out.println(environment.getProperty("com.fabiorapanelo.property2"));
		};
	}
	
	@Bean
	public CommandLineRunner run3(@Autowired JdbcTemplate jdbcTemplate){
		return args -> {
			jdbcTemplate.execute("DROP TABLE customer IF EXISTS");
	        jdbcTemplate.execute("CREATE TABLE customer (id SERIAL, name VARCHAR(255))");
	        
	        
	        List<String> list = Arrays.asList("Fabio Rapanelo", "Viviane Costa");
	        List<Customer> customers = new ArrayList<Customer>();
	        list.stream().map(name -> new Customer(name)).forEach(customers::add);
	        
	        
	        customers.stream().forEach((customer) -> {
	        	jdbcTemplate.update("INSERT INTO customer (name) values (?)", customer.getName());
	        });
	        
	        jdbcTemplate.query(
	                "SELECT id, name FROM customer",
	                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("name"))
	        ).forEach(System.out::println);
	        
		};
	}
	
	@Bean
	public CommandLineRunner run4(@Autowired CustomerRepository customerRepository){
		return args -> {
			List<String> list = Arrays.asList("Mario Rapanelo", "Lourdes Souza Rapanelo");
	        list.stream().map(name -> new Customer(name)).forEach(customerRepository::save);
			customerRepository.findAll().forEach(System.out::println);
		};
	}
	
	@Bean
	public CommandLineRunner playingWithLambdas(){
		return args -> {
			
			String DOG = "DOG";
			Predicate<String> isDog = p -> p.equals(DOG);
			Predicate<String> isNotDog = isDog.negate();
			
			//DOG = "CAT"; // This can't happen
			isDog.test("DOG"); //true
			isNotDog.test("DOG"); //false
			
			//Supplier
			Supplier<String> suplier = String::new;
			Supplier<String> suplier2 = () -> new String();
			suplier.get();
			suplier2.get();
			
			CustomerFactory customerFactory = Customer::new;
			System.out.println(customerFactory.create(1L, "Fabio", false));
			
			//Consuming
			Consumer<String> consumer = (a -> System.out.println(a));
			Consumer<String> consumer2 = (System.out::println);
			consumer.accept("Hello Lambda");
			consumer2.accept("Hello Lambda");
			
			
			Function<String, String> toUpper = (p -> p.toUpperCase());
			toUpper.apply("teste");
			
		};
	}
	
	@Bean
	public CommandLineRunner playingWithStream(@Autowired CustomerRepository customerRepository){
		return args -> {
			
			Stream.iterate(0, i -> i + 1)
				.limit(5000)
				.map(i -> new Customer("Customer" + i))
				.forEach(customerRepository::save);
			
			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
