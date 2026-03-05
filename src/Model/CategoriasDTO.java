import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Articulos")
public class Articulo {

    @Id
    private String id;

    private String titulo;
    private String descripcion;
    private String usuarioId;
    private String categoriaId;
    private List<String> fotos;
    private String estado;
    private Date creadoEn;

    // 🔹 Constructor vacío
    public Articulo() {
    }

    // 🔹 Constructor parametrizado
    public Articulo(String id, String titulo, String descripcion, String usuarioId,
                    String categoriaId, List<String> fotos, String estado, Date creadoEn) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.fotos = fotos;
        this.estado = estado;
        this.creadoEn = creadoEn;
    }

    // 🔹 Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}