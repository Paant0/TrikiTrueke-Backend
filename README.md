# TrikiTrueke Backend

Backend de una plataforma de trueque construida con Spring Boot y MongoDB. Su objetivo es centralizar la logica de consulta y administracion de entidades como usuarios, articulos, categorias e intercambios, de forma que un cliente web o movil pueda consumir una API simple y extensible.

## Descripcion del proyecto

TrikiTrueke plantea un sistema de intercambio de objetos entre usuarios. Desde el backend, eso se traduce en cuatro dominios principales:

- `Usuarios`: personas registradas en la plataforma.
- `Articulos`: objetos publicados para intercambio.
- `Categorias`: clasificacion funcional de los articulos.
- `Intercambios`: relacion de oferta y recepcion entre usuarios.

Aunque el modelo de dominio ya contempla esas cuatro entidades, el estado actual del desarrollo esta concentrado en `Usuarios`, que es el unico modulo con flujo completo de `Controller -> Service -> Repository`.

## Clasificacion del sistema

### Por tipo de aplicacion

Este proyecto se clasifica como un `backend monolitico ligero` orientado a API REST:

- `Monolitico`, porque toda la logica vive en una sola aplicacion Spring Boot.
- `Ligero`, porque la base actual todavia es pequena y con pocos modulos activos.
- `API REST`, porque expone recursos HTTP para ser consumidos por otras interfaces.

### Por arquitectura interna

La organizacion del codigo sigue una separacion por capas:

- `Controller`: define los endpoints HTTP.
- `Service`: encapsula la logica de negocio y validaciones basicas.
- `Repository`: abstrae el acceso a MongoDB mediante Spring Data.
- `Model`: representa los documentos persistidos y los datos que circulan entre capas.

Esta clasificacion es adecuada para el estado actual del proyecto porque facilita crecer desde un caso simple de lectura de usuarios hacia operaciones CRUD mas completas sin mezclar responsabilidades.

### Por estado de implementacion

El proyecto no esta terminado como plataforma de trueque completa. Su madurez actual puede clasificarse asi:

- `Base funcional`: arranque del servidor, conexion a MongoDB y consulta de usuarios.
- `Base de dominio definida`: existen modelos para articulos, categorias e intercambios.
- `Implementacion parcial`: faltan controladores, servicios, repositorios y pruebas para la mayor parte del dominio.

En otras palabras, el repositorio ya expresa la idea del sistema, pero todavia no materializa todos sus casos de uso.

## Stack tecnologico

- `Java 25`
- `Spring Boot 4.0.5`
- `Spring Web`
- `Spring Data MongoDB`
- `MongoDB`
- `Maven`
- `JUnit 5` para la prueba basica de contexto

## Estructura del proyecto

```text
src/
  main/
    java/com/example/BackEnd/TrikiTrueke_BackEnd/
      Controller/
      Model/
      Repository/
      Service/
      TrikiTruekeBackEndApplication.java
    resources/
      application.properties
  test/
    java/com/example/BackEnd/TrikiTrueke_BackEnd/
      TrikiTruekeBackEndApplicationTests.java
```

### Lectura de carpetas

#### `src/main/java/.../Controller`

Contiene la capa de entrada HTTP. Actualmente existen tres controladores:

- `UsuarioController`: expone operaciones sobre usuarios.
  - `GET /usuarios`
  - `GET /usuarios/{id}`
  - `POST /usuarios`
  - `POST /usuarios/login`

- `ArticuloController`: expone operaciones CRUD sobre articulos.
  - `GET /articulos`
  - `GET /articulos/{id}`
  - `POST /articulos`
  - `PUT /articulos/{id}`
  - `DELETE /articulos/{id}`

- `IntercambioController`: expone operaciones CRUD sobre intercambios.
  - `GET /intercambios`
  - `GET /intercambios/{id}`
  - `POST /intercambios`
  - `PUT /intercambios/{id}`
  - `DELETE /intercambios/{id}`

Ademas, existe `GlobalExceptionHandler` que centraliza el manejo de excepciones HTTP.

#### `src/main/java/.../Service`

Contiene la logica de aplicacion:

- `UsuarioService`: gestiona registro, consulta y login de usuarios (con hash de contrasena via BCrypt).
- `ArticuloService`: gestiona la creacion, consulta, actualizacion y eliminacion de articulos.
- `IntercambioService`: gestiona la creacion, consulta, actualizacion y eliminacion de intercambios.

#### `src/main/java/.../Repository`

Contiene la integracion con MongoDB. Cada repositorio extiende `MongoRepository` y hereda operaciones de lectura y persistencia sin implementar consultas manuales:

- `UsuarioRepository`
- `ArticuloRepository`
- `IntercambioRepository`

