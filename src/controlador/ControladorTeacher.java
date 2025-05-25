/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.ModeloTeacher;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import vista.VistaTeacher;

public class ControladorTeacher {
   
    
    private final VistaTeacher vista;


    public ControladorTeacher (VistaTeacher vista) {
        this.vista = vista;
        initControladores();
        cargarTablaTeacher();
    }

    private void initControladores() {
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

        vista.btnAgregar.addActionListener(e -> insertarTeacher());
        vista.btnActualizar.addActionListener(e -> actualizarTeacher());
        vista.btnEliminar.addActionListener(e -> eliminarTeacher());
        vista.btnLimpiar.addActionListener(e -> limpiarTeacher());

        
    }

    private void insertarTeacher() {
        ModeloTeacher e = new ModeloTeacher(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.insertarTeacher()) {
            cargarTablaTeacher();
            limpiarTeacher();
        }
    }

    private void actualizarTeacher() {
        ModeloTeacher e = new ModeloTeacher(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.actualizarTeacher()) {
            cargarTablaTeacher();
            limpiarTeacher();
        }
    }

    private void eliminarTeacher() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        ModeloTeacher e = new ModeloTeacher (cod, null);
        if (e.eliminarTeacher()) {
            cargarTablaTeacher();
            limpiarTeacher();
        }
    }

    private void limpiarTeacher() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.tabla.clearSelection();
    }

    private void cargarTablaTeacher() {
        List<ModeloTeacher> lista = ModeloTeacher.ObtenerTodosTeacher();
        vista.modeloTabla.setRowCount(0);
        for (ModeloTeacher e : lista) {
            vista.modeloTabla.addRow(new Object[]{e.getCodigo(), e.getNombre()});
        }
    }
}
    

