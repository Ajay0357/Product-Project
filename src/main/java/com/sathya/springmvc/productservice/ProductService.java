package com.sathya.springmvc.productservice;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.springmvc.productentity.ProductEntity;
import com.sathya.springmvc.productmodel.ProductModel;
import com.sathya.springmvc.repository.ProductRepository;



@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public void saveProductDetails(ProductModel productModel)
	{
		double stockValue;
		stockValue = productModel.getPrice() * productModel.getQuantity();
		
		double discountPrice;
		discountPrice = productModel.getPrice() * productModel.getDiscountRate()/100;
		
		double offerPrice;
		offerPrice = productModel.getPrice() - discountPrice;
		
		double taxRate = 0.18;
		
		double finalPrice;
		finalPrice = offerPrice + (offerPrice * taxRate);
		
		ProductEntity productEntity = new ProductEntity();
		
		productEntity.setName(productModel.getName());
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadeIn(productModel.getMadeIn());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setDiscountRate(productModel.getDiscountRate());
		productEntity.setDiscountPrice(discountPrice);
		productEntity.setTaxRate(taxRate);
		productEntity.setOfferPrice(offerPrice);
		productEntity.setFinalPrice(finalPrice);
		productEntity.setStockValue(stockValue);
		
		productRepository.save(productEntity);
		
		}
	
	
	
	//Getting the Data
	
	public List<ProductEntity> getAllProducts()
	{
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}
	
	
	//Searching 
	
	public ProductEntity searchProductById(Long id)
	{
		Optional <ProductEntity> optionalData = productRepository.findById(id);
		if(optionalData.isPresent())
		{
			ProductEntity products = optionalData.get();
			return products;
		}
		else
		{
			return null;
		}
		
	}
	
	
	
	
	
	//Delete Products
	public void deleteProductById(Long id)
	{
		productRepository.deleteById(id);
	}
	
	
	//Edit Products
	
	public ProductModel editProductById(Long id)
	{
		Optional <ProductEntity> optionalData = productRepository.findById(id);
		if(optionalData.isPresent())
		{
			ProductEntity product = optionalData.get();
			
			ProductModel productModel = new ProductModel();
			
			productModel.setName(product.getName());
			productModel.setBrand(product.getBrand());
			productModel.setMadeIn(product.getMadeIn());
			productModel.setPrice(product.getPrice());
			productModel.setQuantity(product.getQuantity());
			productModel.setDiscountRate(product.getDiscountRate());
			
			return productModel;
			
		}
		else {
			return null;
		}
		
		
	}
	
	
	//Updating By Editing
	public void editProductById(Long id, ProductModel productModel)
	{
		Optional<ProductEntity> optionalData = productRepository.findById(id);
		if(optionalData.isPresent())
		{
			ProductEntity productEntity = new ProductEntity();
			
			double discountPrice;
			discountPrice = productModel.getPrice() * productModel.getDiscountRate();
			
			double offerPrice;
			offerPrice = productModel.getPrice() - discountPrice;
			
			double taxRate = 18;
			
			double finalPrice;
			finalPrice = offerPrice + taxRate;
			
			double stockValue;
			stockValue = productModel.getQuantity() * productModel.getPrice();
			
			productEntity.setName(productModel.getName());
			productEntity.setBrand(productModel.getBrand());
			productEntity.setMadeIn(productModel.getMadeIn());
			productEntity.setPrice(productModel.getPrice());
			productEntity.setQuantity(productModel.getQuantity());
			productEntity.setDiscountRate(productModel.getDiscountRate());
			
			productEntity.setDiscountPrice(discountPrice);
			productEntity.setTaxRate(taxRate);
			productEntity.setOfferPrice(offerPrice);
			productEntity.setFinalPrice(finalPrice);
			productEntity.setStockValue(stockValue);
			
			productRepository.save(productEntity);
			
		}
		
		
		
	}
	
	
	
}
