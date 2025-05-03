/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author mateorodriguez
 */
public class Cliente implements Comparable<Cliente> {

    private String cedula;
    private String nombre;

    // Constructor
    public Cliente() {
    }

    public Cliente(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return cedula + "-" + nombre;
    }

    // Implementación de compareTo por cédula
    @Override
    public int compareTo(Cliente otro) {
        return this.cedula.compareTo(otro.cedula);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cliente)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        return this.cedula.equals(((Cliente) obj).getCedula());
    }
}
