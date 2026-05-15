package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.ArticuloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/articulos", "/api/articulos"})
public class ArticuloController {
    private final ArticuloService service;

    public ArticuloController(ArticuloService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<ArticuloDTO> crear(@RequestBody ArticuloDTO articulo) {
        return new ApiResponse<>(true, 201, "Artículo creado correctamente", service.crear(articulo), "/articulos");
    }

    @GetMapping
    public ApiResponse<List<ArticuloDTO>> obtenerTodos() {
        return new ApiResponse<>(true, 200, "Artículos obtenidos correctamente", service.obtenerTodos(), "/articulos");
    }

    @GetMapping("/{id}")
    public ApiResponse<ArticuloDTO> obtenerPorId(@PathVariable String id) {
        return new ApiResponse<>(true, 200, "Artículo obtenido correctamente", service.obtenerPorId(id), "/articulos/" + id);
    }

    @PutMapping("/{id}")
    public ApiResponse<ArticuloDTO> actualizar(@PathVariable String id, @RequestBody ArticuloDTO articulo) {
        return new ApiResponse<>(true, 200, "Artículo actualizado correctamente", service.actualizar(id, articulo), "/articulos/" + id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return new ApiResponse<>(true, 204, "Artículo eliminado correctamente", null, "/articulos/" + id);
    }
}