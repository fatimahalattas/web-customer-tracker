package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer (Model theModel) {
		
		// get customers from the service
		
		List<Customer> customers = customerService.getCustomers();
		
		// add the customers to the model
		
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String addCustomer (Model theModel) {
		
		// create model attribute to bind form data
		Customer customer = new Customer();
		
		theModel.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId,
			Model theModel) {
		
		// get the customer from the service
		Customer theCustomer = customerService.getCustomer(customerId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer",theCustomer);
		
		// send over to our form
				
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String showFormForDelete(@RequestParam("customerId") int customerId) {
		
		// delete the customer
		customerService.deleteCustomer(customerId);

		
				
		return "redirect:/customer/list";
	}

}
