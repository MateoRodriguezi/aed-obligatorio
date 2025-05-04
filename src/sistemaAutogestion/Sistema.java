package sistemaAutogestion;

import dominio.Cliente;
import dominio.Entrada;
import dominio.Evento;
import dominio.Calificacion;
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
    private ListaSE<Evento> listaEventosCalificados;

    @Override
    public Retorno crearSistemaDeGestion() {
        // Inicializamos las listas desde cero
        listaSalas = new ListaSE<>();
        listaClientes = new ListaSE<>();
        listaEventos = new ListaSE<>();
        historialCompras = new Pila<>();
        listaEventosCalificados = new ListaSE<>();
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

        // Creamos una copia ordenada por capacidad (de menor a mayor)
        ListaSE<Sala> salasOrdenadas = new ListaSE<>();
        Nodo<Sala> aux = listaSalas.getInicio();
        while (aux != null) {
            salasOrdenadas.insertarOrdenado(aux.getDato()); // usa compareTo en Sala
            aux = aux.getSiguiente();
        }

        // Buscamos la primera sala adecuada
        Nodo<Sala> actual = salasOrdenadas.getInicio();
        Sala salaAsignada = null;

        while (actual != null && salaAsignada == null) {
            Sala sala = actual.getDato();
            if (sala.getCapacidad() >= aforoNecesario && !sala.estaOcupada(fecha)) {
                salaAsignada = sala;
            }
            actual = actual.getSiguiente();
        }

        if (salaAsignada == null) {
            return Retorno.error3(); // No hay salas disponibles con aforo 
        }

        // Crear evento nuevo
        Evento nuevoEvento = new Evento();
        nuevoEvento.setCodigo(codigo);
        nuevoEvento.setDescripcion(descripcion);
        nuevoEvento.setAforoNecesario(aforoNecesario);
        nuevoEvento.setFecha(fecha.atStartOfDay());
        nuevoEvento.setSala(salaAsignada);

        listaEventos.insertarOrdenado(nuevoEvento);
        salaAsignada.ocupar(fecha);

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
        if (puntaje > 10 || puntaje < 1) {
            return Retorno.error3();
        }
        Cliente c = new Cliente();
        c.setCedula(cedula);
        if (listaClientes.obtenerElemento(c) == null) {
            return Retorno.error1();
        }

        Evento e = new Evento();
        e.setCodigo(codigoEvento);
        if (listaEventos.obtenerElemento(e) == null) {
            return Retorno.error2();
        }

        Nodo<Calificacion> actual = e.getCalificaciones().getInicio();
        while (actual != null) {
            if (actual.getDato().getCliente().equals(c)) {
                return Retorno.error4();
            }
            actual = actual.getSiguiente();
        }
        Calificacion cal = new Calificacion();
        cal.setCliente(c);
        cal.setComentario(comentario);
        cal.setPuntaje(puntaje);
        e.actualizarPromedio(cal);
        listaEventosCalificados.insertarOrdenado(e);
        return Retorno.ok();
    }

    @Override
    public Retorno listarSalas() {
        // Lista auxiliar para invertir orden
        ListaSE<Sala> salasInvertidas = new ListaSE<>();

        Nodo<Sala> actual = listaSalas.getInicio();

        // Recorro la lista original y agrego cada sala al inicio de la nueva lista auxiliar
        while (actual != null) {
            salasInvertidas.agregarInicio(actual.getDato());
            actual = actual.getSiguiente();
        }

        // Ahora construimos el string con formato "nombre-capacidad#..."
        StringBuilder resultado = new StringBuilder();
        Nodo<Sala> nodo = salasInvertidas.getInicio();

        while (nodo != null) {
            Sala sala = nodo.getDato();
            resultado.append(sala.getNombre()).append("-").append(sala.getCapacidad());

            if (nodo.getSiguiente() != null) {
                resultado.append("#");
            }

            nodo = nodo.getSiguiente();
        }

        // Retornamos el resultado
        Retorno ret = Retorno.ok();
        ret.valorString = resultado.toString();
        return ret;
    }

    @Override
    public Retorno listarEventos() {
        ListaSE<Evento> eventosOrdenados = new ListaSE<>();

        Nodo<Evento> actual = listaEventos.getInicio();
        while (actual != null) {
            eventosOrdenados.insertarOrdenado(actual.getDato());
            actual = actual.getSiguiente();
        }

        Retorno ret = Retorno.ok();
        ret.valorString = eventosOrdenados.mostrar();
        return ret;
    }

    @Override
    public Retorno listarClientes() {
        ListaSE<Cliente> clientesOrdenados = new ListaSE<>();

        Nodo<Cliente> actual = listaClientes.getInicio();
        while (actual != null) {
            clientesOrdenados.insertarOrdenado(actual.getDato());
            actual = actual.getSiguiente();
        }

        Retorno ret = Retorno.ok();
        ret.valorString = clientesOrdenados.mostrar();  // Igual que en el ejemplo del profe
        return ret;
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
