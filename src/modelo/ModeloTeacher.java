/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloTeacher extends ModeloPerson {


    public ModeloTeacher (int codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public String getTipo() {
        return "Docente";
    }

   public boolean insertarTeacher() {
        String sql = "{CALL insertar_docentes(?,?)}"; // Proceso Almacenado
        try {
            CallableStatement cs = ConexionDataBase.getConnection().prepareCall(sql);
            cs.setInt(1, getCodigo());
            cs.setString(2, getNombre());
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar  el docente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarTeacher() {
        String sql = "UPDATE docentes SET nom_docente = ? WHERE cod_docente = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setString(1, getNombre());
            ps.setInt(2, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el docente : " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarTeacher() {
        String sql = "DELETE FROM docentes WHERE cod_docente = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setInt(1, getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el docente: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloTeacher> ObtenerTodosTeacher() {
        List<ModeloTeacher> lista = new ArrayList<>();
        String sql = "SELECT * FROM docentes";
        try (Statement st = ConexionDataBase.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ModeloTeacher(rs.getInt("Cod_Docente"), rs.getString("Nom_Docente")));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el docente: " + e.getMessage());
        }
        return lista;
    }
}
    

