/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public interface IPila<T extends Comparable<T>> {

    public boolean estaVacia();

    public void apilar(T dato);

    public T top();

    public T desapilar();

    public void vaciar();

    public void mostrar();

    public int cantidadElementos();

    public Pila<T> copiarPila();

    public void intercambiarTope();

    public void concatenar(Pila<T> otraPila);

    public void invertir();

    public ListaSE<T> copiarEnLista();
}
