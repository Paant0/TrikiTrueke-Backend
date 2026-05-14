package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.CategoriaDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ApiResponse<List<CategoriaDTO>> obtenerCategorias() {
        return new ApiResponse<>(true, 200, "Categorías obtenidas correctamente", categoriaService.getCategorias(), "/categorias");
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoriaDTO> obtenerPorId(@PathVariable String id) {
        return new ApiResponse<>(true, 200, "Categoría obtenida correctamente", categoriaService.getCategoriaById(id), "/categorias/" + id);
    }

    @PostMapping
    public ApiResponse<CategoriaDTO> crear(@RequestBody CategoriaDTO categoria) {
        return new ApiResponse<>(true, 201, "Categoría creada correctamente", categoriaService.crear(categoria), "/categorias");
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoriaDTO> actualizar(@PathVariable String id, @RequestBody CategoriaDTO categoria) {
        return new ApiResponse<>(true, 200, "Categoría actualizada correctamente", categoriaService.actualizar(id, categoria), "/categorias/" + id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable String id) {
        categoriaService.eliminar(id);
        return new ApiResponse<>(true, 204, "Categoría eliminada correctamente", null, "/categorias/" + id);
    }
}