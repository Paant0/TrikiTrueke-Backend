package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // REGISTRO
    @PostMapping("/register")
    public UsuarioDTO register(@RequestBody UsuarioDTO usuario) {
        return usuarioService.createUsuario(usuario);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuario) {
        usuarioService.validarLogin(
                usuario.getEmail(),
                usuario.getClave()
        );

        return "Login exitoso";
    }
}