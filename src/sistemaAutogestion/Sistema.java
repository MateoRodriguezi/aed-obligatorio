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

    //1.1
    // Pre-condición: ninguna
    // Post-condición: se inicializa el sistema con listas vacías de salas, clientes, eventos, compras e historial de eventos calificados
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

    //1.2
    // Pre-condición: nombre no puede ser nulo
    // Post-condición: se registra una nueva sala al inicio de la lista si cumple condiciones
    //Ordenar de entrada - Evitamos el mostrarInvertido por eso agregamos las salas al principio
    @Override
    public Retorno registrarSala(String nombre, int capacidad) {
        if (capacidad <= 0) {
            return Retorno.error2(); // Capacidad <= 0
        }
        Sala s = new Sala(nombre, capacidad);
        if (listaSalas.obtenerElemento(s) != null) {
            return Retorno.error1(); // Sala existente
        }
        listaSalas.agregarInicio(s);
        return Retorno.ok();

    }

    //1.3
    // Pre-condición: nombre no es null
    // Post-condición: si existe la sala con ese nombre, se elimina de la lista
    @Override
    public Retorno eliminarSala(String nombre) {

        Sala sBuscar = new Sala(nombre, 1); // La capacidad no importa para equals
        Sala salaReal = listaSalas.obtenerElemento(sBuscar);

        if (salaReal == null) {
            return Retorno.error1(); // No existe la sala
        }

        listaSalas.eliminarElemento(sBuscar); // La eliminamos
        return Retorno.ok();
    }

    //1.4
    // Pre-condición: descripción no puede ser null, el código no debe ser null
    // Post-condición: se registra un nuevo evento ordenado por código y se marca la sala como ocupada en la fecha del evento
    @Override
    public Retorno registrarEvento(String codigo, String descripcion, int aforoNecesario, LocalDate fecha) {
        Evento eBuscar = new Evento();
        eBuscar.setCodigo(codigo);

        if (listaEventos.obtenerElemento(eBuscar) != null) {
            return Retorno.error1(); // Evento ya existe
        }

        if (aforoNecesario <= 0) {
            return Retorno.error2();
        }

        // Crear copia ordenada por capacidad para que le de la sala que cumpla 
        // las condiciones pero con la menor capacidad posible
        ListaSE<Sala> salasOrdenadas = new ListaSE<>();
        Nodo<Sala> aux = listaSalas.getInicio();
        while (aux != null) {
            salasOrdenadas.insertarOrdenado(aux.getDato());
            aux = aux.getSiguiente();
        }

        // Buscar sala adecuada
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
            return Retorno.error3(); // No hay sala disponible
        }

        // Usamos el constructor con parámetros
        Evento nuevoEvento = new Evento(codigo, descripcion, aforoNecesario, fecha, salaAsignada);

        listaEventos.insertarOrdenado(nuevoEvento);
        salaAsignada.ocupar(fecha);

        return Retorno.ok();
    }

    //1.5
    // Pre-condición: cedula no ser null
    // Post-condición: se agrega un nuevo cliente al final de la lista
    @Override
    public Retorno registrarCliente(String cedula, String nombre) {

        if (cedula.length() != 8 || !cedula.matches("\\d{8}")) {
            return Retorno.error1();
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

    //1.6
    // Pre-condición: el cliente y el evento deben existir.
    // Post-condición: si hay entradas disponibles, se registra la entrada en el evento y se guarda en el historial; si no, se agrega al cliente a la lista de espera.
    @Override
    public Retorno comprarEntrada(String cedula, String codigoEvento) {
        Cliente clienteBuscar = new Cliente();
        clienteBuscar.setCedula(cedula);
        clienteBuscar = listaClientes.obtenerElemento(clienteBuscar);
        if (clienteBuscar == null) {
            return Retorno.error1(); // Cliente NO existe
        }
        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigoEvento);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if (eventoBuscar == null) {
            return Retorno.error2(); //EVENTO NO EXISTE
        }

        eventoBuscar.comprarEntrada(clienteBuscar);
        return Retorno.ok();
    }

    //1.7
    // Pre-condición: el código del evento debe existir y no ser null
    // Post-condición: se elimina el evento de la lista
    @Override
    public Retorno eliminarEvento(String codigo) {
        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigo);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if (eventoBuscar == null) {
            return Retorno.error1();
        }

        if (eventoBuscar.tieneEntradasVendidas()) {
            return Retorno.error2();
        }

        eventoBuscar.getSala().liberar(eventoBuscar.getFecha());
        listaEventos.eliminarElemento(eventoBuscar); // Faltaba esto
        return Retorno.ok();
    }

    // 1.8
    // Pre-condición: el cliente y el evento deben existir en el sistema.
    // Post-condición: se elimina la entrada del cliente, y si hay clientes en la lista de espera, se le asigna la entrada al primero en la cola.
    @Override
    public Retorno devolverEntrada(String cedula, String codigoEvento) {
        Cliente clienteBuscar = new Cliente();
        clienteBuscar.setCedula(cedula);
        clienteBuscar = listaClientes.obtenerElemento(clienteBuscar);
        if (clienteBuscar == null) {
            return Retorno.error1(); // Cliente NO existe
        }

        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigoEvento);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if (eventoBuscar == null) {
            return Retorno.error2(); // Evento NO existe
        }

        eventoBuscar.devolverEntrada(clienteBuscar);
        return Retorno.ok();
    }

    // 1.9
    // Pre-condición: cliente y evento con esa cédula y código deben existir, el puntaje debe estar entre 1 y 10, el comentario no puede ser null y el cliente no debe haber calificado antes ese evento.
    // Post-condición: se registra la calificación y se actualiza el promedio del evento.
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
        e = listaEventos.obtenerElemento(e); // recupero el objeto
        if (e == null) {
            return Retorno.error2();
        }

        Nodo<Calificacion> actual = e.getCalificaciones().getInicio();
        while (actual != null) {
            if (actual.getDato().getCliente().equals(c)) {
                return Retorno.error4();
            }
            actual = actual.getSiguiente();
        }
        Calificacion cal = new Calificacion(c, puntaje, comentario);
        e.actualizarPromedio(cal);
        e.getCalificaciones().agregarFinal(cal);  // faltaba agregarla a la lista
        listaEventosCalificados.insertarOrdenado(e);
        return Retorno.ok();
    }

    //2.1
    // Pre-condición: ninguna
    // Post-condición: retorna string con la lista de salas registradas
    @Override
    public Retorno listarSalas() {

        Retorno ret = Retorno.ok();
        ret.valorString = listaSalas.mostrar();

        return ret;
    }

    //2.2
    // Pre-condición: ninguna
    // Post-condición: retorna string con los eventos ordenados por código
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

    //2.3
    // Pre-condición: ninguna.
    // Post-condición: retorna string con los clientes ordenados por cédula
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

    //2.4
    // Pre-condición: la matriz de vistaSala debe tener formato válido.
    // Post-condición: se indica si la sala es óptima según el criterio de columnas con más ocupados consecutivos que libres.
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
    public Retorno listarClientesDeEvento(String codigo, int n) {
        ListaSE<Cliente> clientes = new ListaSE();

        if(n < 1){
            return Retorno.error2();
        }
        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigo);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if(eventoBuscar == null){
            return Retorno.error1();
        }
        Retorno ret = Retorno.ok();
        clientes = eventoBuscar.listarClientes(n);
        ret.valorString = clientes.mostrar();
        return ret;
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
