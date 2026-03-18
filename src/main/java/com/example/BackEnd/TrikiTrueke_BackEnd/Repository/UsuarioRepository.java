package com.example.BackEnd.TrikiTrueke_BackEnd.Repository;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<UsuarioDTO, String> {
}

