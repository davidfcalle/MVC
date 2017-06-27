package com.davidfcalle.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidfcalle.springmvc.services.CustomerService;

@Controller
public class CustomerControler {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customers")
	public String findAll(Model model){
		model.addAttribute("customers", customerService.findAll());
		return "customers/customers";
	}
	
}
