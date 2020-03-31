package ru.tokarev.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import ru.tokarev.shop.repository.entity.Products;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
//@SpringBootApplication
public class ShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}


}