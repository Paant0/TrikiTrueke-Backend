package com.example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO newUsuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(newUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> validarLogin(@RequestBody UsuarioDTO credentials) {
        return ResponseEntity.ok(
                usuarioService.validarLogin(credentials.getEmail(), credentials.getClave())
        );
    }
}
