package com.example.BackEnd.TrikiTrueke_BackEnd.Repository;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntercambioRepository extends MongoRepository<IntercambioDTO, String> {
}
