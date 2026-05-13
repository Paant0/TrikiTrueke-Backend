package com.example.BackEnd.TrikiTrueke_BackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;


@SpringBootApplication
public class TrikiTruekeBackEndApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(TrikiTruekeBackEndApplication.class, args);
		String port = ctx.getEnvironment().getProperty("local.server.port");
		System.out.println("Servidor ejecutandose en puerto: " + (port == null ? "desconocido" : port));

		try {
			String mongoUri = ctx.getEnvironment().getProperty("spring.mongodb.uri");
			System.out.println("Mongo config: spring.mongodb.uri=" + mongoUri);

			MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
			System.out.println("Mongo conectado a DB real: " + mongoTemplate.getDb().getName());
			System.out.println("Mongo colecciones detectadas: " + String.join(", ", mongoTemplate.getCollectionNames()));
		} catch (Exception e) {
			System.out.println("No se pudo consultar Mongo para traer el primer usuario. Error: " + e.getMessage());
		}
	}


}
