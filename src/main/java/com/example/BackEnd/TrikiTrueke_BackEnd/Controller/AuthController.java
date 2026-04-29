package com.Example.BackEnd.TrikiTrueke_BackEnd.Controller;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.LoginRequest;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.UsuarioDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO newUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(newUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(
            @RequestBody LoginRequest credentials,
            HttpServletRequest request
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getClave())
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        UsuarioDTO usuario = usuarioService.getUsuarioByEmail(credentials.getEmail());
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.setHeader("Set-Cookie", "JSESSIONID=; Path=/; HttpOnly; Max-Age=0");

        Map<String, String> body = new LinkedHashMap<>();
        body.put("message", "Sesion cerrada correctamente");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> me(Authentication authentication) {
        return ResponseEntity.ok(usuarioService.getUsuarioByEmail(authentication.getName()));
    }
}
