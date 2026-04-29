# TrikiTrueke Backend (src)

Backend de TrikiTrueke con Spring Boot, MongoDB y autenticación con Spring Security basada en sesion (`JSESSIONID`), sin JWT.

## Stack

- Java 21
- Spring Boot 4.0.5
- Spring Web
- Spring Data MongoDB
- Spring Security
- Lombok

## Ejecución

Requiere MongoDB en `localhost:27017` (BD: `TrikiTrueke`).

```powershell
.\mvnw.cmd spring-boot:run
```

Servidor: `http://localhost:8080`

## Estructura principal

```text
src/main/java/com/example/BackEnd/TrikiTrueke_BackEnd/
  Config/
    PasswordConfig.java
    SecurityConfig.java
    CustomUserDetailsService.java
  Controller/
    AuthController.java
    UsuarioController.java
    ArticuloController.java
    IntercambioController.java
    CategoriaController.java
    GlobalExceptionHandler.java
  Service/
    UsuarioService.java
    ArticuloService.java
    IntercambioService.java
    CategoriaService.java
  Repository/
    UsuarioRepository.java
    ArticuloRepository.java
    IntercambioRepository.java
    CategoriaRepository.java
  Model/
    UsuarioDTO.java
    LoginRequest.java
    ArticuloDTO.java
    IntercambioDTO.java
    CategoriaDTO.java
```

## Flujo de autenticación (actual)

### Registro

- `POST /auth/register` (alterno legado: `POST /usuarios`)
- Crea usuario en Mongo
- Validaciones:
  - email obligatorio y con formato válido
  - teléfono obligatorio
  - clave obligatoria
  - email/telefono unicos
- Clave cifrada con BCrypt

### Login

- `POST /auth/login` (alterno legado: `POST /usuarios/login`)
- Autentica con `AuthenticationManager` + `CustomUserDetailsService`
- Si es exitoso, guarda `SecurityContext` en sesión HTTP (`JSESSIONID`)

### Sesion y perfil

- `POST /auth/logout`: invalida sesion
- `GET /auth/me`: devuelve usuario autenticado actual

## Seguridad de rutas

- Publicas:
  - `POST /auth/register`
  - `POST /auth/login`
  - `POST /usuarios`
  - `POST /usuarios/login`
- Protegidas:
  - resto de endpoints (`anyRequest().authenticated()`)

## Endpoints funcionales

### Usuarios

- `GET /usuarios`
- `GET /usuarios/{id}`
- `POST /usuarios`
- `POST /usuarios/login`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

### Auth

- `POST /auth/register`
- `POST /auth/login`
- `POST /auth/logout`
- `GET /auth/me`

### Artículos

- `GET /articulos`
- `GET /articulos/{id}`
- `POST /articulos`
- `PUT /articulos/{id}`
- `DELETE /articulos/{id}`

### Intercambios

- `GET /intercambios`
- `GET /intercambios/{id}`
- `POST /intercambios`
- `PUT /intercambios/{id}`
- `DELETE /intercambios/{id}`

## Estado de historias (actualizado)

1. Registro de usuario: **Cumple**
2. Iniciar sesion: **Cumple**
3. Crear solicitud de trueque: **Cumple**
4. Publicar articulo: **Cumple**

## Reglas de negocio implementadas

- Al crear artículo (`POST /articulos`):
  - `usuarioId` obligatorio
  - válida existencia del usuario
  - estado automatico: `DISPONIBLE`

- Al crear intercambio (`POST /intercambios`):
  - `articuloOfrecido` obligatorio
  - `usuarioOfrece` obligatorio
  - válida existencia del artículo ofrecido
  - válida que el artículo ofrecido pertenezca al `usuarioOfrece`
  - estado automatico: `PENDIENTE`

## Configuración

`src/main/resources/application.properties`:

```properties
spring.application.name=TrikiTrueke-BackEnd
spring.mongodb.uri=mongodb://localhost:27017/TrikiTrueke
server.port=8080
spring.data.mongodb.auto-index-creation=true
```

## Testing

Actualmente, existe prueba básica de carga de contexto. Aun no hay suite de pruebas de integración para auth/usuarios/articulos/intercambios.
