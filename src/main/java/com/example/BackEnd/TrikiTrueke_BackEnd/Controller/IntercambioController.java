package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.IntercambioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/intercambios", "/api/intercambios"})
public class IntercambioController {
    private final IntercambioService service;

    public IntercambioController(IntercambioService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<IntercambioDTO> crear(@RequestBody IntercambioDTO intercambioDTO) {
        return new ApiResponse<>(true, 201, "Intercambio creado correctamente", service.create(intercambioDTO), "/intercambios");
    }

    @GetMapping
    public ApiResponse<List<IntercambioDTO>> listar() {
        return new ApiResponse<>(true, 200, "Intercambios obtenidos correctamente", service.getAll(), "/intercambios");
    }

    @GetMapping("/{id}")
    public ApiResponse<IntercambioDTO> obtener(@PathVariable String id) {
        return new ApiResponse<>(true, 200, "Intercambio obtenido correctamente", service.getById(id).orElse(null), "/intercambios/" + id);
    }

    @PutMapping("/{id}")
    public ApiResponse<IntercambioDTO> actualizar(@PathVariable String id, @RequestBody IntercambioDTO intercambioDTO) {
        return new ApiResponse<>(true, 200, "Intercambio actualizado correctamente", service.update(id, intercambioDTO), "/intercambios/" + id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> eliminar(@PathVariable String id) {
        service.deleteById(id);
        return new ApiResponse<>(true, 204, "Intercambio eliminado correctamente", null, "/intercambios/" + id);
    }
}