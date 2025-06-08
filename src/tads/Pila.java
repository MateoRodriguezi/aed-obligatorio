/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class Pila<T extends Comparable<T>> implements IPila<T> {

    private Nodo<T> tope;
    private int cantidad;

    public Pila() {
        this.tope = null;
        this.cantidad = 0;
    }

    @Override
    public boolean estaVacia() {
        return tope == null;
    }

    @Override
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>();
        nuevoNodo.setDato(dato);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        cantidad++;
    }

    @Override
    public T top() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }

    @Override
    public T desapilar() {
        if (estaVacia()) {
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        cantidad--;
        return dato;
    }

    @Override
    public void vaciar() {
        tope = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = tope;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        return cantidad;
    }

    @Override
    public Pila<T> copiarPila() {
        Pila<T> copia = new Pila<>();
        Pila<T> auxiliar = new Pila<>();

        Nodo<T> aux = tope;
        while (aux != null) {
            auxiliar.apilar(aux.getDato());
            aux = aux.getSiguiente();
        }

        while (!auxiliar.estaVacia()) {
            T dato = auxiliar.desapilar();
            copia.apilar(dato);
        }

        return copia;
    }

    @Override
    public void intercambiarTope() {
        if (cantidad >= 2) {
            T primero = desapilar();
            T segundo = desapilar();
            apilar(primero);
            apilar(segundo);
        }
    }

    @Override
    public void concatenar(Pila<T> otraPila) {
        Pila<T> auxiliar = new Pila<>();

        while (!otraPila.estaVacia()) {
            auxiliar.apilar(otraPila.desapilar());
        }

        while (!auxiliar.estaVacia()) {
            apilar(auxiliar.desapilar());
        }
    }

    @Override
    public void invertir() {
        Pila<T> auxiliar = new Pila<>();
        while (!estaVacia()) {
            auxiliar.apilar(desapilar());
        }
        this.tope = auxiliar.tope;
        this.cantidad = auxiliar.cantidad;
    }
}
