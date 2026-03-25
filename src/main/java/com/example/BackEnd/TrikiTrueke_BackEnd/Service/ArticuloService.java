package com.example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.ArticuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository repository;

    // ✅ CREAR
    public ArticuloDTO crear(ArticuloDTO articulo) {
        articulo.setCreadoEn(LocalDateTime.now());
        return repository.save(articulo);
    }

    // ✅ OBTENER TODOS
    public List<ArticuloDTO> obtenerTodos() {
        return repository.findAll();
    }

    // ✅ OBTENER POR ID
    public ArticuloDTO obtenerPorId(String id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ ACTUALIZAR
    public ArticuloDTO actualizar(String id, ArticuloDTO articulo) {
        articulo.setId(id);
        return repository.save(articulo);
    }

    // ✅ ELIMINAR
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}