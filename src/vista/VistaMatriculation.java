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
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de formulario con GridBagLayout
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Matrícula"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Estudiante:"), gbc);
        gbc.gridx = 1;
        cbEstudiantes = new JComboBox<>();
        cbEstudiantes.setPreferredSize(new Dimension(200, 25));
        panelFormulario.add(cbEstudiantes, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Curso:"), gbc);
        gbc.gridx = 1;
        cbCursos = new JComboBox<>();
        cbCursos.setPreferredSize(new Dimension(200, 25));
        panelFormulario.add(cbCursos, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Nota:"), gbc);
        gbc.gridx = 1;
        txtNota = new JTextField(10);
        panelFormulario.add(txtNota, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnRegistrar = new JButton("Registrar Matrícula");
        panelFormulario.add(btnRegistrar, gbc);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla de matrículas
        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "Curso", "Nota"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaMatriculas = new JTable(modeloTabla);
        tablaMatriculas.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tablaMatriculas);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Matrículas"));

        add(scroll, BorderLayout.CENTER);
    }
}
