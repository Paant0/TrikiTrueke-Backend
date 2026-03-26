package com.example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioDTO getUsuarioById(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id));
    }

    public UsuarioDTO getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + email));
    }

    public UsuarioDTO createUsuario(UsuarioDTO newUsuario) {
        if (newUsuario.getEmail() == null || newUsuario.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email es obligatorio");
        }
        if (newUsuario.getTelefono() == null || newUsuario.getTelefono().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefono es obligatorio");
        }
        if (newUsuario.getClave() == null || newUsuario.getClave().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clave es obligatoria");
        }
        if (usuarioRepository.existsByEmail(newUsuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado: " + newUsuario.getEmail());
        }
        if (usuarioRepository.existsByTelefono(newUsuario.getTelefono())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Telefono ya registrado: " + newUsuario.getTelefono());
        }
        newUsuario.setClave(passwordEncoder.encode(newUsuario.getClave()));
        newUsuario.setCreadoEn(new Date());
        return usuarioRepository.save(newUsuario);
    }

    public UsuarioDTO validarLogin(String email, String clave) {
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email es obligatorio");
        }
        if (clave == null || clave.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clave es obligatoria");
        }
        UsuarioDTO usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas"));
        if (!passwordEncoder.matches(clave, usuario.getClave())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas");
        }
        return usuario;
    }
}
