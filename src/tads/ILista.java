/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public interface ILista<T> {
    
    // Retorna true si la lista está vacía, false en caso contrario
    public boolean esVacia();

    // Agrega un elemento al inicio de la lista
    public void agregarInicio(T elemento);
    
    // Agrega un elemento al final de la lista
    public void agregarFinal(T elemento);
    
    // Borra el primer elemento de la lista
    public void borrarInicio();

    // Borra el último elemento de la lista
    public void borrarFinal();

    // Elimina todos los elementos de la lista
    public void vaciar();

    // Muestra los elementos de la lista (puede ser con un System.out)
    public void mostrar();

    public T obtenerElemento(T elemento);
    
    public void insertarOrdenado(T dato);
}
