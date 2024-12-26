package com.sathya.springmvc.productcontroller;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.springmvc.productentity.ProductEntity;
import com.sathya.springmvc.productmodel.ProductModel;
import com.sathya.springmvc.productservice.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	
	
	
	//Providing Details Of Products
	
	/*@GetMapping("/productform")
	public String getProductForm()
	{
		return "add-product";
	}
	
	/*
	@PostMapping("/saveProduct")
	public String setProduct(ProductModel productModel)
	{
		productService.saveProductDetails(productModel);
		return "Success";
	}*/
	
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		ProductModel productModel = new ProductModel();
		productModel.setBrand("india");
		productModel.setQuantity(3);
		productModel.setDiscountRate(10.5);
		model.addAttribute("productModel", productModel);
		return "add-product";
	} 
	
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model)
	{
		HashMap<String, String> validationErrors = new HashMap<>();
		if(bindingResult.hasErrors())
		{
			for(FieldError fieldError : bindingResult.getFieldErrors())
			{
				validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			model.addAttribute("validationErrors", validationErrors);
			return "add-product";
		}
		productService.saveProductDetails(productModel);
		return "redirect:/getallproducts";
		
	} 
	
	
	
	
	
	
	
	// Displaying All The Products
	@GetMapping("/getallproducts")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		return "Product-List";
	}
	
	
	
	
	
	// Searching Product
	@GetMapping("/search")
	public String search()
	{
		return "Search-Product";
	}
	
	
	@PostMapping("/searchById")
	public String searchProductById(@RequestParam Long id, Model model)
	{
		ProductEntity product = productService.searchProductById(id);
		model.addAttribute("product", product);
		return "Search-Product";
	}
	
	
	
	
	//Deleting The Products
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable("id") Long id)
	{
		productService.deleteProductById(id);
		return "redirect:/getallproducts";
	}
	
	
	
	//Edit Products
	
	@GetMapping("/edit/{id}")
	public String showEditProductPage(@PathVariable("id") Long id, Model model)
	{
		ProductModel product = productService.editProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("id", id);
		return "edit-product";
	}
	
	@PostMapping("/editbyid/{id}")
	public String updatedData(@PathVariable("id") Long id, ProductModel productModel)
	{
		productService.editProductById(id, productModel);
		return "redirect:/getallproducts";
	}
	
	
	
}







