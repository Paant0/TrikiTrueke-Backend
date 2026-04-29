package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.CategoriaDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<CategoriaDTO> listar() {
        return service.getCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaDTO obtener(@PathVariable String id) {
        return service.getCategoriaById(id);
    }
    @PostMapping
    public CategoriaDTO crear(@RequestBody CategoriaDTO CategoriaDTO) {
        return service.crear(CategoriaDTO);
    }

}