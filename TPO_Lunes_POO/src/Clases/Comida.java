package Clases;

public final class Comida extends Producto {
    private String fechaVencimiento;
    private float peso;

    public Comida(int codigo, String nombre, String descripcion, float precio, String fechaVencimiento, float peso) {
        super(codigo, nombre, descripcion, precio);
        this.fechaVencimiento = fechaVencimiento;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return codigo + "," + nombre + "," + descripcion + "," + precio + "," + fechaVencimiento + "," + peso;
    }

    public String imprimirTipo() {
        return "Comida";
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public float getPeso() {
        return peso;
    }
}
