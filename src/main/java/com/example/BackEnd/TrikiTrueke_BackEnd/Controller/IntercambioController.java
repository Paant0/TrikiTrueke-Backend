package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.IntercambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intercambios")
public class IntercambioController {

    @Autowired
    private IntercambioService service;

    @PostMapping
    public IntercambioDTO crear(@RequestBody IntercambioDTO intercambioDTO) {
        return service.create(intercambioDTO);
    }

    @GetMapping
    public List<IntercambioDTO> listar() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<IntercambioDTO> obtener(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public IntercambioDTO actualizar(@PathVariable String id, @RequestBody IntercambioDTO intercambioDTO) {
        return service.update(id, intercambioDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        service.deleteById(id);
    }
}
