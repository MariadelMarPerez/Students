
package controlador;
 
import modelo.ModeloStudent;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import vista.VistaStudent;

public class ControladorStudent {
    
    private final VistaStudent vista;


    public ControladorStudent (VistaStudent vista) {
        this.vista = vista;
        initControladores();
        cargarTabla();
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

        vista.btnAgregar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        vista.btnLimpiar.addActionListener(e -> limpiar());

        
    }

    private void insertar() {
        ModeloStudent e = new ModeloStudent(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.insertar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void actualizar() {
        ModeloStudent e = new ModeloStudent(
                Integer.parseInt(vista.txtCodigo.getText()),
                vista.txtNombre.getText()
        );
        if (e.actualizar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        ModeloStudent e = new ModeloStudent (cod, null);
        if (e.eliminar()) {
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.tabla.clearSelection();
    }

    private void cargarTabla() {
        List<ModeloStudent> lista = ModeloStudent.obtenerTodos();
        vista.modeloTabla.setRowCount(0);
        for (ModeloStudent e : lista) {
            vista.modeloTabla.addRow(new Object[]{e.getCodigo(), e.getNombre()});
        }
    }
}
