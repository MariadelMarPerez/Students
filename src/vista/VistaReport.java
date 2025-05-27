package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaReport extends JPanel {

    public JTable tablaNotas;
    public DefaultTableModel modeloTabla;

    public VistaReport() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear modelo de tabla
        modeloTabla = new DefaultTableModel(
            new String[]{"Estudiante", "Curso", "Docente", "Nota"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Tabla con modelo y mejor apariencia
        tablaNotas = new JTable(modeloTabla);
        tablaNotas.setFillsViewportHeight(true);
        tablaNotas.setRowHeight(24);
        tablaNotas.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tablaNotas);
        scroll.setBorder(BorderFactory.createTitledBorder("Reporte de Notas"));

        add(scroll, BorderLayout.CENTER);
    }
}
