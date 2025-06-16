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
    private ListaSE<Evento> listaEventosMejorPuntuados = new ListaSE<>();

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
            return Retorno.error1();
        }

        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigoEvento);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if (eventoBuscar == null) {
            return Retorno.error2();
        }

        // Guardamos cantidad antes de comprar
        int antes = eventoBuscar.getEntradasvendidas().cantidadElementos();
        eventoBuscar.comprarEntrada(clienteBuscar);

        // Verificamos si realmente se compró (y no quedó en espera)
        int despues = eventoBuscar.getEntradasvendidas().cantidadElementos();
        if (despues > antes) {
            Entrada entradaComprada = clienteBuscar.getEntradasCompradas().obtenerUltimo();
            historialCompras.apilar(entradaComprada);
        }

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
// Post-condición: se registra la calificación, se actualiza el promedio y las listas auxiliares correspondientes.
    @Override
    public Retorno calificarEvento(String cedula, String codigoEvento, int puntaje, String comentario) {
        if (puntaje > 10 || puntaje < 1) {
            return Retorno.error3(); // Puntaje fuera de rango
        }

        // Verificamos existencia del cliente
        Cliente clienteTemp = new Cliente();
        clienteTemp.setCedula(cedula);
        Cliente cliente = listaClientes.obtenerElemento(clienteTemp);
        if (cliente == null) {
            return Retorno.error1(); // Cliente no existe
        }

        // Verificamos existencia del evento
        Evento eventoTemp = new Evento();
        eventoTemp.setCodigo(codigoEvento);
        Evento evento = listaEventos.obtenerElemento(eventoTemp);
        if (evento == null) {
            return Retorno.error2(); // Evento no existe
        }

        // Validamos que el cliente no haya calificado antes este evento
        Nodo<Calificacion> nodoCal = evento.getCalificaciones().getInicio();
        while (nodoCal != null) {
            if (nodoCal.getDato().getCliente().equals(cliente)) {
                return Retorno.error4(); // Ya calificó
            }
            nodoCal = nodoCal.getSiguiente();
        }

        // Crear calificación y actualizar promedio
        Calificacion cal = new Calificacion(cliente, puntaje, comentario);
        evento.getCalificaciones().agregarFinal(cal);
        evento.actualizarPromedio(cal);

        // Insertar en lista de eventos calificados (orden alfabético)
        listaEventosCalificados.insertarOrdenado(evento);

        // Actualizar lista de eventos mejor puntuados
        if (listaEventosMejorPuntuados.esVacia()) {
            listaEventosMejorPuntuados.agregarFinal(evento);
        } else {
            double maxPromedio = ((Evento) listaEventosMejorPuntuados.getInicio().getDato()).getPromedioCalificaciones();
            double nuevoPromedio = evento.getPromedioCalificaciones();

            if (nuevoPromedio > maxPromedio) {
                listaEventosMejorPuntuados.vaciar();
                listaEventosMejorPuntuados.agregarFinal(evento);
            } else if (nuevoPromedio == maxPromedio) {
                listaEventosMejorPuntuados.agregarFinal(evento);
            }
        }

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

    //2.5
    // Pre-condición: El código del evento no puede ser null. El parámetro n debe ser mayor o igual a 1.
    // Post-condición: Si el evento existe y n es válido, retorna los últimos n clientes que compraron entradas para el evento.     
    // Si hay menos de n clientes, se listan todos los disponibles. El resultado se retorna en un string con el formato "cedula-nombre#...".
    @Override
    public Retorno listarClientesDeEvento(String codigo, int n) {
        ListaSE<Cliente> clientes = new ListaSE();

        if (n < 1) {
            return Retorno.error2();
        }
        Evento eventoBuscar = new Evento();
        eventoBuscar.setCodigo(codigo);
        eventoBuscar = listaEventos.obtenerElemento(eventoBuscar);
        if (eventoBuscar == null) {
            return Retorno.error1();
        }
        Retorno ret = Retorno.ok();
        clientes = eventoBuscar.listarClientes(n);
        ret.valorString = clientes.mostrar();
        return ret;
    }

    //2.6
    // Pre-condición: El sistema debe tener eventos cargados.
    // Post-condición: Solo se listan eventos que tengan al menos un cliente en su lista de espera. Los eventos están ordenados por código.
    // Los clientes están ordenados por cédula dentro de cada evento. El resultado se entrega en un string con el formato: "CODIGOEVENTO-CEDULA#...".
    @Override
    public Retorno listarEsperaEvento() {
        ListaSE<Evento> eventosOrdenados = new ListaSE<>();
        Nodo<Evento> actual = listaEventos.getInicio();
        while (actual != null) {
            eventosOrdenados.insertarOrdenado(actual.getDato());
            actual = actual.getSiguiente();
        }

        String salida = "";
        boolean esPrimero = true;

        actual = eventosOrdenados.getInicio();
        while (actual != null) {
            Evento evento = actual.getDato();
            if (!evento.getColaDeEspera().esVacia()) {
                ListaSE<Cliente> clientesOrdenados = evento.getColaDeEspera().copiarOrdenadoPorDato();
                Nodo<Cliente> nodoCliente = clientesOrdenados.getInicio();
                while (nodoCliente != null) {
                    Cliente cliente = nodoCliente.getDato();

                    if (!esPrimero) {
                        salida += "#";
                    }
                    salida += evento.getCodigo() + "-" + cliente.getCedula();
                    esPrimero = false;

                    nodoCliente = nodoCliente.getSiguiente();
                }
            }
            actual = actual.getSiguiente();
        }

        Retorno ret = Retorno.ok();
        ret.valorString = salida;
        return ret;
    }

    //2.7
    @Override
    public Retorno deshacerUtimasCompras(int n) {
        ListaSE<String> deshechas = new ListaSE<>();

        int cantidad = 0;
        while (!historialCompras.estaVacia() && cantidad < n) {
            Entrada entrada = historialCompras.desapilar();

            Evento evento = entrada.getEvento();
            Cliente cliente = entrada.getCliente();

            cliente.getEntradasCompradas().eliminarElemento(entrada);
            evento.getEntradasvendidas().eliminarElemento(entrada);
            evento.reintegrarEntrada();

            String registro = evento.getCodigo() + "-" + cliente.getCedula();
            deshechas.insertarOrdenado(registro);

            if (!evento.getColaDeEspera().esVacia()) {
                Cliente siguiente = evento.getColaDeEspera().desencolar();
                evento.comprarEntrada(siguiente);
            }

            cantidad++;
        }

        Retorno r = Retorno.ok();
        r.valorString = deshechas.mostrar(); // El método concatena con #
        return r;
    }

    // 2.8
// Pre-condición: No tiene
// Post-condición: Retorna el/los eventos con el mejor promedio de calificaciones en orden alfabético por código. 
// El resultado está en formato "codigo-promedio#codigo-promedio"
    @Override
    public Retorno eventoMejorPuntuado() {
        Retorno r = Retorno.ok();

        if (listaEventosMejorPuntuados.esVacia()) {
            r.valorString = "";
            return r;
        }

        ListaSE<String> eventos = new ListaSE<>();

        Nodo<Evento> actual = listaEventosMejorPuntuados.getInicio();
        while (actual != null) {
            Evento e = actual.getDato();
            int promedioEntero = (int) e.getPromedioCalificaciones();  
            eventos.insertarOrdenado(e.getCodigo() + "-" + promedioEntero);
            actual = actual.getSiguiente();
        }

        r.valorString = eventos.mostrar(); 
        return r;
    }

    //2.9
    @Override
    public Retorno comprasDeCliente(String cedula) {
        // Creamos un cliente para buscarlo en la lista
        Cliente clienteBuscar = new Cliente();
        clienteBuscar.setCedula(cedula);
        Cliente cliente = listaClientes.obtenerElemento(clienteBuscar);

        if (cliente == null) {
            return Retorno.error1(); // Cliente no existe
        }

        ListaSE<String> compras = new ListaSE<>();

        Nodo<Entrada> actual = cliente.getEntradasCompradas().getInicio();
        while (actual != null) {
            Entrada entrada = actual.getDato();
            String estado = entrada.isDevuelta() ? "D" : "N";
            String linea = entrada.getEvento().getCodigo() + "-" + estado;
            compras.agregarFinal(linea);
            actual = actual.getSiguiente();
        }

        Retorno r = Retorno.ok();
        r.valorString = compras.mostrar();
        return r;
    }

    //2.10
    @Override
    public Retorno comprasXDia(int mes) {
        if (mes < 1 || mes > 12) {
            return Retorno.error1(); // mes inválido
        }

        Retorno r = Retorno.ok();
        ListaSE<String> resumen = new ListaSE<>();

        // Copiamos las entradas del historial sin modificar la pila original
        ListaSE<Entrada> copiaHistorial = historialCompras.copiarEnLista();
        Nodo<Entrada> actual = copiaHistorial.getInicio();

        while (actual != null) {
            Entrada entrada = actual.getDato();
            if (entrada.getFechaCompra().getMonthValue() == mes) {
                int dia = entrada.getFechaCompra().getDayOfMonth();
                boolean encontrado = false;

                Nodo<String> nodoResumen = resumen.getInicio();
                Nodo<String> anterior = null;

                // Buscamos si ya hay registro para ese día
                while (nodoResumen != null) {
                    String dato = nodoResumen.getDato();
                    int diaGuardado = Integer.parseInt(dato.substring(0, dato.indexOf("-")));
                    if (diaGuardado == dia) {
                        int cantidad = Integer.parseInt(dato.substring(dato.indexOf("-") + 1)) + 1;
                        nodoResumen.setDato(dia + "-" + cantidad);
                        encontrado = true;
                        break;
                    } else if (diaGuardado > dia) {
                        break;
                    }
                    anterior = nodoResumen;
                    nodoResumen = nodoResumen.getSiguiente();
                }

                if (!encontrado) {
                    Nodo<String> nuevoNodo = new Nodo<>();
                    nuevoNodo.setDato(dia + "-1");
                    nuevoNodo.setSiguiente(nodoResumen);

                    if (anterior == null) {
                        resumen.setInicio(nuevoNodo);
                    } else {
                        anterior.setSiguiente(nuevoNodo);
                    }
                }
            }

            actual = actual.getSiguiente();
        }

        r.valorString = resumen.mostrar();
        return r;
    }

}
