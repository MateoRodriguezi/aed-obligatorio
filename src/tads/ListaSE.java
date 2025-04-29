/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class ListaSE<T> implements ILista<T> {

    private Nodo<T> inicio;

    public ListaSE() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setDato(dato);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }

    @Override
    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setDato(dato);
        nuevo.setSiguiente(null);

        if (esVacia()) {
            inicio = nuevo;
        } else {
            Nodo<T> aux = inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }
    }

    @Override
    public void borrarFinal() {
        if (inicio.getSiguiente() == null) {
            inicio = null;
        } else {
            Nodo<T> aux = inicio;
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
        }
    }

    @Override
    public void vaciar() {
        inicio = null; // Eliminamos todos los nodos
    }

    @Override
    public void mostrar() {
        if (inicio != null) {
            Nodo aux = inicio;
            while (aux.getSiguiente() != null) {
                System.out.println(aux.getDato() + " ");
                aux = aux.getSiguiente();
            }
        }
    }

    @Override
    public T obtenerElemento(int indice) {
        Nodo<T> aux = inicio;
        int actual = 0;

        while (aux != null) {
            if (actual == indice) {
                return aux.getDato();
            }
            aux = aux.getSiguiente();
            actual++;
        }

        throw new IndexOutOfBoundsException("Índice " + indice + " fuera de rango.");
    }

    @Override
    public void insertarOrdenado(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
