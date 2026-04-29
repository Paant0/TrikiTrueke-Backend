# Proyecto Spring Boot - User Auth API

Proyecto base con Spring Boot 4.0.5, Spring Security y MongoDB para autenticación de usuarios.

## Estructura del Proyecto

```
src/main/java/com/example/Temp/
├── TempApplication.java          # Main class
├── Config/
│   └── SecurityConfig.java       # Configuración de seguridad Spring
├── Controller/
│   ├── UserController.java      # CRUD usuarios
│   └── AuthController.java       # Login/Auth
├── Service/
│   └── UserService.java         # Lógica de negocio
├── Repository/
│   └── UserRepository.java      # Acceso a datos Mongo
├── DTO/
│   ├── UserDTO.java
│   ├── LoginRequest.java
│   └── LoginResponse.java
└── Exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

## Dependencias (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

## Configuración (application.properties)

```properties
spring.application.name=TrikiTrueke
```

## Entidades

### User.java
```java
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

}
```

## Repositorio

### UserRepository.java
```java
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
```

## Servicio

### UserService.java
```java
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() { ... }
    public UserDTO findById(Long id) { ... }
    public UserDTO create(UserDTO userDTO) {
        User user = toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return toDTO(userRepository.save(user));
    }
    public UserDTO update(Long id, UserDTO userDTO) { ... }
    public void delete(Long id) { ... }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
```

## Controladores

### UserController.java
```java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAll() { return userService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) { ... }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) { ... }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) { ... }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { ... }
}
```

### AuthController.java
```java
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());

        LoginResponse response = new LoginResponse(
            "token-sin-jwt-por-ahora",
            loginRequest.getUsername(),
            userDetails.getAuthorities().iterator().next().getAuthority(),
            200
        );
        return ResponseEntity.ok(response);
    }
}
```

## Configuración de Seguridad

### SecurityConfig.java
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

## Excepciones

### ResourceNotFoundException.java
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

### GlobalExceptionHandler.java
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = Map.of(
            "timestamp", LocalDateTime.now().toString(),
            "status", HttpStatus.NOT_FOUND.value(),
            "error", "Not Found",
            "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) { ... }
}
```

## DTOs

### UserDTO.java
```java
@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
```

### LoginRequest.java
```java
@Data
public class LoginRequest {
    private String username;
    private String password;
}
```

### LoginResponse.java
```java
@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private int status;
}
```

## Endpoints

| Método | Endpoint | Descripción |
|--------|---------|-----------|
| POST | /api/auth/login | Login de usuario |
| GET | /api/users | Listar usuarios |
| GET | /api/users/{id} | Obtener usuario por ID |
| POST | /api/users | Crear usuario |
| PUT | /api/users/{id} | Actualizar usuario |
| DELETE | /api/users/{id} | Eliminar usuario |

## Ejecutar

```bash
./mvnw spring-boot:run
```

La aplicación corre en `http://localhost:8080`

## Base de Datos

Crear base de datos PostgreSQL:

```sql
CREATE DATABASE TempDB;
```

## Notas

- Las contraseñas se encriptan con BCrypt antes de guardar
- El login usa AuthenticationManager de Spring Security
- Rutas `/api/users/**` y `/api/auth/**` son públicas (permitAll)
- Para producción: cambiar permitAll a autenticación

## Flujo de autenticación

```
Petición
    │
    ├──→ SecurityConfig
    │         │
    │         ├──→ SecurityFilterChain (configura rutas)
    │         │
    │         └──→ UserDetailService
    │                     │
    │                     └──→ UserRepository
    │                                   │
    │                           findByEmail()
    │                                   │
    │                     ←─── User
    │                     │
    │                     └──→ User Service (carga datos)
    │
    ←─── 200 OK
```

### Descripción del flujo

1. **Petición** → Llega al sistema
2. **SecurityConfig** → Se dirige al `UserDetailService` y configura rutas con `SecurityFilterChain`
3. **UserDetailService** → Consulta a `UserRepository` + `loadUserByEmail()`
4. **UserRepository** → Ejecuta `findByEmail()` → Devuelve `User`
5. **User** → Pasa a `Service User`
6. **Respuesta** → `200 OK`

## Próximos pasos

- [ ] Implementar `SecurityConfig` con `SecurityFilterChain`
- [ ] Crear `UserDetailService` que use `UserRepository`
- [ ] Configurar `UserRepository` con `findByEmail()`
- [ ] Proteger endpoints con roles (ADMIN, USER)

## Ejecutar