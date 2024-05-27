package Clases;

public abstract class Producto  {
    protected int codigo;
    protected String nombre;
    protected String descripcion;
    protected float precio;

    public Producto(int codigo, String nombre, String descripcion, float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descricion='" + descripcion + '\'' +
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
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
