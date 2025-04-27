/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class ListaSE<T> implements ILista {

    private Nodo<T> inicio;

    public ListaSE() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }
    
    
//    public void agregarInicio(T dato) {
//        if (esVacia()) {
//            Nodo<T> nuevo = new Nodo();
//            nuevo.setDato(dato);
//            nuevo.setSiguiente(inicio);
//            inicio = nuevo;
//        }
//    }
//
//    @Override
//    public void agregarFinal(T dato) {
//    Nodo<T> nuevo = new Nodo<>();
//    nuevo.setDato(dato);
//    nuevo.setSiguiente(null);
//    if (esVacia()) {
//        inicio = nuevo;
//    } else {
//        Nodo<T> aux = inicio;
//        while (aux.getSiguiente() != null) {
//            aux = aux.getSiguiente();
//        }
//        aux.setSiguiente(nuevo);
//    }
//}

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }
    }

    @Override
    public void borrarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                inicio = null;
            } else {
                Nodo<T> aux = inicio;
                while (aux.getSiguiente() != null && aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
            }
        }
    }

    @Override
    public void vaciar() {
        inicio = null; // Eliminamos todos los nodo
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Agregar nuestra propia excepcion seria bueno
    @Override
    public T obtenerElemento(int indice) {
        Nodo<T> aux = inicio;
        int actual = 0;

        while (aux != null) {
            if (actual == indice) {
                return aux.getDato(); 
            } else {
                aux = aux.getSiguiente();
                actual++;
            }
        }

        // Si no se encontró el índice, lanzamos una excepción
        throw new PosFueraDeRangoException();
    }
    
//    @Override
//    public void insertarOrdenado(T dato) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public void agregarInicio(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarFinal(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertarOrdenado(Object dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}


