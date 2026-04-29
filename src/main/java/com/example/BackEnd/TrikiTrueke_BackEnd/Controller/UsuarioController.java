package com.Example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.ApiResponse;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.LoginRequest;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // REGISTRO
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDTO>> register(
            @RequestBody UsuarioDTO usuario,
            HttpServletRequest request
    ) {
        UsuarioDTO created = usuarioService.createUsuario(usuario);
        ApiResponse<UsuarioDTO> body = new ApiResponse<>(
                true,
                HttpStatus.CREATED.value(),
                "Usuario registrado correctamente",
                created,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UsuarioDTO>> login(
            @RequestBody LoginRequest credentials,
            HttpServletRequest request
    ) {
        UsuarioDTO logged = usuarioService.validarLogin(credentials.getEmail(), credentials.getClave());
        ApiResponse<UsuarioDTO> body = new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                "Login exitoso",
                logged,
                request.getRequestURI()
        );
        return ResponseEntity.ok(body);
    }
}
