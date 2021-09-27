
package restful.Model;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioModel {
    private int cod_usuario;
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private String sexo;
    private int cod_nacionalidad;    
    
    
    public UsuarioModel() {
    }

    public UsuarioModel(int cod_usuario, String nombres, String apellidos, String email, String password, String sexo, int cod_nacionalidad) {
        this.cod_usuario = cod_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.sexo = sexo;
        this.cod_nacionalidad = cod_nacionalidad;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
   
    public int getCod_nacionalidad() {
        return cod_nacionalidad;
    }

    public void setCod_nacionalidad(int cod_nacionalidad) {
        this.cod_nacionalidad = cod_nacionalidad; 
    }
}
