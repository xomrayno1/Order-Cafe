package com.tampro.Service.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.tampro.Dao.ProductRepository;
import com.tampro.Dao.Impl.ProductRepositoryImpl;
import com.tampro.Model.Product;
import com.tampro.Service.ProductService;

public class ProductServiceImpl  implements ProductService{
	
	private static ProductRepository productRepository = new ProductRepositoryImpl();

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.getAllProduct();
		
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return productRepository.getProductById(id);
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		product.setCreateDate(Date.valueOf(LocalDate.now()));
		productRepository.addProduct(product);
	}

	@Override
	public void updateProductById(Product product) {
		// TODO Auto-generated method stub
		productRepository.updateProductById(product);
	}

	@Override
	public void deleteProductById(Product product) {
		// TODO Auto-generated method stub
		productRepository.deleteProductById(product);
	}

	@Override
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.getProductByName(name);
	}

}
