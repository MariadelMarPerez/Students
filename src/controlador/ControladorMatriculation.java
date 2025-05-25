package controlador;

import modelo.*;
import vista.VistaMatriculation;

import java.awt.event.*;
import java.util.List;

public class ControladorMatriculation {

    private final VistaMatriculation vista;
    private List<ModeloStudent> estudiantes;
    private List<ModeloCourse> cursos;

    public ControladorMatriculation(VistaMatriculation vista) {
        this.vista = vista;
        cargarCombos();
        cargarTabla();
        configurarEventos();
    }

    private void cargarCombos() {
        estudiantes = ModeloStudent.listarEstudiantes();
        cursos = ModeloCourse.obtenerTodosCourse();

        for (ModeloStudent est : estudiantes) {
            vista.cbEstudiantes.addItem(est.getNombre());
        }

        for (ModeloCourse curso : cursos) {
            vista.cbCursos.addItem(curso.getNomCurso());
        }
    }

    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (ModeloMatriculation m : ModeloMatriculation.listarMatriculas()) {
            vista.modeloTabla.addRow(new Object[]{
                ModeloMatriculation.obtenerNombreEstudiante(m.getCodEstudiante()),
                ModeloMatriculation.obtenerNombreCurso(m.getCodCurso()),
                m.getNotaCurso()
            });
        }
    }

    private void configurarEventos() {
        vista.tablaMatriculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.tablaMatriculas.getSelectedRow();
                if (fila != -1) {
                    vista.cbEstudiantes.setSelectedItem(vista.tablaMatriculas.getValueAt(fila, 0).toString());
                    vista.cbCursos.setSelectedItem(vista.tablaMatriculas.getValueAt(fila, 1).toString());
                    vista.txtNota.setText(vista.tablaMatriculas.getValueAt(fila, 2).toString());
                }
            }
        });

        vista.btnRegistrar.addActionListener(e -> {
            try {
                int codEst = estudiantes.get(vista.cbEstudiantes.getSelectedIndex()).getCodigo();
                int codCurso = cursos.get(vista.cbCursos.getSelectedIndex()).getCodCurso();
                double nota = Double.parseDouble(vista.txtNota.getText());

                ModeloMatriculation matricula = new ModeloMatriculation(codEst, codCurso, nota);
                if (matricula.registrarMatricula()) {
                    cargarTabla();
                    vista.txtNota.setText("");
                }
            } catch (Exception ex) {
                System.err.println("Error al registrar matrícula: " + ex.getMessage());
            }
        });
    }
}
