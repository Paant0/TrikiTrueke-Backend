package com.example.BackEnd.TrikiTrueke_BackEnd.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Articulos")
public class ArticuloDTO {
    @Id
    private String id;
    private String titulo;





    // Getters y Setters
}