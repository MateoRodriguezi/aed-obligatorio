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

    public Nodo getInicio() {
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
    public String mostrar() {
        Nodo<T> mostrar = inicio;
        String res = "";
        while (mostrar != null) {
            res += mostrar.getDato();  // usa toString del dato
            if (mostrar.getSiguiente() != null) {
                res += "#";  // agrega # si no es el último
            }
            mostrar = mostrar.getSiguiente();
        }
        return res;
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
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setDato(dato);

        if (esVacia() || dato.compareTo(inicio.getDato()) < 0) {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        } else {
            Nodo<T> aux = inicio;
            while (aux.getSiguiente() != null && dato.compareTo(aux.getSiguiente().getDato()) > 0) {
                aux = aux.getSiguiente();
            }

            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }

        size++;
    }

    @Override
    public int cantidadElementos() {
        return size;
    }
}
