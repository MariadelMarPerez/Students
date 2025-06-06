package modelo;

public abstract class ModeloPerson {

    private int codigo;
    private String nombre;

    public ModeloPerson() {
    }

    public ModeloPerson(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre;
    }
}