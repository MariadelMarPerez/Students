package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloStudent extends ModeloPerson {

    public ModeloStudent() {}

    public ModeloStudent(int codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public String getTipo() {
        return "Estudiante";
    }

    public boolean guardarEstudiante() {
        String sql = "{CALL insertar_estudiante(?,?)}";
        try {
            CallableStatement cs = ConexionDataBase.getConnection().prepareCall(sql);
            cs.setInt(1, getCodigo());
            cs.setString(2, getNombre());
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarEstudiante() {
        String sql = "UPDATE estudiantes SET nom_estudiante = ? WHERE cod_estudiante = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setString(1, getNombre());
            ps.setInt(2, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean removerEstudiante() {
        String sql = "DELETE FROM estudiantes WHERE cod_estudiante = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setInt(1, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al remover estudiante: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloStudent> listarEstudiantes() {
        List<ModeloStudent> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Statement st = ConexionDataBase.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ModeloStudent(rs.getInt("cod_estudiante"), rs.getString("nom_estudiante")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes: " + e.getMessage());
        }
        return lista;
    }
}
