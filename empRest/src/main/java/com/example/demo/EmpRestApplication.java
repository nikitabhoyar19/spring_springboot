package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info =@Info(title = "Employee CRUD REST API", version = "1.0", description = "Sample API"))
public class EmpRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpRestApplication.class, args);
		
		System.out.println("Hello its working .....");
		
//		Path for swagger API document just add after our URL : /v3/api-docs:
//			http://localhost:8080/v3/api-docs
//		To see HTML page also swagger have : swagger-ui/index.html
//			http://localhost:8080/swagger-ui/index.html
	}

}
