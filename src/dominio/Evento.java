/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDateTime;
import java.time.LocalDate;
import tads.ListaSE;

/**
 *
 * @author mateorodriguez
 */
public class Evento implements Comparable<Evento> {

    private String codigo;
    private String descripcion;
    private int aforoNecesario;
    private LocalDateTime fecha;
    private Sala sala;
    private ListaSE<Entrada> entradasvendidas;
    private ListaSE<Cliente> listaclientesevento;
    //private Cola<Cliente> ColaDeEspera;
    private int disponibilidad;  // Eliminar la inicialización que depende de sala
    private double promedioCalificaciones;
    private int sumaPuntajes;
    private int cantidadPuntajes;
    private ListaSE<Calificacion> calificaciones = new ListaSE<>();

    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha, Sala sala) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aforoNecesario = aforoNecesario;
        this.fecha = fecha.atStartOfDay();  // lo convertimos a LocalDateTime directamente acá
        this.sala = sala;
    }

    public Evento() {
    }

    public double getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    public int getSumaPuntajes() {
        return sumaPuntajes;
    }

    public ListaSE<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAforoNecesario() {
        return aforoNecesario;
    }

    public void setAforoNecesario(int aforoNecesario) {
        this.aforoNecesario = aforoNecesario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void actualizarPromedio(Calificacion c) {
        this.sumaPuntajes += c.getPuntaje();
        this.cantidadPuntajes++;
        this.promedioCalificaciones = (double) sumaPuntajes / cantidadPuntajes;
    }

    @Override
    public int compareTo(Evento otroEvento) {
        return this.codigo.compareTo(otroEvento.codigo);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Evento)) {
            return false;
        }
        return this.codigo.equalsIgnoreCase(((Evento) obj).codigo);
    }

    @Override
    public String toString() {
        int vendidas = 0;
        if (entradasvendidas != null) {
            vendidas = entradasvendidas.cantidadElementos();
        }

        int disponibles = sala.getCapacidad() - vendidas;

        return codigo + "-" + descripcion + "-"
                + sala.getCapacidad() + "-"
                + disponibles + "-"
                + vendidas;
    }

}
