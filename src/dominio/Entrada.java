/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author mateorodriguez
 */
public class Entrada implements Comparable<Entrada> {

    private Cliente cliente;
    private Evento evento;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Entrada otraEntrada) {
        // Compara los ids de las dos instancias
        return Integer.compare(this.id, otraEntrada.id);
    }
}
