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
    private String tel;
    private Date creadoEn;
    // Getters y Setters


    public UsuarioDTO() {
    }

    // 🔹 Constructor parametrizado
    public UsuarioDTO(String id, String nombre, String clave, String email, String tel, Date creadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.tel = tel;
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

    public String getContraseña() {
        return clave;
    }

    public void setContraseña(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreaadoEn() {
        return creadoEn;
    }

    public void setCreaadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }


}