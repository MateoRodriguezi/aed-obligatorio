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

    @Override
    public Retorno crearSistemaDeGestion() {
        // Inicializamos las listas desde cero
        listaSalas = new ListaSE<>();
        listaClientes = new ListaSE<>();
        listaEventos = new ListaSE<>();

        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        if (capacidad <= 0) {
            return Retorno.error2(); // capacidad inválida
        }
        Sala s = new Sala(nombre, capacidad);
        if (listaSalas.obtenerElemento(s) == null) {
            listaSalas.agregarFinal(s);
            return Retorno.ok();
        } else {
            return Retorno.error1();
        }

    }

    @Override
    public Retorno eliminarSala(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1(); // nombre inválido
        }

        Sala sBuscar = new Sala(nombre, 1); // la capacidad no importa para equals
        Sala salaReal = listaSalas.obtenerElemento(sBuscar);

        if (salaReal == null) {
            return Retorno.error1(); // no existe la sala
        }

        listaSalas.eliminarElemento(sBuscar); // la eliminamos
        return Retorno.ok();
    }

    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {

        // Verificamos si ya existe un evento con ese código
        Evento eBuscar = new Evento();
        eBuscar.setCodigo(codigo);

        Evento eventoReal = listaEventos.obtenerElemento(eBuscar);
        if (eventoReal != null) {
            return Retorno.error1(); // ya existe evento con ese código
        }

        if (aforoNecesario <= 0) {
            return Retorno.error2();
        }

//        if (fecha == null || fecha.isBefore(LocalDate.now())) {
//            return Retorno.error3();
//        }
        // Buscar sala con capacidad suficiente
        // Me falta implementar esta logica 
        // Crear evento nuevo
        Evento nuevoEvento = new Evento();
        nuevoEvento.setCodigo(codigo);
        nuevoEvento.setDescripcion(descripcion);
        nuevoEvento.setAforoNecesario(aforoNecesario);
        nuevoEvento.setFecha(fecha.atStartOfDay());
//        nuevoEvento.setSala(salaAsignada);

        listaEventos.agregarFinal(nuevoEvento);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarCliente(String cedula, String nombre) {
        
        if (cedula == null || cedula.length() != 8) {
            return Retorno.error1(); // Formato inválido
        }
        
        Cliente clienteBuscar = new Cliente();
        clienteBuscar.setCedula(cedula);

        if (listaClientes.obtenerElemento(clienteBuscar) != null) {
            return Retorno.error2(); // Cliente ya existe
        }

        Cliente nuevoCliente = new Cliente(cedula, nombre);
        listaClientes.agregarFinal(nuevoCliente);

        return Retorno.ok();
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
