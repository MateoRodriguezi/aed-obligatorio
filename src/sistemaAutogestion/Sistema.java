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
    private Pila<Entrada> historialCompras;

    @Override
    public Retorno crearSistemaDeGestion() {
        // Inicializamos las listas desde cero
        listaSalas = new ListaSE<>();
        listaClientes = new ListaSE<>();
        listaEventos = new ListaSE<>();
        historialCompras = new Pila<>();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        if (capacidad <= 0) {
            return Retorno.error2(); // Capacidad <= 0
        }
        Sala s = new Sala(nombre, capacidad);
        if (listaSalas.obtenerElemento(s) != null) {
            return Retorno.error1(); // Sala existente
        }
        listaSalas.agregarFinal(s);
        return Retorno.ok();

    }

    @Override
    public Retorno eliminarSala(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1(); // Nombre inválido
        }

        Sala sBuscar = new Sala(nombre, 1); // La capacidad no importa para equals
        Sala salaReal = listaSalas.obtenerElemento(sBuscar);

        if (salaReal == null) {
            return Retorno.error1(); // No existe la sala
        }

        listaSalas.eliminarElemento(sBuscar); // La eliminamos
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

        // Falta la logica para el error 3: 3. No hay salas disponibles para esa fecha con aforo suficiente
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
        Cliente clienteBuscar = new Cliente();
        clienteBuscar.setCedula(cedula);
        if (listaClientes.obtenerElemento(clienteBuscar) == null) {
            return Retorno.error1(); // Cliente NO existe
        }
        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigoEvento);
        if (listaEventos.obtenerElemento(eventoBuscar) == null) {
            return Retorno.error2(); //EVENTO NO EXISTE
        }

        //DECIDIR COMO USAR LA PILA (POR EL REQUERIMIENTO 2.7)
        //VALIDAR CAPACIDAD DISPONIBLE CON EVENTO.GETDISPONIBILIDAD 
        //Y SI ES > A 0 SE ASIGNA CLIENTE A ENTRADA Y ENTRADA A EVENTO
        //EN CASO CONTRARIO SI ASIGNA CLIENTE A LISTA DE ESPERA DEL EVENTO
        return Retorno.ok();
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
        // Creamos una lista auxiliar para guardar los eventos ordenados por código
        ListaSE<Evento> listaOrdenada = new ListaSE<>();

        // Recorremos la lista original de eventos
        Nodo<Evento> actual = listaEventos.getInicio();
        while (actual != null) {
            // Agrego cada evento en la lista ordenada ( uso el compareTo del Evento)
            listaOrdenada.insertarOrdenado(actual.getDato());
            actual = actual.getSiguiente();
        }

        // Use el StringBuilder para construir el output del string
        StringBuilder reporte = new StringBuilder();
        Nodo<Evento> nodo = listaOrdenada.getInicio();

        // Recorro la lista ordenada de eventos
        while (nodo != null) {
            // Voy agregando la información del evento usando su toString()
            reporte.append(nodo.getDato().toString());

            // Si no es el último evento, agrego el separador #
            if (nodo.getSiguiente() != null) {
                reporte.append("#");
            }

            // Avanzo al siguiente nodo
            nodo = nodo.getSiguiente();
        }

        // Creamos el retorno con estado OK y el valorString con el reporte generado
        Retorno ret = Retorno.ok();
        ret.valorString = reporte.toString();
        return ret;
    }

    @Override
    public Retorno listarClientes() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno esSalaOptima(String[][] vistaSala) {
        // Obtengo la cantidad de columnas y filas de la matriz
        int columnas = vistaSala[0].length;
        int filas = vistaSala.length;

        // Variable de contador de columnas que cumplen con la condicion de que son optimas
        int columnasOptimas = 0;

        // Recorro cada columna de la sala
        for (int col = 0; col < columnas; col++) {
            int libres = 0;               // Variable para llevar la cantidad de asientos libres ('X')
            int maxConsecutivos = 0;      // Variable para guardar la mayor cantidad de ocupados consecutivos ('O') en esta columna
            int consecutivos = 0;         // Variable para contar temporalmente la cantidad de ocupados consecutivos

            // Aca recorro cada fila de la columna
            for (int fila = 0; fila < filas; fila++) {
                String asiento = vistaSala[fila][col];

                // Si no es un límite de sala ('#') sigo procesando
                if (!asiento.equals("#")) {
                    // Si es un asiento libre
                    if (asiento.equals("X")) {
                        libres++;
                        consecutivos = 0;
                    } // Si es un asiento ocupado
                    else if (asiento.equals("O")) {
                        consecutivos++;
                        maxConsecutivos = Math.max(maxConsecutivos, consecutivos);
                    }
                }
            }

            // Chequeo si esta columna es "óptima"
            if (maxConsecutivos > libres) {
                columnasOptimas++;
            }
        }

        Retorno ret = Retorno.ok();
        // Si hay al menos 2 columnas óptimas, la sala es óptima
        if (columnasOptimas >= 2) {
            ret.valorString = "Es óptimo";
        } else {
            ret.valorString = "No es óptimo";
        }

        return ret;
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
