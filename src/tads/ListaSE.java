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

    private Nodo inicio;

    public ListaSE() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(int n) {
        if (esVacia()) {
            Nodo nuevo = new Nodo();
            nuevo.setDato(n);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }

    @Override
    public void agregarFinal(int elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }
    }

    @Override
    public void borrarFinal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public int obtenerElemento(int indice) {
        Nodo aux = inicio;
        int actual = 0;

        while (aux != null) {
            if (actual == indice) {
                return aux.getDato(); // Asumimos que getDato() devuelve int
            } else {
                aux = aux.getSiguiente();
                actual++;
            }
        }

        // Si no se encontró el índice, lanzamos una excepción
        throw new PosFueraDeRangoException();
    }

    @Override
    public void insertarOrdenado(int dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
