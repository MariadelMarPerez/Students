package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaCourse extends JPanel {

    public JTextField txtCodigo, txtNombre;
    public JComboBox<String> cbDocente;
    public JButton ButtonAgregar, ButtonActualizar, ButtonEliminar, ButtonLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public VistaCourse() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel principal superior
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder("Gestión de Cursos"));

        // Panel del formulario con mejor disposición
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(15);
        panelFormulario.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelFormulario.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Docente:"), gbc);
        gbc.gridx = 1;
        cbDocente = new JComboBox<>();
        cbDocente.setPreferredSize(new Dimension(150, 25));
        panelFormulario.add(cbDocente, gbc);

        panelPrincipal.add(panelFormulario);

        // Panel de botones con diseño más limpio
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        ButtonAgregar = new JButton("Agregar");
        ButtonActualizar = new JButton("Actualizar");
        ButtonEliminar = new JButton("Eliminar");
        ButtonLimpiar = new JButton("Limpiar");

        panelBotones.add(ButtonAgregar);
        panelBotones.add(ButtonActualizar);
        panelBotones.add(ButtonEliminar);
        panelBotones.add(ButtonLimpiar);

        panelPrincipal.add(panelBotones);

        // Agregar panel principal al norte
        add(panelPrincipal, BorderLayout.NORTH);

        // Tabla de cursos
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Docente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Cursos"));

        add(scroll, BorderLayout.CENTER);
    }
}
