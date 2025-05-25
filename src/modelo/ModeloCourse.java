package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloCourse {

    private int codCurso;
    private String nomCurso;
    private int codDocente;

    public ModeloCourse() {}

    public ModeloCourse(int codCurso, String nomCurso, int codDocente) {
        this.codCurso = codCurso;
        this.nomCurso = nomCurso;
        this.codDocente = codDocente;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(int codDocente) {
        this.codDocente = codDocente;
    }

    public boolean insertarCourse() {
        String sql = "INSERT INTO cursos (cod_curso, nom_curso, cod_docente) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setInt(1, codCurso);
            ps.setString(2, nomCurso);
            ps.setInt(3, codDocente);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCourse() {
        String sql = "UPDATE cursos SET nom_curso = ?, cod_docente = ? WHERE cod_curso = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setString(1, nomCurso);
            ps.setInt(2, codDocente);
            ps.setInt(3, codCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCourse() {
        String sql = "DELETE FROM cursos WHERE cod_curso = ?";
        try (PreparedStatement ps = ConexionDataBase.getConnection().prepareStatement(sql)) {
            ps.setInt(1, codCurso);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }

    public static List<ModeloCourse> obtenerTodosCourse() {
        List<ModeloCourse> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Statement st = ConexionDataBase.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ModeloCourse c = new ModeloCourse(
                    rs.getInt("cod_curso"),
                    rs.getString("nom_curso"),
                    rs.getInt("cod_docente")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener cursos: " + e.getMessage());
        }
        return lista;
    }
}
