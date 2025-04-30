/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDateTime;

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

    public Evento() {
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

    @Override
    public int compareTo(Evento otroEvento) {
        // Compara las fechas de los dos eventos
        return this.fecha.compareTo(otroEvento.fecha);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Evento)) {
            return false;
        }
        return this.codigo.equals(((Evento) obj).codigo);
    }

    @Override
    public String toString() {
        return codigo + "-" + descripcion + "-" + aforoNecesario + "-"
                + sala.getCapacidad() + "-" + aforoNecesario;
    }

}
