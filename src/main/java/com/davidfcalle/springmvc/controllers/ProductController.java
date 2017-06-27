package com.davidfcalle.springmvc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidfcalle.springmvc.domain.Product;
import com.davidfcalle.springmvc.services.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products")
	public String listProducts(Model model){
		model.addAttribute("products", productService.listAllProducts());
		return "products/products";
	}
	
	
	@RequestMapping("/products/{id}")
	public String getProductById(Model model, @PathVariable("id") Integer id){
		Optional<Product> productOptional = productService.getProductById(id);
		if(productOptional.isPresent()){
			model.addAttribute("product", productOptional.get());
			return "products/product";
		}
		throw new IllegalArgumentException("Invalid id");
	}
	
	@RequestMapping("/product/new")
	public String newProduct(Model model){
		model.addAttribute("product", new Product());
		return "products/productform";
	}
	
	@RequestMapping("/products/edit/{id}")
	public String edit(Model model, @PathVariable Integer id){
		Optional<Product> productOptional = productService.getProductById(id);
		if(productOptional.isPresent()){
			model.addAttribute("product", productOptional.get());
			return "products/productform";
		}
		throw new IllegalArgumentException();
	}
	
	@RequestMapping(value="/product", method = RequestMethod.POST)
	public String saveOrUpdateProduct(Product product){
		Product p = productService.saveOrUpdate(product);
		return "redirect:/products/" + p.getId();
	}
	
	@RequestMapping("/products/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		Optional<Product> productOptional = productService.getProductById(id);
		if(productOptional.isPresent()){
			productService.deleteProduct(id);
			return "redirect:/products/";
		}
		throw new IllegalArgumentException("Invalid id");
	}
}
