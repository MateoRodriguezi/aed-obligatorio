/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author nicoc
 */
public class Calificacion implements Comparable<Calificacion> {
    private Cliente cliente;
    private int puntaje;
    private String comentario;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

//    public Calificacion(Cliente cliente, int puntaje, String comentario) {
//        this.cliente = cliente;
//        this.puntaje = puntaje;
//        this.comentario = comentario;
//    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    @Override
    public int compareTo(Calificacion otra) {
        return this.cliente.compareTo(otra.cliente);
    }
}
