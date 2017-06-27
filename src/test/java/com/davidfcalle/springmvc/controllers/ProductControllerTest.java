package com.davidfcalle.springmvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.davidfcalle.springmvc.domain.Product;
import com.davidfcalle.springmvc.services.ProductService;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class ProductControllerTest {

	@Mock
	private ProductService productService;
	
	@InjectMocks
	private ProductController productController;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testProductList() throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		
		
		when(productService.listAllProducts()).thenReturn(products);
		
		mockMvc.perform(get("/products/"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/products"))
			.andExpect(model().attribute("products", hasSize(2)));
		
	}
	
	@Test
	public void testEditExistingProduct() throws Exception{
		Integer testId = 1;
		when(productService.getProductById(testId)).thenReturn(Optional.of(new Product()));
		
		mockMvc.perform(get("/products/edit/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("products/productform"))
			.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	public void testCreateNewProduct() throws Exception {
		Integer createdProduct = 1;
		Product p = new Product();
		p.setId(createdProduct);
		when(productService.saveOrUpdate(p)).thenReturn(p);
		
		mockMvc.perform(post("/product/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("redirect:/products/1"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testDeleteExistingProject() throws Exception{
		Integer id = 1;
		when(productService.getProductById(id)).thenReturn(Optional.of(new Product()));
		doNothing().when(productService).deleteProduct(id);
		mockMvc.perform(get("/products/delete/1"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/products/"));
	}
}
