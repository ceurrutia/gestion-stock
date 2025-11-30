package com.sistemastock.stock;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {

		//instancio dotenv
		Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMissing().load();

		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(StockApplication.class, args);
	}

}
