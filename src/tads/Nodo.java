/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class Nodo<T extends Comparable> {

    private T dato;
    private Nodo<T> siguiente;  // Nodo debe ser del tipo genérico T

    // Método para obtener el dato
    public T getDato() {
        return dato;
    }

    // Método para establecer el dato
    public void setDato(T dato) {
        this.dato = dato;
    }

    // Método para obtener el siguiente nodo
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    // Método para establecer el siguiente nodo
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
