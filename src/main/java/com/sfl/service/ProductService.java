package com.sfl.service;

import com.sfl.model.Product;
import com.sfl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	

	public ProductService(@Qualifier("productRepository") ProductRepository  productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> findAll(){
		List<Product> products = new ArrayList<>();
		products = productRepository.findAll();
		return products;
	}
	
	public Product findProduct(int id){
		return productRepository.getById(id);
	}
	
	public void save(Product product){
		productRepository.save(product);
	}
	
	public void delete(int id){
		productRepository.deleteById(id);

	}
}
