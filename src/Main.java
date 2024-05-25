import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cliente c1 = new Cliente("sdg","dagg","adgdggad34");
        ArrayList<Producto> pro = new ArrayList<Producto>();
        Carrito ca1 = new Carrito(pro , c1);
        Producto p1 = new Comida(123,"T","nada", 45,"2/3/2023",3.6F);
        Producto p2 = new Comida(124,"r","nada", 25,"2/3/2023",2.6F);
        ca1.agregarIteam(p1);
        ca1.agregarIteam(p2);
        System.out.println(ca1.imprimirCarrito());
        ca1.eleminarIteam(p2);
        System.out.println(ca1.imprimirCarrito());
        ca1.borrarListaCarrito();
        System.out.println(ca1.imprimirCarrito());
    }
}