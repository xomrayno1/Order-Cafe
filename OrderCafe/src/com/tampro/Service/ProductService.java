package com.tampro.Service;

import java.util.List;

import com.tampro.Model.Product;

public interface ProductService {
	
    public List<Product> getAllProduct();
	
	public Product getProductById(int id);
	
	public void addProduct(Product product);
	
	public void updateProductById(Product product);
	
	public void deleteProductById(Product product);
	
	public List<Product> getProductByName(String name);

}
