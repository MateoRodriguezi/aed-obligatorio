/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.time.LocalDateTime;
import java.time.LocalDate;
import tads.Cola;
import tads.ListaSE;
import tads.Nodo;

/**
 *
 * @author mateorodriguez
 */
public class Evento implements Comparable<Evento> {

    private String codigo;
    private String descripcion;
    private int aforoNecesario;
    private LocalDate fecha;
    private Sala sala;
    private ListaSE<Entrada> entradasvendidas = new ListaSE();

    private Cola<Cliente> ColaDeEspera = new Cola();
    private int disponibilidad;  // Eliminar la inicialización que depende de sala
    private double promedioCalificaciones;
    private int sumaPuntajes;
    private int cantidadPuntajes;
    private ListaSE<Calificacion> calificaciones = new ListaSE<>();

    public Evento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha, Sala sala) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aforoNecesario = aforoNecesario;
        this.fecha = fecha;
        this.sala = sala;
        this.disponibilidad = sala.getCapacidad(); // Tiene toda la capacidad de la sala
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

    public ListaSE<Entrada> getEntradasvendidas() {
        return entradasvendidas;
    }

    public Cola<Cliente> getColaDeEspera() {
        return ColaDeEspera;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public void comprarEntrada(Cliente c) {
        if (this.disponibilidad > 0) {
            Entrada e = new Entrada(c, this, LocalDateTime.now(), false);
            this.entradasvendidas.agregarInicio(e);
            c.getEntradasCompradas().agregarFinal(e);
            this.disponibilidad--; // nuevo
        } else {
            this.ColaDeEspera.encolar(c);
        }
    }

    public void reintegrarEntrada() {
        this.disponibilidad++; // Al devolver una entrada, aumenta el cupo disponible
    }

    public Boolean tieneEntradasVendidas() {
        return this.entradasvendidas.cantidadElementos() > 0;
    }

    public void devolverEntrada(Cliente c) {
        Nodo<Entrada> actual = entradasvendidas.getInicio();
        while (actual != null) {
            Entrada entradaNodo = actual.getDato();
            if (c.equals(entradaNodo.getCliente())) {
                Entrada e = c.getEntradasCompradas().obtenerElemento(entradaNodo);
                if (e != null) {
                    e.setDevuelta(true);
                }
                entradasvendidas.eliminarElemento(entradaNodo);
                this.reintegrarEntrada(); // Faltaba liberar el cupo
                break;
            }
            actual = actual.getSiguiente();
        }

        if (ColaDeEspera.cantidadElementos() > 0) {
            Cliente siguiente = ColaDeEspera.desencolar();
            this.comprarEntrada(siguiente); // reasignación completa
        }
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

    public ListaSE<Cliente> listarClientes(int n) {
        ListaSE<Cliente> clientes = new ListaSE<>();
        int contador = 0;
        Nodo<Entrada> actual = entradasvendidas.getInicio();

        while (actual != null && contador < n) {
            clientes.agregarFinal(actual.getDato().getCliente());
            contador++;
            actual = actual.getSiguiente();
        }

        return clientes;
    }

}