#### `src/main/java/.../Model`

Agrupa los documentos del dominio:

- `UsuarioDTO`: documento de la coleccion `Usuarios`.
- `ArticuloDTO`: documento de la coleccion `Articulos`.
- `CategoriaDTO`: documento de la coleccion `Categorias`.
- `IntercambioDTO`: documento de la coleccion `Intercambios`.

Aunque los nombres usan el sufijo `DTO`, en la practica estas clases estan actuando como `modelos persistidos de MongoDB`, no como DTOs de transporte separados. Esa distincion conviene dejarla clara para futuras refactorizaciones.

#### `src/main/resources`

Incluye la configuracion principal de la aplicacion:

- nombre del servicio
- URI de MongoDB
- puerto del servidor

#### `src/test`

Incluye una prueba minima de carga de contexto (`contextLoads`). Sirve para validar que la aplicacion arranca, pero todavia no cubre endpoints, servicios ni repositorios.

## Funcionalidad implementada

### Endpoints disponibles

#### Usuarios

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| `GET` | `/usuarios` | Obtener todos los usuarios |
| `GET` | `/usuarios/{id}` | Obtener un usuario por id (`404` si no existe) |
| `POST` | `/usuarios` | Crear un nuevo usuario (`201 Created`) |
| `POST` | `/usuarios/login` | Validar credenciales de login (`401` si invalidas) |

#### Articulos

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| `GET` | `/articulos` | Obtener todos los articulos |
| `GET` | `/articulos/{id}` | Obtener un articulo por id |
| `POST` | `/articulos` | Crear un nuevo articulo |
| `PUT` | `/articulos/{id}` | Actualizar un articulo existente |
| `DELETE` | `/articulos/{id}` | Eliminar un articulo |

#### Intercambios

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| `GET` | `/intercambios` | Obtener todos los intercambios |
| `GET` | `/intercambios/{id}` | Obtener un intercambio por id |
| `POST` | `/intercambios` | Crear un nuevo intercambio |
| `PUT` | `/intercambios/{id}` | Actualizar un intercambio existente |
| `DELETE` | `/intercambios/{id}` | Eliminar un intercambio |

## Modelo de datos identificado

### Usuario

Campos observados en `UsuarioDTO`:

- `id`
- `nombre`
- `clave`
- `email`
- `telefono`
- `creadoEn`

### Articulo

Campos observados en `ArticuloDTO`:

- `id`
- `titulo`
- `descripcion`
- `usuarioId`
- `categoriaId`
- `fotos`
- `estado`
- `creadoEn`

### Categoria

Campos observados en `CategoriaDTO`:

- `id`
- `nombre`
- `descripcion`

### Intercambio

Campos observados en `IntercambioDTO`:

- `id`
- `estado`
- `articuloOfrecido`
- `articuloRecibido`
- `usuarioOfrece`
- `usuarioRecibe`
- `creadoEn`

## Configuracion local

El archivo `src/main/resources/application.properties` define actualmente:

```properties
spring.application.name=TrikiTrueke-BackEnd
spring.mongodb.uri=mongodb://localhost:27017/TrikiTrueke
server.port=8080
```

Para ejecutar el proyecto en local necesitas:

1. Tener `Java 25` instalado.
2. Tener `MongoDB` corriendo en `localhost:27017`.
3. Tener creada o disponible la base `TrikiTrueke`.

## Ejecucion

Con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Observaciones tecnicas

- La clase principal imprime informacion de arranque y de conexion a MongoDB en consola.
- El sistema esta orientado a documentos Mongo por coleccion.
- No hay autenticacion ni autorizacion implementadas.
- Se exponen operaciones de creacion, lectura, actualizacion y eliminacion (CRUD) para usuarios, articulos e intercambios a traves de la API.
- Existe manejo global de excepciones centralizado en una clase de tipo `GlobalExceptionHandler`.
- No hay separacion entre entidades persistidas y DTOs de respuesta.

## Proxima evolucion recomendada

- Crear controladores, servicios y repositorios para `Categorias`.
- Incorporar validaciones de entrada con Bean Validation.
- Separar entidades de persistencia y DTOs de API.
- Agregar pruebas unitarias e integracion para endpoints y acceso a datos.
- Externalizar configuraciones sensibles por variables de entorno.
- Definir autenticacion para proteger datos de usuario y operaciones de intercambio.

## Conclusion

TrikiTrueke Backend ya cuenta con una base tecnica coherente para crecer como API de una plataforma de trueque. Su principal fortaleza actual es la estructura por capas y la integracion inicial con MongoDB. Su principal limitacion es que el dominio esta modelado de forma parcial: la idea del sistema esta clara en las clases, pero la implementacion operativa aun esta centrada casi por completo en usuarios.
