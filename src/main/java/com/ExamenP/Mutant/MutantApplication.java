package com.ExamenP.Mutant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Collections;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MutantApplication {

	public static void main(String[] args) {

		//SpringApplication.run(MutantApplication.class, args);
		SpringApplication app = new SpringApplication(MutantApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8081"));
		app.run(args);
	}

}
