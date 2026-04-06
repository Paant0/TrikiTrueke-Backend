package com.example.BackEnd.TrikiTrueke_BackEnd.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Intercambios")
public class IntercambioDTO {
    @Id
    private String id;
    private String estado;
    private String articuloOfrecido;
    private String articuloRecibido;
    private String usuarioOfrece;
    private String usuarioRecibe;
    private Date creadoEn;

    public IntercambioDTO() {
    }

    public IntercambioDTO(String id, String estado, String articuloOfrecido, String articuloRecibido, String usuarioOfrece, String usuarioRecibe, Date creadoEn) {
        this.id = id;
        this.estado = estado;
        this.articuloOfrecido = articuloOfrecido;
        this.articuloRecibido = articuloRecibido;
        this.usuarioOfrece = usuarioOfrece;
        this.usuarioRecibe = usuarioRecibe;
        this.creadoEn = creadoEn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArticuloOfrecido() {
        return articuloOfrecido;
    }

    public void setArticuloOfrecido(String articuloOfrecido) {
        this.articuloOfrecido = articuloOfrecido;
    }

    public String getArticuloRecibido() {
        return articuloRecibido;
    }

    public void setArticuloRecibido(String articuloRecibido) {
        this.articuloRecibido = articuloRecibido;
    }

    public String getUsuarioOfrece() {
        return usuarioOfrece;
    }

    public void setUsuarioOfrece(String usuarioOfrece) {
        this.usuarioOfrece = usuarioOfrece;
    }

    public String getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(String usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
