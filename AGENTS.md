# TrikiTrueke Backend - AGENTS.md

## Run commands

```powershell
.\mvnw.cmd spring-boot:run
```

Requires MongoDB running on `localhost:27017`.

## Tech stack

- Java 21
- Spring Boot 4.0.5
- MongoDB (database: TrikiTrueke)
- Server port: 8080

## Architecture

- Controllers: `UsuarioController`, `ArticuloController`, `IntercambioController`, `CategoriaController`
- Authentication: `SecurityConfig`, `CustomUserDetailsService`
- Password hashing: BCrypt via `PasswordEncoder`

## Key files

- `src/main/resources/application.properties` - MongoDB URI and port config
- `src/main/java/.../Config/SecurityConfig.java` - Security filter chain
- `src/main/java/.../Config/CustomUserDetailsService.java` - User loader for auth
- `src/main/java/.../Controller/` - HTTP endpoints
- `src/main/java/.../Service/` - Business logic with BCrypt password hashing
- `src/main/java/.../Repository/` - MongoDB access

## Dependencies

- `spring-boot-starter-web`
- `spring-boot-starter-data-mongodb`
- `spring-boot-starter-security`
- `lombok`

## Testing

- Only basic context load test exists