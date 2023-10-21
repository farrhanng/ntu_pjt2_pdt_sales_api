package com.pdt_sales.pdt_sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdtSalesApplication {

	public static void main(String[] args) {
	try{
		SpringApplication.run(PdtSalesApplication.class, args);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

}
