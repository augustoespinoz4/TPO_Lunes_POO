import java.util.ArrayList;

public class Carrito {
    private ArrayList<Producto> productos;
    private Cliente cliente;
    private int cantidad;
    private float precioTotal;

    public Carrito(ArrayList<Producto> productos , Cliente cliente) {
        this.productos = productos;
        this.cantidad = 0;
        this.precioTotal = 0;
    }
    public void agregarIteam(Producto producto){
        this.cantidad++;
        this.precioTotal+=producto.getPrecio();
        productos.add(producto);
    }
    public void eleminarIteam(Producto producto){
        this.cantidad--;
        this.precioTotal-=producto.getPrecio();
        int i=0;
        boolean borrado=false;
        while (i < productos.size() && !borrado ){
            if (producto.getCodigo() == productos.get(i).getCodigo()){
                productos.remove(i);
                borrado=true;
            }
            i++;
        }
    }
    public void borrarListaCarrito(){ // BONUS PUNTO
        this.cantidad=0;
        this.precioTotal=0;
        this.productos.clear();
    }
    public String imprimirCarrito(){ // VER A FUTURO
        String datos="";
        for (int i =0;i < productos.size();i++){
            datos+=productos.get(i).toString()+"\n";
        }
        return datos;
    }
}
