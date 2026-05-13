package com.example.BackEnd.TrikiTrueke_BackEnd.Repository;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends MongoRepository<ArticuloDTO, String> {
}