package sistemaAutogestion;

import dominio.Cliente;
import dominio.Entrada;
import dominio.Evento;
import dominio.Sala;
import tads.ListaSE;
import tads.Cola;
import tads.Pila;
import tads.Nodo;

import java.time.LocalDate;

public class Sistema implements IObligatorio {

    private ListaSE<Sala> listaSalas;
    private ListaSE<Cliente> listaClientes;
    private ListaSE<Evento> listaEventos;
    private Cola<Cliente> clientesEnEspera;

    public Sistema() {
        listaSalas = new ListaSE<>();
        listaClientes = new ListaSE<>();
        listaEventos = new ListaSE<>();
    }

    public ListaSE<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(ListaSE<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public ListaSE<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaSE<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ListaSE<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(ListaSE<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    @Override
    public Retorno crearSistemaDeGestion() {
        // Verifico si las colecciones ya están inicializadas
        if (this.listaSalas == null || this.listaClientes == null || this.listaEventos == null) {
            return Retorno.noImplementada();
        }

        Retorno ret = new Retorno(Retorno.Resultado.OK);

        this.setListaSalas(listaSalas);
        this.setListaClientes(listaClientes);
        this.setListaEventos(listaEventos);

        return ret;
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        
        if(capacidad <= 0){
            return Retorno.error2();
        }
        
        for(Sala s: listaSalas)
    }

    @Override
    public Retorno eliminarSala(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEvento(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarSalas() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEventos() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarClientesDeEvento(String código, int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEsperaEvento() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUtimasCompras(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eventoMejorPuntuado() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasDeCliente(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno comprasXDia(int mes) {
        return Retorno.noImplementada();
    }

}
