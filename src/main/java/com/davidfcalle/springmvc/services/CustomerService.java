package com.davidfcalle.springmvc.services;

import java.util.List;

import com.davidfcalle.springmvc.domain.Customer;

public interface CustomerService {
	List<Customer> findAll();
}
