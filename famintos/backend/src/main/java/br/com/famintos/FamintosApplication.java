package br.com.famintos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FamintosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamintosApplication.class, args);
	}

}
