package com.fabiorapanelo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebApplication {

	@RequestMapping("/index")
	public String index(@RequestParam(value = "name", required = true) String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@RequestMapping("/products")
	public String products(Model model) {

		List<Product> products = Arrays.asList(new Product(1L, "Smartphone"), new Product(2L, "TV"));
		model.addAttribute("products", products);
		return "products";
	}

}