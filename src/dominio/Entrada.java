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
public class Entrada implements Comparable<Entrada> {

    private Cliente cliente;
    private Evento evento;
    private LocalDateTime fechaCompra;
    private int id;
    private static int contadorId = 0;
    private boolean devuelta;
    private Integer calificacion; // null si no fue calificada
    private String comentario; // null si no hay comentario

    public Entrada(Cliente cliente, Evento evento, LocalDateTime fechaCompra, boolean devuelta) {
        this.cliente = cliente;
        this.evento = evento;
        this.fechaCompra = fechaCompra;
        this.devuelta = devuelta;
        id = ++contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean isDevuelta() {
        return devuelta;
    }

    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int compareTo(Entrada otraEntrada) {
        // Compara los ids de las dos instancias
        return Integer.compare(this.id, otraEntrada.id);
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", cliente=" + cliente + ", evento=" + evento + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entrada)) {
            return false;
        }
        Entrada otra = (Entrada) obj;
        return this.id == otra.id;
    }
}
