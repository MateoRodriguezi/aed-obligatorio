/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class ListaSE<T extends Comparable> implements ILista<T> {

    private Nodo<T> inicio;
    private int size = 0;

    public ListaSE() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    private Nodo getInicio() {
        return inicio;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setDato(dato);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        size++;
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
        size++;

    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }

        size--;

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

        size--;

    }

    @Override
    public void vaciar() {
        inicio = null; // Eliminamos todos los nodos
        size = 0;
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
    public T obtenerElemento(T elemento) {
        Nodo aux = inicio;
        int cont = 0;

        while (aux != null && cont <= size) {
            if (aux.getDato().equals(elemento)) {
                return (T) aux.getDato();
            }
            cont++;
            aux = aux.getSiguiente();
        }
        return null;
    }

// Nico agregue este metodo a ILista chusmealo y decime que te parece
    @Override
    public void eliminarElemento(T elemento) {
        //Evaluo el primero 

        if (inicio != null) {

            if (inicio.getDato() == elemento) {
                inicio = inicio.getSiguiente();
            } else {
                Nodo actual = inicio;
                while (actual.getSiguiente() != null && actual.getSiguiente().getDato().equals(elemento)) {
                    actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    Nodo aBorrar = actual.getSiguiente();
                    actual.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);

                }
            }
        }
    }

    @Override
    public void insertarOrdenado(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantidadElementos() {
        return size;
    }
}
