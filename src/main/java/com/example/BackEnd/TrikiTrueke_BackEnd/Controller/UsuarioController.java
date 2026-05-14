package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.LoginRequest;
import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ApiResponse<List<UsuarioDTO>> getUsuarios() {
        return new ApiResponse<>(true, 200, "Usuarios obtenidos correctamente", usuarioService.getUsuarios(), "/usuarios");
    }

    @GetMapping("/{id}")
    public ApiResponse<UsuarioDTO> getUsuarioById(@PathVariable String id) {
        return new ApiResponse<>(true, 200, "Usuario obtenido correctamente", usuarioService.getUsuarioById(id), "/usuarios/" + id);
    }
}