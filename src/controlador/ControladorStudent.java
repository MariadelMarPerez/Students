package controlador;

import modelo.ModeloStudent;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import vista.VistaStudent;

public class ControladorStudent {
    
    private final VistaStudent vista;

    public ControladorStudent(VistaStudent vista) {
        this.vista = vista;
        configurarEventos();
        refrescarTabla();
    }

    private void configurarEventos() {
        vista.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    String codigo = vista.tabla.getValueAt(fila, 0).toString();
                    String nombre = vista.tabla.getValueAt(fila, 1).toString();

                    vista.txtCodigo.setText(codigo);
                    vista.txtNombre.setText(nombre);
                }
            }
        });

        vista.btnAgregar.addActionListener(e -> registrarEstudiante());
        vista.btnActualizar.addActionListener(e -> editarEstudiante());
        vista.btnEliminar.addActionListener(e -> borrarEstudiante());
        vista.btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    private void registrarEstudiante() {
        ModeloStudent estudiante = new ModeloStudent(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (estudiante.guardarEstudiante()) {
            refrescarTabla();
            limpiarFormulario();
        }
    }

    private void editarEstudiante() {
        ModeloStudent estudiante = new ModeloStudent(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (estudiante.modificarEstudiante()) {
            refrescarTabla();
            limpiarFormulario();
        }
    }

    private void borrarEstudiante() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        ModeloStudent estudiante = new ModeloStudent(cod, null);
        if (estudiante.removerEstudiante()) {
            refrescarTabla();
            limpiarFormulario();
        }
    }

    private void limpiarFormulario() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.tabla.clearSelection();
    }

    private void refrescarTabla() {
        List<ModeloStudent> lista = ModeloStudent.listarEstudiantes();
        vista.modeloTabla.setRowCount(0);
        for (ModeloStudent e : lista) {
            vista.modeloTabla.addRow(new Object[]{e.getCodigo(), e.getNombre()});
        }
    }
}
