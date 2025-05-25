package vista;

import controlador.ControladorStudent;
import controlador.ControladorReport;
import controlador.ControladorMatriculation;
import controlador.ControladorCourse;
import controlador.ControladorTeacher;
import modelo.ConexionDataBase;

import javax.swing.*;
import java.awt.*;

public class MainStudent {

    public static void main(String[] args) {
        // Conexión a la base de datos
        ConexionDataBase.getConnection();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión Académica");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // Crear barra de menú
            JMenuBar menuBar = new JMenuBar();

            // Crear menús horizontales
            JMenu menuEstudiantes = new JMenu("ESTUDIANTES");
            JMenu menuDocentes = new JMenu("DOCENTES");
            JMenu menuCursos = new JMenu("CURSOS");
            JMenu menuMatricula = new JMenu("MATRICULA");
            JMenu menuReporte = new JMenu("REPORTE");

            // Agregar menús directamente a la barra
            menuBar.add(menuEstudiantes);
            menuBar.add(menuDocentes);
            menuBar.add(menuCursos);
            menuBar.add(menuMatricula);
            menuBar.add(menuReporte);

            frame.setJMenuBar(menuBar);

            // Panel principal para mostrar las vistas
            JPanel panelPrincipal = new JPanel(new BorderLayout());
            frame.setContentPane(panelPrincipal);

            // Acción al presionar "Estudiantes"
            menuEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    VistaStudent vistaStudent = new VistaStudent();
                    new ControladorStudent(vistaStudent);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaStudent, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });

            // Acción al presionar "Docentes"
            menuDocentes.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    VistaTeacher vistaTeacher = new VistaTeacher();
                    new ControladorTeacher(vistaTeacher);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaTeacher, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });

            // Acción al presionar "Cursos"
            menuCursos.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    VistaCourse vistaCourse = new VistaCourse();
                    new ControladorCourse(vistaCourse);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaCourse, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });

            // Acción al presionar "Matrícula"
            menuMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    VistaMatriculation vistaMatriculation = new VistaMatriculation();
                    new ControladorMatriculation(vistaMatriculation);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaMatriculation, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });

            // Acción al presionar "Reporte"
            menuReporte.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    VistaReport vistaReport = new VistaReport();
                    new ControladorReport(vistaReport);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaReport, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                }
            });

            // Mostrar ventana
            frame.setVisible(true);
        });
    }
}
