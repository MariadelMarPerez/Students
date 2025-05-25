package controlador;

import modelo.ModeloCourse;
import modelo.ModeloTeacher;
import vista.VistaCourse;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ControladorCourse {

    private final VistaCourse vista;
    private List<ModeloTeacher> lista;

    public ControladorCourse(VistaCourse vista) {
        this.vista = vista;
        cargarDocentes();
        initControladores();
        cargarTablaCourse();
    }

    private void cargarDocentes() {
        vista.cbDocente.removeAllItems();
        lista = ModeloTeacher.ObtenerTodosTeacher();
        for (ModeloTeacher d : lista) {
            vista.cbDocente.addItem(d.getNombre());
        }
    }

    private void initControladores() {
        vista.tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tabla.getSelectedRow();
                if (fila != -1) {
                    vista.txtCodigo.setText(vista.modeloTabla.getValueAt(fila, 0).toString());
                    vista.txtNombre.setText(vista.modeloTabla.getValueAt(fila, 1).toString());
                    vista.cbDocente.setSelectedItem(vista.modeloTabla.getValueAt(fila, 2).toString());
                }
            }
        });

        vista.ButtonAgregar.addActionListener(e -> insertarCourse());
        vista.ButtonActualizar.addActionListener(e -> actualizarCourse());
        vista.ButtonEliminar.addActionListener(e -> eliminarCourse());
        vista.ButtonLimpiar.addActionListener(e -> limpiarFormulario());
    }

    private void insertarCourse() {
        try {
            int cod = Integer.parseInt(vista.txtCodigo.getText());
            String nombre = vista.txtNombre.getText();
            int codDoc = lista.get(vista.cbDocente.getSelectedIndex()).getCodigo();

            ModeloCourse c = new ModeloCourse(cod, nombre, codDoc);
            if (c.insertarCourse()) {
                JOptionPane.showMessageDialog(null, "Curso insertado correctamente.");
                cargarTablaCourse();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el curso.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos inválidos o campos vacíos: " + ex.getMessage());
        }
    }

    private void actualizarCourse() {
        try {
            int cod = Integer.parseInt(vista.txtCodigo.getText());
            String nombre = vista.txtNombre.getText();
            int codDoc = lista.get(vista.cbDocente.getSelectedIndex()).getCodigo();

            ModeloCourse c = new ModeloCourse(cod, nombre, codDoc);
            if (c.actualizarCourse()) {
                JOptionPane.showMessageDialog(null, "Curso actualizado correctamente.");
                cargarTablaCourse();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el curso.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos inválidos o campos vacíos: " + ex.getMessage());
        }
    }

    private void eliminarCourse() {
        try {
            int cod = Integer.parseInt(vista.txtCodigo.getText());
            ModeloCourse c = new ModeloCourse();
            c.setCodCurso(cod);
            if (c.eliminarCourse()) {
                JOptionPane.showMessageDialog(null, "Curso eliminado correctamente.");
                cargarTablaCourse();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el curso.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.cbDocente.setSelectedIndex(0);
        vista.tabla.clearSelection();
    }

    private void cargarTablaCourse() {
        vista.modeloTabla.setRowCount(0);
        for (ModeloCourse c : ModeloCourse.obtenerTodosCourse()) {
            String nombreDocente = lista.stream()
                    .filter(d -> d.getCodigo() == c.getCodDocente())
                    .findFirst()
                    .map(ModeloTeacher::getNombre)
                    .orElse("Desconocido");
            vista.modeloTabla.addRow(new Object[]{c.getCodCurso(), c.getNomCurso(), nombreDocente});
        }
    }
}
