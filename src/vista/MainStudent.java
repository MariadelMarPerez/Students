package vista;

import controlador.ControladorStudent;
import controlador.ControladorTeacher;
import modelo.ConexionDataBase;

import javax.swing.*;
import java.awt.*;

public class MainStudent {

    public static void main(String[] args) {
        // Conexión a la base de datos
        ConexionDataBase.getConnection();

        // Crear la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión Académica");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // Crear barra de menú
            JMenuBar menuBar = new JMenuBar();
            JMenu menuGestion = new JMenu("GESTION ACADEMICA");

            JMenuItem itemEstudiantes = new JMenuItem("ESTUDIANTES");
            JMenuItem itemDocentes = new JMenuItem("DOCENTES");
            JMenuItem itemCursos = new JMenuItem("CURSOS");
            JMenuItem itemMatricula = new JMenuItem("MATRICULA");
            JMenuItem itemReporte = new JMenuItem("REPORTE");

            // Agregar ítems al menú
            menuGestion.add(itemEstudiantes);
            menuGestion.add(itemDocentes);
            menuGestion.add(itemCursos);
            menuGestion.add(itemMatricula);
            menuGestion.add(itemReporte);

            // Agregar menú a la barra
            menuBar.add(menuGestion);
            frame.setJMenuBar(menuBar);

            // Panel principal para cambiar vistas
            JPanel panelPrincipal = new JPanel(new BorderLayout());
            frame.setContentPane(panelPrincipal);

                // Acción al presionar "Estudiantes"
            itemEstudiantes.addActionListener(e -> {
                VistaStudent vistaStudent = new VistaStudent();
                new ControladorStudent(vistaStudent); 
                
                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaStudent, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
            });
            
            itemDocentes.addActionListener(e -> {
            VistaTeacher vistaTeacher = new VistaTeacher();
            new ControladorTeacher(vistaTeacher);

                    panelPrincipal.removeAll();
                    panelPrincipal.add(vistaTeacher, BorderLayout.CENTER);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
});
             // Mostrar ventana
            frame.setVisible(true);
        });
    }
}
            
                    
        
             
    
                
         
