package Clases;

import java.util.ArrayList;

public class CatalogoProductos {
    private  ArrayList<Producto> ListaProducto;

    public CatologoProductos(ArrayList<Producto> listaProducto) {
        ListaProducto = listaProducto;
    }

    private boolean existeProducto(int codigo){
        boolean existe = false;
        int i = 0;
        while (i < ListaProducto.size() && !existe){
            if(ListaProducto.get(i).getCodigo() == codigo){
                existe = true;
            }
            i++;
        }
        return existe;
    }

    public void Agregar(Producto producto){
        if(!existeProducto(producto.getCodigo())){
            ListaProducto.add(producto);
            //Si el producto no existe saltar error pop up
            //Falta reflejarlo en un archivo de texto, solo reescribir el archivo
            //aniadir un pop error up en el caso que producto tenga algun campo vacio
            //Aniadir pop up para confirmar que se agrege el producto
        }

    }
    public void Modificar(int codigo, String nombre,String descripcion, float precio){
        boolean encontre = false;
        int i = 0;
        if(existeProducto(codigo)){
           while (i < ListaProducto.size() && !encontre){
               if(ListaProducto.get(i).getCodigo() == codigo){
                   ListaProducto.get(i).setDescripcion(descripcion);
                   ListaProducto.get(i).setNombre(nombre);
                   ListaProducto.get(i).setPrecio(precio);
                   encontre = true;
               }
           }
        }
        //Si el producto no existe saltar error pop up
        //Falta reflejarlo en un archivo de texto, solo reescribir el archivo

    }
    public void Eliminar(int codigo){
        int i = 0;
        boolean soyEste = false;
        if(existeProducto(codigo)){
            while (i < ListaProducto.size() && !soyEste){
                if(ListaProducto.get(i).getCodigo() == codigo){
                    soyEste = true;
                }else{
                    i++;
                }
            }
            ListaProducto.remove(i);
        }
        //estaria bueno antes de confirmar borrar algo el pop up que muestra cual es el producto junto a un si o no para borrar
        //si el producto no existe saltar error pop up
        //Falta reflejarlo en un archivo de texto, solo reescribir el archivo
    }
}
