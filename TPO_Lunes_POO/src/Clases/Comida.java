public final class Comida extends Producto {
    private String fechaVencimiento;
    private float peso;

    public Comida(int codigo, String nombre, String descricion, float precio, String fechaVencimiento, float peso) {
        super(codigo, nombre, descricion, precio);
        this.fechaVencimiento = fechaVencimiento;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descricion='" + descricion + '\'' +
                ", precio=" + precio +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", peso=" + peso +
                '}';
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
