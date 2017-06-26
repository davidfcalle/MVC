package com.davidfcalle.springmvc.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.davidfcalle.springmvc.domain.Product;


@Service
public class ProductServiceImpl implements ProductService {

	private Map<Integer, Product> products;
	
	public ProductServiceImpl() {
		loadAllProducts();
	}
	private void loadAllProducts(){
		products = new HashMap<>();
		
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Product #1");
		p1.setPrice(new BigDecimal("19.53"));
		p1.setImageUrl("http://holahola.com");
		products.put(1, p1);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Product #2");
		p2.setPrice(new BigDecimal("123.999"));
		p2.setImageUrl("http://lalalala.com");
		products.put(2, p2);
		
	}
	
	@Override
	public List<Product> listAllProducts() {
		return new ArrayList<>(products.values());
	}
	@Override
	public Optional<Product> getProductById(Integer id) {
		Product p = products.get(id);
		Optional<Product> productOptional = Optional.of(p);
		return productOptional;
	}
	@Override
	public Product saveOrUpdate(Product product) {
		if(product != null){
			if(product.getId() == null){
				Integer id = Collections.max(products.keySet()) + 1;
				product.setId(id);
			}
			products.put(product.getId(), product);
			return product;
		}
		throw new RuntimeException("Product cannot be null");
	}
	@Override
	public void deleteProduct(Integer id) {
		products.remove(id);
	}

}
