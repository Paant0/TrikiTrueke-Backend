package com.example.BackEnd.TrikiTrueke_BackEnd;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;

import java.util.List;

@SpringBootApplication
public class TrikiTruekeBackEndApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(TrikiTruekeBackEndApplication.class, args);
		String port = ctx.getEnvironment().getProperty("local.server.port");
		System.out.println("Servidor ejecutandose en puerto: " + (port == null ? "desconocido" : port));

		try {
			String mongoUri = ctx.getEnvironment().getProperty("spring.data.mongodb.uri");
			String mongoDatabaseProp = ctx.getEnvironment().getProperty("spring.data.mongodb.database");
			System.out.println("Mongo config: spring.data.mongodb.uri=" + mongoUri);
			System.out.println("Mongo config: spring.data.mongodb.database=" + mongoDatabaseProp);

			MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
			System.out.println("Mongo conectado a DB real: " + mongoTemplate.getDb().getName());
			System.out.println("Mongo colecciones detectadas: " + String.join(", ", mongoTemplate.getCollectionNames()));
		} catch (Exception e) {
			System.out.println("No se pudo consultar Mongo para traer el primer usuario. Error: " + e.getMessage());
		}
	}


}
