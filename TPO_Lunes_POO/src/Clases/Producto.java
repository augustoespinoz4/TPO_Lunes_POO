package Clases;

public abstract class Producto  {
    protected int codigo;
    protected String nombre;
    protected String descricion;
    protected float precio;

    public Producto(int codigo, String nombre, String descricion, float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descricion = descricion;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descricion='" + descricion + '\'' +
                ", precio=" + precio +
                '}';
    }

    public abstract String imprimirTipo();

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescricion() {
        return descricion;
    }

    public float getPrecio() {
        return precio;
    }
}
