package com.example.BackEnd.TrikiTrueke_BackEnd.Repository;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<UsuarioDTO, String> {
    Optional<UsuarioDTO> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByTelefono(String telefono);
}
