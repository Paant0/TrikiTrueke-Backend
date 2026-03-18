package com.example.BackEnd.TrikiTrueke_BackEnd.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Usuarios")
public class UsuarioDTO {
    @Id
    private String id;
    private String nombre;
    private String clave;
    private String email;
    private String telefono;
    private Date creadoEn;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nombre, String clave, String email, String telefono, Date creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.telefono = telefono;
        this.creadoEn = creadoEn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
