package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.UsuarioModel;
import restful.Model.Conexion;

public class UsuarioService {

    public ArrayList<UsuarioModel> getUsuarios() {
        ArrayList<UsuarioModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM usuario";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setCod_usuario(rs.getInt("cod_usuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setCod_nacionalidad(rs.getInt("nacionalidad"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public UsuarioModel getUsuario(int id) {
        UsuarioModel usuario = new UsuarioModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM usuario WHERE cod_usuario = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                usuario.setCod_usuario(rs.getInt("cod_usuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setCod_nacionalidad(rs.getInt("nacionalidad"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuario;
    }

    public UsuarioModel addUsuario(UsuarioModel usuario) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO usuario(cod_usuario,nombres,apellidos,email,password)";
        Sql = Sql + "values (?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, usuario.getCod_usuario());
            pstm.setString(2, usuario.getNombres());
            pstm.setString(3, usuario.getApellidos());
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getPassword());
            pstm.setString(6, usuario.getSexo());
            pstm.setInt(7, usuario.getCod_nacionalidad());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return usuario;
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario) {
        Conexion conn = new Conexion();
        String sql = "UPDATE usuario SET nombres=?,email=?,apellidos=?,password=? WHERE cod_usuario= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, usuario.getCod_usuario());
            pstm.setString(2, usuario.getNombres());
            pstm.setString(3, usuario.getApellidos());
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getPassword());
            pstm.setString(6, usuario.getSexo());
            pstm.setInt(7, usuario.getCod_nacionalidad());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return usuario;
    }

    public String delUsuario(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM usuario WHERE cod_usuario= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
}
