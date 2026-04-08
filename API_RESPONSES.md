# API Response Examples - Usuario Endpoints

## Success Responses

### GET /usuarios - Get All Users
**Status:** `200 OK`
```json
[
  {
    "id": "507f1f77bcf86cd799439011",
    "nombre": "John Doe",
    "email": "john@example.com",
    "telefono": "1234567890",
    "creadoEn": "2026-04-07T10:30:00.000+00:00"
  }
]
```
*Note: `clave` is not returned for security reasons*

---

### GET /usuarios/{id} - Get User by ID
**Status:** `200 OK`
```json
{
  "id": "507f1f77bcf86cd799439011",
  "nombre": "John Doe",
  "email": "john@example.com",
  "telefono": "1234567890",
  "creadoEn": "2026-04-07T10:30:00.000+00:00"
}
```

---

### POST /usuarios - Create User
**Status:** `201 Created`
**Request:**
```json
{
  "nombre": "Jane Smith",
  "clave": "securePassword123",
  "email": "jane@example.com",
  "telefono": "0987654321"
}
```

**Response:**
```json
{
  "id": "507f1f77bcf86cd799439012",
  "nombre": "Jane Smith",
  "email": "jane@example.com",
  "telefono": "0987654321",
  "creadoEn": "2026-04-07T11:00:00.000+00:00"
}
```

---

### POST /usuarios/login - Login
**Status:** `200 OK`
**Request:**
```json
{
  "email": "jane@example.com",
  "clave": "securePassword123"
}
```

**Response:**
```json
{
  "id": "507f1f77bcf86cd799439012",
  "nombre": "Jane Smith",
  "email": "jane@example.com",
  "telefono": "0987654321",
  "creadoEn": "2026-04-07T11:00:00.000+00:00"
}
```

---

### PUT /usuarios/{id} - Update User
**Status:** `200 OK`
**Request:**
```json
{
  "nombre": "Jane Smith Updated",
  "email": "jane.new@example.com"
}
```

**Response:**
```json
{
  "id": "507f1f77bcf86cd799439012",
  "nombre": "Jane Smith Updated",
  "email": "jane.new@example.com",
  "telefono": "0987654321",
  "creadoEn": "2026-04-07T11:00:00.000+00:00"
}
```

---

### DELETE /usuarios/{id} - Delete User
**Status:** `204 No Content`
**Response:** *(empty body)*

---

## Error Responses

All error responses use the GlobalExceptionHandler and return a consistent JSON format:

### 400 Bad Request - Validation Error
**Status:** `400 Bad Request`
```json
{
  "timestamp": "2026-04-07T12:00:00.000Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Email es obligatorio",
  "path": "/usuarios"
}
```

**Common scenarios:**
- Missing required fields (email, telefono, clave)
- Invalid input data

---

### 404 Not Found - Resource Not Found
**Status:** `404 Not Found`
```json
{
  "timestamp": "2026-04-07T12:00:00.000Z",
  "status": 404,
  "error": "Not Found",
  "message": "Usuario no encontrado: 507f1f77bcf86cd799439011",
  "path": "/usuarios/507f1f77bcf86cd799439011"
}
```

**Common scenarios:**
- User ID doesn't exist
- Email not found during login

---

### 409 Conflict - Duplicate Resource
**Status:** `409 Conflict`
```json
{
  "timestamp": "2026-04-07T12:00:00.000Z",
  "status": 409,
  "error": "Conflict",
  "message": "Email ya registrado: john@example.com",
  "path": "/usuarios"
}
```

**Common scenarios:**
- Email already registered
- Phone number already registered

---

### 401 Unauthorized - Invalid Credentials
**Status:** `401 Unauthorized`
```json
{
  "timestamp": "2026-04-07T12:00:00.000Z",
  "status": 401,
  "error": "Unauthorized",
  "message": "Credenciales invalidas",
  "path": "/usuarios/login"
}
```

**Common scenarios:**
- Wrong email or password during login

---

### 500 Internal Server Error - Unexpected Error
**Status:** `500 Internal Server Error`
```json
{
  "timestamp": "2026-04-07T12:00:00.000Z",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Error interno del servidor: [error details]",
  "path": "/usuarios"
}
```

---

## HTTP Status Codes Summary

| Method | Endpoint | Success Status | Error Status Codes |
|--------|----------|---------------|-------------------|
| GET | `/usuarios` | `200 OK` | `500` |
| GET | `/usuarios/{id}` | `200 OK` | `404`, `500` |
| POST | `/usuarios` | `201 Created` | `400`, `409`, `500` |
| POST | `/usuarios/login` | `200 OK` | `400`, `401`, `500` |
| PUT | `/usuarios/{id}` | `200 OK` | `400`, `404`, `409`, `500` |
| DELETE | `/usuarios/{id}` | `204 No Content` | `404`, `500` |
