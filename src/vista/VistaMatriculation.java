package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaMatriculation extends JPanel {

    public JComboBox<String> cbEstudiantes, cbCursos;
    public JTextField txtNota;
    public JButton btnRegistrar;
    public JTable tablaMatriculas;
    public DefaultTableModel modeloTabla;

    public VistaMatriculation() {
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Matrícula"));

        panelFormulario.add(new JLabel("Estudiante:"));
        cbEstudiantes = new JComboBox<>();
        panelFormulario.add(cbEstudiantes);

        panelFormulario.add(new JLabel("Curso:"));
        cbCursos = new JComboBox<>();
        panelFormulario.add(cbCursos);

        panelFormulario.add(new JLabel("Nota:"));
        txtNota = new JTextField();
        panelFormulario.add(txtNota);

        panelFormulario.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar Matrícula");
        panelFormulario.add(btnRegistrar);

        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "Curso", "Nota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaMatriculas = new JTable(modeloTabla);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaMatriculas), BorderLayout.CENTER);
    }
}
