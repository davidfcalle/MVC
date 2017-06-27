package com.davidfcalle.springmvc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.davidfcalle.springmvc.domain.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {

	private Map<Integer, Customer> customers = new HashMap<>();
	
	public CustomerServiceImpl() {
		initCustomers();
	}
	
	public void initCustomers(){
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
		
		customers.put(1, first);
		
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
		
		customers.put(2, second);
	}
	
	@Override
	public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
	}
}
