package com.example.BackEnd.TrikiTrueke_BackEnd.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Intercambios")
public class IntercambioDTO {
    @Id
    private String id;
    private String estado;
    private ObjectId articuloOfrecido;
    private ObjectId articuloRecibido;
    private ObjectId usuarioOfrece;
    private ObjectId usuarioRecibe;
    private Date creadoEn;

    public IntercambioDTO() {
    }

    public IntercambioDTO(String id, String estado, ObjectId articuloOfrecido, ObjectId articuloRecibido, ObjectId usuarioOfrece, ObjectId usuarioRecibe, Date creadoEn) {
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

    public ObjectId getArticuloOfrecido() {
        return articuloOfrecido;
    }

    public void setArticuloOfrecido(ObjectId articuloOfrecido) {
        this.articuloOfrecido = articuloOfrecido;
    }

    public ObjectId getArticuloRecibido() {
        return articuloRecibido;
    }

    public void setArticuloRecibido(ObjectId articuloRecibido) {
        this.articuloRecibido = articuloRecibido;
    }

    public ObjectId getUsuarioOfrece() {
        return usuarioOfrece;
    }

    public void setUsuarioOfrece(ObjectId usuarioOfrece) {
        this.usuarioOfrece = usuarioOfrece;
    }

    public ObjectId getUsuarioRecibe() {
        return usuarioRecibe;
    }

    public void setUsuarioRecibe(ObjectId usuarioRecibe) {
        this.usuarioRecibe = usuarioRecibe;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
