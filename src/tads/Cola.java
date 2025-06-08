/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class Cola<T extends Comparable<T>> implements ICola<T> {

    private Nodo<T> primero = null;
    private Nodo<T> ultimo = null;
    private int cantidadElementos;

    public Cola() {
        primero = null;
        ultimo = null;
        cantidadElementos = 0;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nodo = new Nodo();
        nodo.setDato(dato);

        if (esVacia()) {
            primero = nodo;
            ultimo = nodo;
        } else {
            ultimo.setSiguiente(nodo);
            ultimo = nodo; // ESTO FALTABA
        }
        cantidadElementos++;
    }

    @Override
    public T desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();

        if (primero == null) {
            ultimo = null;
        }
        cantidadElementos--;

        return dato;
    }

    @Override
    public T frente() {
        if (esVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return primero.getDato();
    }

    @Override
    public boolean esVacia() {
        return (primero == null && ultimo == null);
    }

    @Override
    public int cantidadElementos() {
        return cantidadElementos;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = primero;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public ListaSE<T> copiarOrdenadoPorDato() {
        ListaSE<T> lista = new ListaSE<>();
        Nodo<T> actual = this.primero;

        while (actual != null) {
            lista.insertarOrdenado(actual.getDato());
            actual = actual.getSiguiente();
        }

        return lista;
    }
}
