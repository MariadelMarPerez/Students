/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaReport extends JPanel {

    public JTable tablaNotas;
    public DefaultTableModel modeloTabla;

    public VistaReport() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(
            new String[]{"Estudiante", "Curso", "Docente", "Nota"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaNotas = new JTable(modeloTabla);
        add(new JScrollPane(tablaNotas), BorderLayout.CENTER);
    }
}
