package com.example.BackEnd.TrikiTrueke_BackEnd.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Intercambios")
public class IntercambioDTO {
    @Id
    private String id;
    private String estado;
    private ArticuloDTO articuloOfrecido;
    private ArticuloDTO articuloRecibido;
    private UsuarioDTO usuarioOfrece;
    private UsuarioDTO usuarioRecibe;
    private Date creadoEn;

    public IntercambioDTO() {
    }

    public IntercambioDTO(String id, String estado, ArticuloDTO articuloOfrecido, ArticuloDTO articuloRecibido, UsuarioDTO usuarioOfrece, UsuarioDTO usuarioRecibe, Date creadoEn) {
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

    public ArticuloDTO getArticuloOfrecido() {
        return articuloOfrecido;
    }

    public void setArticuloOfrecido(ArticuloDTO articuloOfrecido) {
        this.articuloOfrecido = articuloOfrecido;
    }

    public ArticuloDTO getArticuloRecibido() {
        return articuloRecibido;
    }

    public void setArticuloRecibido(ArticuloDTO articuloRecibido) {
        this.articuloRecibido = articuloRecibido;
    }

    public UsuarioDTO getUsuarioOfrece() {
        return usuarioOfrece;
    }

    public void setUsuarioOfrece(UsuarioDTO usuarioOfrece) {
        this.usuarioOfrece = usuarioOfrece;
    }

    public UsuarioDTO getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(UsuarioDTO usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}