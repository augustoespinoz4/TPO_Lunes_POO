package Clases;

public final class Electronico extends  Producto{

    private int garantia;

    public Electronico(int codigo, String nombre, String descricion, float precio, int garantia) {
        super(codigo, nombre, descricion, precio);
        this.garantia = garantia;
    }

    public String imprimirTipo() {
        return "Eletronico";
    }

    @Override
    public String toString() {
        return "Electronico{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descricion='" + descricion + '\'' +
                ", precio=" + precio +
                ", garantia=" + garantia +
                '}';
    }
    public int getGarantia() {
        return garantia;
    }
}
