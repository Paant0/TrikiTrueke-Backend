package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.ArticuloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    private ArticuloService service;

    @PostMapping
    public ArticuloDTO crear(@RequestBody ArticuloDTO articulo) {
        return service.crear(articulo);
    }

    @GetMapping
    public List<ArticuloDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ArticuloDTO obtenerPorId(@PathVariable String id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ArticuloDTO actualizar(@PathVariable String id, @RequestBody ArticuloDTO articulo) {
        return service.actualizar(id, articulo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.eliminar(id);
    }
}