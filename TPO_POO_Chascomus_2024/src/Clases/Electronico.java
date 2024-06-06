package Clases;

public class Electronico extends Producto {

    private int garantia;

    public Electronico(int codigo, String nombre, String descripcion, double precio, int garantia) {
        super(codigo, nombre, descripcion, precio);
        this.garantia = garantia;
    }

    @Override
    public String getTipo() {
        return "Electronico";
    }

    @Override
    public String toString() {
        return "Electronico;" + codigo + ";" + nombre + ";" + descripcion + ";" + precio + ";" + garantia + "\n";
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }
}
