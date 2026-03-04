import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String contraseña;
    private String email;
    private String tel;
    private Date creaadoEn;
    // Getters y Setters


    public Usuario() {
    }

    // 🔹 Constructor parametrizado
    public Usuario(String id, String nombre, String contraseña, String email, String tel, Date creaadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
        this.tel = tel;
        this.creaadoEn = creaadoEn;
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
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
        return creaadoEn;
    }

    public void setCreaadoEn(Date creaadoEn) {
        this.creaadoEn = creaadoEn;
    }


}
    }
}