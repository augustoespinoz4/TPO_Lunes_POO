package Clases;

public final class Electronico extends  Producto{

    private int garantia;

    public Electronico(int codigo, String nombre, String descripcion, float precio, int garantia) {
        super(codigo, nombre, descripcion, precio);
        this.garantia = garantia;
    }

    public String imprimirTipo() {
        return "Eletronico";
    }

    @Override
    public String toString() {
        return "Electronico," + codigo + "," + nombre + "," + descripcion + "," + precio + "," + garantia;
    }
    public int getGarantia() {
        return garantia;
    }
}
