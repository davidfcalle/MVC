package com.davidfcalle.springmvc.services;

import java.util.List;
import java.util.Optional;

import com.davidfcalle.springmvc.domain.Product;

public interface ProductService {

	List<Product> listAllProducts();
	Optional<Product> getProductById(Integer id);
	Product saveOrUpdate(Product product);
	void deleteProduct(Integer id);
}
