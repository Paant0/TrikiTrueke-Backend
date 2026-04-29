package com.Example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.CategoriaDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Repository.CategoriaRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> getCategorias() {
        return this.categoriaRepository.findAll();
    }

    public CategoriaDTO getCategoriaById(String id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Categoria no encontrada: " + id
                ));
    }
    public CategoriaDTO crear(CategoriaDTO categoria) {
        return categoriaRepository.save(categoria);
    }
}