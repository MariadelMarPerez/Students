/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
  import modelo.ModeloReport;
import vista.VistaReport;

import java.util.List;


public class ControladorReport {

    private final VistaReport vista;

    public ControladorReport(VistaReport vista) {
        this.vista = vista;
        cargarInformacionNotas();
    }

    private void cargarInformacionNotas() {
        List<String[]> datos = ModeloReport.consultarNotas();
        vista.modeloTabla.setRowCount(0);
        for (String[] fila : datos) {
            vista.modeloTabla.addRow(fila);
    }
}
}


