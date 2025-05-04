/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import tads.ListaSE;
import tads.Nodo;

/**
 *
 * @author mateorodriguez
 */
public class Sala implements Comparable<Sala> {

    private String nombre;
    private int capacidad;
    private ListaSE<LocalDate>fechasOcupada;

    public boolean estaOcupada(LocalDate fecha) {
    Nodo<LocalDate> actual = fechasOcupada.getInicio();
    while (actual != null) {
        LocalDate fechaNodo = actual.getDato();
        if (fechaNodo.equals(fecha)) {
            return true;
        }
        actual = actual.getSiguiente();
    }
    return false;
    }   



    public ListaSE<LocalDate> getFechasOcupada() {
        return fechasOcupada;
    }

    public void setFechasOcupada(ListaSE<LocalDate> fechasOcupada) {
        this.fechasOcupada = fechasOcupada;
    }


    public Sala(String nom, int cap) {
        nombre = nom;
        capacidad = cap;
        fechasOcupada = new ListaSE<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int compareTo(Sala otraSala) {
        // Comparar alfabéticamente por el nombre
        return this.nombre.compareTo(otraSala.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sala)) {
            return false;
        }
        return this.nombre.equals(((Sala) obj).nombre);
    }

    @Override
    public String toString() {
        return nombre + "-" + capacidad;
    }

    public void ocupar(LocalDate fecha) {
        fechasOcupada.agregarFinal(fecha);
    }

}
