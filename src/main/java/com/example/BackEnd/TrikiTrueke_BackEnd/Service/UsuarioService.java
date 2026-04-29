package com.Example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Repository.UsuarioRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsuarioService implements UserDetailsService {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

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
        if (!EMAIL_PATTERN.matcher(newUsuario.getEmail()).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de email invalido");
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
        try {
            return usuarioRepository.save(newUsuario);
        } catch (DuplicateKeyException e) {
            String msg = e.getMessage() != null && e.getMessage().contains("email")
                    ? "Email ya registrado: " + newUsuario.getEmail()
                    : "Telefono ya registrado: " + newUsuario.getTelefono();
            throw new ResponseStatusException(HttpStatus.CONFLICT, msg);
        }
    }

    public UsuarioDTO validarLogin(String email, String clave) {
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email es obligatorio");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de email invalido");
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

    public UsuarioDTO updateUsuario(String id, UsuarioDTO usuarioActualizado) {
        UsuarioDTO usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id));

        if (usuarioActualizado.getNombre() != null && !usuarioActualizado.getNombre().isBlank()) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
        }

        if (usuarioActualizado.getEmail() != null && !usuarioActualizado.getEmail().isBlank()) {
            if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail())
                    && usuarioRepository.existsByEmail(usuarioActualizado.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado: " + usuarioActualizado.getEmail());
            }
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
        }

        if (usuarioActualizado.getTelefono() != null && !usuarioActualizado.getTelefono().isBlank()) {
            if (!usuarioExistente.getTelefono().equals(usuarioActualizado.getTelefono())
                    && usuarioRepository.existsByTelefono(usuarioActualizado.getTelefono())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Telefono ya registrado: " + usuarioActualizado.getTelefono());
            }
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        }

        if (usuarioActualizado.getClave() != null && !usuarioActualizado.getClave().isBlank()) {
            usuarioExistente.setClave(passwordEncoder.encode(usuarioActualizado.getClave()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    public void deleteUsuario(String id) {
        UsuarioDTO usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado: " + id));

        usuarioRepository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioDTO usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserPrincipal(usuario);
    }

    public record UserPrincipal(UsuarioDTO usuario) implements UserDetails {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        @Override
        public String getPassword() {
            return usuario.getClave();
        }

        @Override
        public String getUsername() {
            return usuario.getEmail();
        }
    }
}
