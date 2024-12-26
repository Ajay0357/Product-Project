package com.sathya.springmvc.productmodel;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	@NotBlank(message = "Product Name Cannot Be Blank")
	private String name;
	
	@NotBlank(message = "Product Brand Cannot Be Blank")
	private String brand;
	
	@NotBlank(message = "Product MadeIn Cannot Be Blank")
	private String madeIn;
	
	@Positive(message = "Product Price Must Be Greater Than Zero")
	private double price;
	
	@Min(value = 1, message = "Product Quantity Must Be Atleast 1")
	private int quantity;
	
	@DecimalMax(value = "100.0", message = "Product Discount Rate Cannot Exceed 100" )
	private double discountRate;
}

