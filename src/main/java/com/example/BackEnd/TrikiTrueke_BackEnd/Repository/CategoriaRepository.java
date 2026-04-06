//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.BackEnd.TrikiTrueke_BackEnd.Repository;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.CategoriaDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends MongoRepository<CategoriaDTO, String> {
}
