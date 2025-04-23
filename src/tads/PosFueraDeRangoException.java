/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author mateorodriguez
 */
public class PosFueraDeRangoException extends RuntimeException {
    
    public PosFueraDeRangoException() {
        super("Posicion fuera de rango");
    }
    
}
