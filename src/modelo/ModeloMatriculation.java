package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloMatriculation {
    private int codEstudiante;
    private int codCurso;
    private double notaCurso;

    public ModeloMatriculation() {}

    public ModeloMatriculation(int codEstudiante, int codCurso, double notaCurso) {
        this.codEstudiante = codEstudiante;
        this.codCurso = codCurso;
        this.notaCurso = notaCurso;
    }

    public int getCodEstudiante() {
        return codEstudiante;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public double getNotaCurso() {
        return notaCurso;
    }

    public boolean registrarMatricula() {
        String sql = "INSERT INTO matricula VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setInt(1, codEstudiante);
            ps.setInt(2, codCurso);
            ps.setDouble(3, notaCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar matrícula: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloMatriculation> listarMatriculas() {
        List<ModeloMatriculation> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula";
        try (Statement st = ConexionDataBase.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ModeloMatriculation(
                        rs.getInt("cod_estudiante"),
                        rs.getInt("cod_curso"),
                        rs.getDouble("nota_curso")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar matrículas: " + e.getMessage());
        }
        return lista;
    }

    public static String obtenerNombreEstudiante(int codEstudiante) {
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(
                "SELECT nom_estudiante FROM estudiantes WHERE cod_estudiante = ?")) {
            ps.setInt(1, codEstudiante);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre de estudiante: " + e.getMessage());
        }
        return "";
    }

    public static String obtenerNombreCurso(int codCurso) {
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(
                "SELECT nom_curso FROM cursos WHERE cod_curso = ?")) {
            ps.setInt(1, codCurso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString(1);
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre de curso: " + e.getMessage());
        }
        return "";
    }
}
