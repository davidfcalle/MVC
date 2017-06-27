package com.davidfcalle.springmvc.controllers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.davidfcalle.springmvc.domain.Customer;
import com.davidfcalle.springmvc.services.CustomerService;

public class CustomerControllerTest {
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerControler customerController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void testCustomerList() throws Exception{
		
		List<Customer> customers = new ArrayList<>();
		Customer first = new Customer();
		first.setId(1);
		first.setFirstName("David");
		first.setLastName("Calle");
		first.setAddressLine1("calle 2");
		first.setAddressLine2("#21 - 75");
		first.setPhoneNumber("+9823322312");
		first.setCity("Bogotá");
		first.setState("Cundi");
		first.setZipCode("331234");
		
		customers.add(first);
		
		Customer second = new Customer();
		second.setId(2);
		second.setFirstName("Fabián");
		second.setLastName("Merchán");
		second.setAddressLine1("calle 2");
		second.setAddressLine2("#21 - 75");
		second.setPhoneNumber("+9823322312");
		second.setCity("Bogotá");
		second.setState("Cundi");
		second.setZipCode("331234");
		
		customers.add(second);
		
		
		when(customerService.findAll()).thenReturn(customers);
		mockMvc.perform(get("/customers/"))
			.andExpect(status().isOk())
			.andExpect(view().name("customers/customers"))
			.andExpect(model().attribute("customers",  hasSize(2)));
	}
}
