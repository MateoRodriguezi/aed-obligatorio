/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public interface ICola<T extends Comparable> {
    
    public void encolar(T dato);
    
    public T desEncolar();
    
    public T front();
    
    public boolean esVacia();
    
    public int cantidadElementos();
    
    public void mostrar();
}
