package com.Example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.LoginRequest;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(newUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> validarLogin(@RequestBody LoginRequest credentials) {
        return ResponseEntity.ok(
                usuarioService.validarLogin(credentials.getEmail(), credentials.getClave())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable String id, @RequestBody UsuarioDTO usuarioActualizado) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
