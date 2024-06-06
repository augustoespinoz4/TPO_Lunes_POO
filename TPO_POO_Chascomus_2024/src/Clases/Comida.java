package Clases;

public class Comida extends Producto {

    private String fechaVencimiento;
    private double peso;

    public Comida(int codigo, String nombre, String descripcion, double precio, String fechaVencimiento, double peso) {
        super(codigo, nombre, descripcion, precio);
        this.fechaVencimiento = fechaVencimiento;
        this.peso = peso;
    }

    @Override
    public String getTipo() {
        return "Comida";
    }

    @Override
    public String toString() {
        return "Comida;" + codigo + ";" + nombre + ";" + descripcion + ";" + precio + ";" + fechaVencimiento + ";" + peso + "\n";
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
