/*
  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
  * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pesce
 */
public class IObligatorioTest {

    private Sistema miSistema;

    public IObligatorioTest() {
    }

    @Before
    public void setUp() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    // Requerimiento 1.1
    @Test
    public void testCrearSistemaDeGestion() {
        miSistema = new Sistema();
        miSistema.crearSistemaDeGestion();
    }

    // Requerimiento 1.2
    @Test
    public void testRegistrarSalaOK() {
        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarSala("Sala Este", 500);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarSala("Sala Sur", 20);
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.registrarSala("Sala Oeste", 250);
        assertEquals(Retorno.Resultado.OK, resultado4.resultado);

    }

    @Test
    public void testRegistrarSalaError1() {

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarSala("Sala norte", 500);
        assertEquals(Retorno.Resultado.ERROR_1, resultado3.resultado);

    }

    @Test
    public void testRegistrarSalaError2() {

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 0);
        assertEquals(Retorno.Resultado.ERROR_2, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarSala("Sala Sur", -100);
        assertEquals(Retorno.Resultado.ERROR_2, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarSala("Sala Este", -1);
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.resultado);

    }

    // Requerimiento 1.3
    @Test
    public void testEliminarSalaOK() {
        // Caso 1: eliminar una sala existente
        Retorno resultado1 = miSistema.registrarSala("Sala Este", 80);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        Retorno resultado2 = miSistema.eliminarSala("Sala Este");
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.eliminarSala("sala norte");
        assertEquals(Retorno.Resultado.OK, resultado4.resultado);
    }

    @Test
    public void testEliminarSalaError1() {
        // Caso 2: eliminar una sala que no existe
        Retorno resultado1 = miSistema.eliminarSala("Sala Norte");
        assertEquals(Retorno.Resultado.ERROR_1, resultado1.resultado);

        // Caso 3: eliminar con nombre inválido (solo espacios) - Para validar el trim()
        Retorno resultado2 = miSistema.eliminarSala("   ");
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.resultado);
    }

    // Requerimiento 1.4
    // Este falta completarlo en sistema y en test
    @Test
    public void testRegistrarEvento() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);
        //----------------------------------------------------------------------
        Retorno resultado3 = miSistema.registrarCliente("19345698", "Mateo");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.registrarSala("Sala Sur", 100);
        assertEquals(Retorno.Resultado.OK, resultado4.resultado);

        LocalDate fecha2 = LocalDate.parse("2025-11-11");
        Retorno resultado5 = miSistema.registrarEvento("Evento 2", "Expo", 100, fecha2);
        assertEquals(Retorno.Resultado.OK, resultado5.resultado);
    }

    @Test
    public void testRegistrarEventoError1() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno r = miSistema.registrarSala("Sala Norte", 100);
        Retorno r2 = miSistema.registrarSala("Sala Sur", 200);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        Retorno resultado3 = miSistema.registrarEvento("Evento 1", "Concierto", 100, fecha);
        Retorno resultado4 = miSistema.registrarEvento("evento 1", "Concierto", 100, fecha);

        assertEquals(Retorno.Resultado.ERROR_1, resultado3.resultado);
        assertEquals(Retorno.Resultado.ERROR_1, resultado4.resultado);

    }

    @Test
    public void testRegistrarEventoError2() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno r = miSistema.registrarSala("Sala Norte", 100);
        Retorno r2 = miSistema.registrarSala("Sala Sur", 200);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 0, fecha);
        Retorno resultado3 = miSistema.registrarEvento("Evento 10", "Carreras F1", -10, fecha);

        assertEquals(Retorno.Resultado.ERROR_2, resultado2.resultado);
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.resultado);

    }

    @Test
    public void testRegistrarEventoError3() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno r = miSistema.registrarSala("Sala Norte", 100);
        Retorno r2 = miSistema.registrarSala("Sala Sur", 200);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals(Retorno.Resultado.OK, r2.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");

        //Caso NO quedan salas
        Retorno resultado1 = miSistema.registrarEvento("Evento 1", "Carreras F1", 20, fecha);
        Retorno resultado2 = miSistema.registrarEvento("Evento 2", "Concierto", 20, fecha);
        Retorno resultado3 = miSistema.registrarEvento("Evento 3", "Expo", 20, fecha);

        assertEquals(Retorno.Resultado.ERROR_3, resultado3.resultado);

        Retorno r3 = miSistema.registrarSala("Sala Este", 100);
        assertEquals(Retorno.Resultado.OK, r3.resultado);
        //Caso no alcanza la capacidad
        Retorno resultado4 = miSistema.registrarEvento("Evento 4", "Expo", 200, fecha);
        Retorno resultado5 = miSistema.registrarEvento("Evento 5", "Expo", 101, fecha);

        assertEquals(Retorno.Resultado.ERROR_3, resultado4.resultado);
        assertEquals(Retorno.Resultado.ERROR_3, resultado5.resultado);
    }

    // Requerimiento 1.5
    @Test
    public void testRegistrarClienteOk() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Mateo");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado2 = miSistema.registrarCliente("98765432", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);
    }

    @Test
    public void testRegistrarClienteError1() {
        Retorno resultado = miSistema.registrarCliente("12345", "Juan");
        assertEquals(Retorno.Resultado.ERROR_1, resultado.resultado);

        Retorno resultado2 = miSistema.registrarCliente("123451596123", "Pedro");
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarCliente("ABCD1234", "Pedro");
        assertEquals(Retorno.Resultado.ERROR_1, resultado3.resultado);
    }

    @Test
    public void testRegistrarClienteError2() {
        Retorno resultado1 = miSistema.registrarCliente("12345678", "Ana");
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarCliente("12345678", "María");
        assertEquals(Retorno.Resultado.ERROR_2, resultado2.resultado);

        Retorno resultado3 = miSistema.registrarCliente("87654321", "Pedro");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.registrarCliente("87654321", "Mauro");
        assertEquals(Retorno.Resultado.ERROR_2, resultado4.resultado);
    }

    // Requerimiento 1.6
    @Test
    public void testComprarEntradaOk() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.comprarEntrada("12345678", "Evento 1");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);
    }

    @Test
    public void testComprarEntradaError1() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.comprarEntrada("456789", "Evento 1");
        assertEquals(Retorno.Resultado.ERROR_1, resultado3.resultado);

        Retorno resultado4 = miSistema.comprarEntrada("pruebaError1", "Evento 1");
        assertEquals(Retorno.Resultado.ERROR_1, resultado4.resultado);

    }

    @Test
    public void testComprarEntradaError2() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.comprarEntrada("12345678", "Evento 12");
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.resultado);

        Retorno resultado4 = miSistema.comprarEntrada("12345678", "Evento1");
        assertEquals(Retorno.Resultado.ERROR_2, resultado4.resultado);

    }

    // Requerimiento 1.7
    @Test
    public void testEliminarEventoOK() {
        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.eliminarEvento("Evento 1");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

    }

    @Test
    public void testEliminarEventoError1() {
        Retorno resultado = miSistema.eliminarEvento("evento inexistente");
        assertEquals(Retorno.Resultado.ERROR_1, resultado.resultado);
    }

    @Test
    public void testEliminarEventoError2() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.comprarEntrada("12345678", "Evento 1");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.eliminarEvento("Evento 1");
        assertEquals(Retorno.Resultado.ERROR_2, resultado4.resultado);

    }

    // Requerimiento 1.8
    @Test
    public void testDevolverEntradaOK() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarCliente("87654321", "Laura");

        miSistema.registrarSala("Sala Norte", 1);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 1, fecha);

        // Mateo compra la única entrada
        Retorno r1 = miSistema.comprarEntrada("12345678", "EVT1");
        assertEquals(Retorno.Resultado.OK, r1.resultado);

        // Laura intenta comprar pero se encola
        Retorno r2 = miSistema.comprarEntrada("87654321", "EVT1");
        assertEquals(Retorno.Resultado.OK, r2.resultado);

        // Mateo devuelve la entrada
        Retorno r3 = miSistema.devolverEntrada("12345678", "EVT1");
        assertEquals(Retorno.Resultado.OK, r3.resultado);

        // Verificar que Laura recibió la entrada de EVT1
        Retorno r5 = miSistema.comprasDeCliente("87654321");
        System.out.println("Compras de Laura: " + r5.valorString);

        assertEquals(Retorno.Resultado.OK, r5.resultado);
        assertTrue("Laura debería haber recibido la entrada de EVT1", r5.valorString.contains("EVT1-N"));
    }

    @Test
    public void testDevolverEntradaError1() {
        // No registramos al cliente

        // Registramos evento y sala
        miSistema.registrarSala("Sala 1", 10);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 5, fecha);

        // Intentamos devolver entrada con cliente no existente
        Retorno r = miSistema.devolverEntrada("99999999", "EVT1");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testDevolverEntradaError2() {
        // Registramos cliente pero no el evento
        miSistema.registrarCliente("12345678", "Mateo");

        // Intentamos devolver entrada para evento inexistente
        Retorno r = miSistema.devolverEntrada("12345678", "NOEXISTE");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

//    -----------
    // Requerimiento 1.9
    @Test
    public void testCalificarEventoError1() {
        miSistema.registrarSala("Sala 1", 50);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 10, fecha);

        Retorno r = miSistema.calificarEvento("12345678", "EVT1", 8, "Muy bueno");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testCalificarEventoError2() {
        miSistema.registrarCliente("12345678", "Mateo");

        Retorno r = miSistema.calificarEvento("12345678", "NOEXISTE", 7, "Estuvo bien");
        assertEquals(Retorno.Resultado.ERROR_2, r.resultado);
    }

    @Test
    public void testCalificarEventoError3() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarSala("Sala 1", 30);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 10, fecha);

        // Puntaje menor a 1
        Retorno r1 = miSistema.calificarEvento("12345678", "EVT1", 0, "Malo");
        assertEquals(Retorno.Resultado.ERROR_3, r1.resultado);

        // Puntaje mayor a 10
        Retorno r2 = miSistema.calificarEvento("12345678", "EVT1", 11, "Excelente");
        assertEquals(Retorno.Resultado.ERROR_3, r2.resultado);
    }

    @Test
    public void testCalificarEventoError4() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarSala("Sala 1", 30);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 10, fecha);

        // Califica por primera vez
        Retorno r1 = miSistema.calificarEvento("12345678", "EVT1", 9, "Muy bueno");
        assertEquals(Retorno.Resultado.OK, r1.resultado);

        // Intenta volver a calificar
        Retorno r2 = miSistema.calificarEvento("12345678", "EVT1", 6, "Cambio de opinión");
        assertEquals(Retorno.Resultado.ERROR_4, r2.resultado);
    }

    @Test
    public void testCalificarEventoOK() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarSala("Sala 1", 30);
        LocalDate fecha = LocalDate.parse("2025-10-10");
        miSistema.registrarEvento("EVT1", "Evento Test", 10, fecha);

        Retorno r = miSistema.calificarEvento("12345678", "EVT1", 8, "Estuvo excelente");
        assertEquals(Retorno.Resultado.OK, r.resultado);
    }

//    -----------
// Requerimiento 2.1
    @Test
    public void testListarSalas() {
        miSistema.registrarSala("Sala Verde", 45);
        miSistema.registrarSala("Sala Azul", 20);
        miSistema.registrarSala("Sala Violeta", 67);

        Retorno ret = miSistema.listarSalas();

        assertEquals(Retorno.Resultado.OK, ret.resultado);

        String esperado = "Sala Violeta-67#Sala Azul-20#Sala Verde-45";
        assertEquals(esperado, ret.valorString);
    }

    // Requerimiento 2.2
    @Test
    public void testListarEventos() {
        // Primero registramos las salas necesarias
        miSistema.registrarSala("Sala Azul", 20);
        miSistema.registrarSala("Sala Violeta", 67);
        miSistema.registrarSala("Sala Verde", 45);

        // Registramos eventos (en desorden para verificar ordenación por código)
        miSistema.registrarEvento("TEC43", "Seminario de Tecnología", 45, LocalDate.of(2025, 5, 10));
        miSistema.registrarEvento("CUC22", "Tango Azul", 20, LocalDate.of(2025, 5, 11));
        miSistema.registrarEvento("KAK34", "Noche de Rock", 30, LocalDate.of(2025, 5, 12));

        // Ejecutamos el método a testear
        Retorno ret = miSistema.listarEventos();

        // Validamos resultado OK
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        // Y el string generado debe estar ordenado por código
        String esperado = "CUC22-Tango Azul-20-20-0#KAK34-Noche de Rock-45-45-0#TEC43-Seminario de Tecnología-45-45-0";
        assertEquals(esperado, ret.valorString);
    }

    // Requerimiento 2.3
    @Test
    public void testListarClientesOK() {

        miSistema.registrarCliente("45678992", "Micaela Ferrez");
        miSistema.registrarCliente("47489126", "Mateo Rodriguez");
        miSistema.registrarCliente("12345678", "Nicolas Cardona");
        miSistema.registrarCliente("35679992", "Ramiro Perez");
        miSistema.registrarCliente("23331118", "Martina Bustos");

        Retorno ret = miSistema.listarClientes();

        assertEquals(Retorno.Resultado.OK, ret.resultado);

        String esperado = "12345678-Nicolas Cardona#23331118-Martina Bustos#35679992-Ramiro Perez#45678992-Micaela Ferrez#47489126-Mateo Rodriguez";
        assertEquals(esperado, ret.valorString);
    }

    // Requerimiento 2.4
    @Test
    public void testEsSalaOptima() {
        // Matriz del ejemplo del  obligatorio
        String[][] vista = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "X", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno ret = miSistema.esSalaOptima(vista);

        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Es óptimo", ret.valorString);

        String[][] vista2 = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "#"},
            {"#", "X", "X", "X", "O", "O", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno ret2 = miSistema.esSalaOptima(vista2);

        assertEquals(Retorno.Resultado.OK, ret2.resultado);
        assertEquals("Es óptimo", ret2.valorString);

    }

    @Test
    public void testNoEsSalaOptima() {
        // Matriz del ejemplo del  obligatorio
        String[][] vista = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "X", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "X", "O", "#"},
            {"#", "O", "O", "O", "X", "O", "#"},
            {"#", "X", "X", "X", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "#"},
            {"#", "X", "X", "X", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "X", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno ret = miSistema.esSalaOptima(vista);

        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("No es óptimo", ret.valorString);

        String[][] vista2 = {
            {"#", "#", "#", "#", "#", "#", "#"},
            {"#", "#", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "X", "X", "X", "#"},
            {"#", "O", "O", "O", "O", "X", "#"},
            {"#", "O", "O", "X", "O", "O", "#"},
            {"#", "O", "O", "O", "O", "O", "#"},
            {"#", "X", "X", "O", "O", "O", "#"},
            {"#", "X", "X", "X", "X", "O", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "X", "X", "O", "X", "X", "#"},
            {"#", "#", "#", "O", "#", "#", "#"},
            {"#", "#", "#", "O", "#", "#", "#"}
        };

        Retorno ret2 = miSistema.esSalaOptima(vista2);

        assertEquals(Retorno.Resultado.OK, ret2.resultado);
        assertEquals("No es óptimo", ret2.valorString);
    }

    // Requerimiento 2.5
    @Test
    public void testListarClientesDeEventoOk() {
        Retorno resultado = miSistema.registrarCliente("12345678", "Nicolas");
        assertEquals(Retorno.Resultado.OK, resultado.resultado);
        miSistema.registrarCliente("12345679", "Mateo");

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.comprarEntrada("12345678", "Evento 1");
        assertEquals(Retorno.Resultado.OK, resultado3.resultado);

        Retorno resultado4 = miSistema.comprarEntrada("12345679", "Evento 1");
        assertEquals(Retorno.Resultado.OK, resultado4.resultado);

        Retorno resultado5 = miSistema.listarClientesDeEvento("Evento 1", 2);
        String esperado = "12345679-Mateo#12345678-Nicolas";
        assertEquals(esperado, resultado5.valorString);
    }

    @Test
    public void testListarClientesDeEventoError1() {
        Retorno r = miSistema.listarClientesDeEvento("NoExiste", 6);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);

    }

    @Test
    public void testListarClientesDeEventoError2() {
        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        LocalDate fecha = LocalDate.parse("2025-05-03");
        Retorno resultado2 = miSistema.registrarEvento("Evento 1", "Carreras F1", 100, fecha);
        assertEquals(Retorno.Resultado.OK, resultado2.resultado);

        Retorno resultado3 = miSistema.listarClientesDeEvento("Evento 1", 0);
        assertEquals(Retorno.Resultado.ERROR_2, resultado3.resultado);

        Retorno resultado4 = miSistema.listarClientesDeEvento("Evento 1", -1);
        assertEquals(Retorno.Resultado.ERROR_2, resultado4.resultado);
    }

    // Requerimiento 2.6
    @Test
    public void testListarEsperaEventoOK() {
        // Registramos clientes
        miSistema.registrarCliente("45678992", "Micaela");
        miSistema.registrarCliente("23331118", "Martina");
        miSistema.registrarCliente("35679992", "Ramiro");

        // Registramos salas
        miSistema.registrarSala("Sala 1", 1); // solo una entrada disponible
        miSistema.registrarSala("Sala 2", 1); // solo una entrada disponible

        // Registramos eventos (los códigos sean distintos y desordenados)
        miSistema.registrarEvento("KAK34", "Rock Fest", 1, LocalDate.of(2025, 10, 10));
        miSistema.registrarEvento("TEC43", "Tech Expo", 1, LocalDate.of(2025, 10, 15));

        // Un cliente compra la única entrada en cada evento
        miSistema.comprarEntrada("45678992", "KAK34");
        miSistema.comprarEntrada("23331118", "TEC43");

        // Otro cliente intenta comprar y queda en lista de espera
        miSistema.comprarEntrada("23331118", "KAK34"); // encola a Martina
        miSistema.comprarEntrada("35679992", "TEC43"); // encola a Ramiro

        Retorno resultado = miSistema.listarEsperaEvento();

        assertEquals(Retorno.Resultado.OK, resultado.resultado);

        String esperado = "KAK34-23331118#TEC43-35679992";
        assertEquals(esperado, resultado.valorString);
    }

    @Test
    public void testListarEsperaEventoSinEspera() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarSala("Sala A", 50);
        miSistema.registrarEvento("AAA01", "Evento sin espera", 50, LocalDate.of(2025, 11, 1));
        miSistema.comprarEntrada("12345678", "AAA01");

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString); // no debe listar nada
    }

    @Test
    public void testListarEsperaEventoMultiplesClientes() {
        miSistema.registrarCliente("12345678", "Mateo");
        miSistema.registrarCliente("22345678", "Nicolas");
        miSistema.registrarCliente("32345678", "Laura");
        miSistema.registrarCliente("42345678", "Martina");
        miSistema.registrarCliente("47489126", "Juan");

        miSistema.registrarSala("Sala A", 1);
        miSistema.registrarEvento("EVT9", "Evento", 1, LocalDate.of(2025, 10, 1));

        miSistema.comprarEntrada("12345678", "EVT9"); // único lugar
        miSistema.comprarEntrada("22345678", "EVT9"); // espera
        miSistema.comprarEntrada("32345678", "EVT9"); // espera
        miSistema.comprarEntrada("42345678", "EVT9"); // espera
        miSistema.comprarEntrada("47489126", "EVT9"); // espera

        Retorno listar = miSistema.listarClientes();
        System.out.println("Clientes registrados: " + listar.valorString);

        Retorno r = miSistema.listarEsperaEvento();

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("EVT9-22345678#EVT9-32345678#EVT9-42345678#EVT9-47489126", r.valorString);
    }

    // Requerimiento 2.7
    @Test
    public void testDeshacerUltimasComprasOK() {

        // Registramos una sala con capacidad 2
        miSistema.registrarSala("Sala Test", 2);

        // Creamos un evento con aforo 2, para usar la sala anterior
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        miSistema.registrarEvento("EVT10", "Callejero Fino", 2, fecha);

        // Registramos 3 clientes
        miSistema.registrarCliente("11111111", "Cliente1");
        miSistema.registrarCliente("22222222", "Cliente2");
        miSistema.registrarCliente("33333333", "Cliente3");

        // Dos clientes compran entrada, el tercero queda en lista de espera
        miSistema.comprarEntrada("11111111", "EVT10"); // entra
        miSistema.comprarEntrada("22222222", "EVT10"); // entra
        miSistema.comprarEntrada("33333333", "EVT10"); // espera

        // Deshacemos las 2 últimas compras (las dos primeras que habían entrado)
        Retorno r = miSistema.deshacerUtimasCompras(2);

        // Comprobamos que el retorno sea OK
        assertEquals(Retorno.Resultado.OK, r.resultado);

        // El resultado tiene que contener los dos códigos de evento-cliente deshechos, en orden alfabético
        String esperado1 = "EVT10-11111111#EVT10-22222222";
        String esperado2 = "EVT10-22222222#EVT10-11111111";

        System.out.println("valorString retornado: '" + r.valorString + "'");

        assertTrue("El valorString retornado fue: " + r.valorString,
                r.valorString.equals(esperado1) || r.valorString.equals(esperado2));
    }

    @Test
    public void testDeshacerMasQueExistentes() {
        miSistema.registrarSala("Sala X", 2);
        miSistema.registrarEvento("EVT01", "Evento Prueba", 2, LocalDate.of(2025, 12, 25));
        miSistema.registrarCliente("11111111", "Cliente Uno");
        miSistema.comprarEntrada("11111111", "EVT01");

        Retorno r = miSistema.deshacerUtimasCompras(5); // solo hay 1 compra

        System.out.println("valorString retornado testDeshacerMasQueExistentes: '" + r.valorString + "'");

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("EVT01-11111111", r.valorString);
    }

    @Test
    public void testDeshacerCeroCompras() {
        miSistema.registrarSala("Sala X", 2);
        miSistema.registrarEvento("EVT02", "Evento", 2, LocalDate.of(2025, 12, 25));
        miSistema.registrarCliente("22222222", "Cliente Dos");
        miSistema.comprarEntrada("22222222", "EVT02");

        Retorno r = miSistema.deshacerUtimasCompras(0);

        System.out.println("valorString retornado testDeshacerCeroCompras: '" + r.valorString + "'");

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString); // no se deshizo ninguna compra
    }

    // Requerimiento 2.8
    @Test
    public void testEventoMejorPuntuadoSinEventos() {
        miSistema.crearSistemaDeGestion();
        Retorno r = miSistema.eventoMejorPuntuado();
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    // Requerimiento 2.8
    @Test
    public void testEventoMejorPuntuadoUnico() {
        miSistema.crearSistemaDeGestion();
        miSistema.registrarSala("SalaA", 100);
        miSistema.registrarEvento("E001", "Rock", 80, LocalDate.of(2025, 12, 1));
        miSistema.registrarCliente("11111111", "Ana");

        miSistema.calificarEvento("11111111", "E001", 9, "Muy bueno");

        Retorno r = miSistema.eventoMejorPuntuado();

        System.out.println("Valor retornado testEventoMejorPuntuadoUnico: '" + r.valorString + "'");

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("E001-9", r.valorString);
    }

    @Test
    public void testEventoMejorPuntuadoUnoSuperior() {
        miSistema.crearSistemaDeGestion();
        miSistema.registrarSala("SalaA", 100);
        miSistema.registrarEvento("E001", "Rock", 80, LocalDate.of(2025, 12, 1));
        miSistema.registrarEvento("E002", "Jazz", 80, LocalDate.of(2025, 12, 2));

        miSistema.registrarCliente("11111111", "Ana");
        miSistema.registrarCliente("22222222", "Luis");

        miSistema.calificarEvento("11111111", "E001", 9, "Muy bueno");
        miSistema.calificarEvento("22222222", "E002", 7, "Regular");

        Retorno r = miSistema.eventoMejorPuntuado();

        System.out.println("Valor retornado testEventoMejorPuntuadoUnoSuperior: '" + r.valorString + "'");

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("E001-9", r.valorString);
    }

    @Test
    public void testEventoMejorPuntuadoEmpate() {
        miSistema.crearSistemaDeGestion();
        miSistema.registrarSala("SalaA", 100);
        miSistema.registrarEvento("E001", "Rock", 80, LocalDate.of(2025, 12, 1));
        miSistema.registrarEvento("E002", "Jazz", 80, LocalDate.of(2025, 12, 2));
        miSistema.registrarEvento("E003", "Pop", 80, LocalDate.of(2025, 12, 3));

        miSistema.registrarCliente("11111111", "Ana");
        miSistema.registrarCliente("22222222", "Luis");
        miSistema.registrarCliente("33333333", "Sara");

        miSistema.calificarEvento("11111111", "E001", 9, "Muy bueno");
        miSistema.calificarEvento("22222222", "E002", 9, "Muy bueno");
        miSistema.calificarEvento("33333333", "E003", 7, "Buena");

        Retorno r = miSistema.eventoMejorPuntuado();

        System.out.println("Valor retornado testEventoMejorPuntuadoEmpate: '" + r.valorString + "'");

        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("E001-9#E002-9", r.valorString); // orden alfabético
    }

    // Requerimiento 2.9
    @Test
    public void testComprasDeClienteOK() {
        miSistema.registrarCliente("11111111", "Mateo");
        miSistema.registrarSala("Sala 1", 5);
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        miSistema.registrarEvento("EVT1", "Concierto", 1, fecha);
        miSistema.comprarEntrada("11111111", "EVT1");

        Retorno r = miSistema.comprasDeCliente("11111111");

        System.out.println("Test testComprasDeClienteOK - valorString: '" + r.valorString);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("EVT1-N", r.valorString);
    }

    @Test
    public void testClienteSinCompras() {
        miSistema.registrarCliente("11111111", "Mateo");

        Retorno r = miSistema.comprasDeCliente("11111111");

        System.out.println("Test sin compras - valorString: '" + r.valorString + "'");
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("", r.valorString);
    }

    @Test
    public void testComprasConDevolucion() {
        miSistema.registrarCliente("11111111", "Mateo");
        miSistema.registrarSala("Sala 1", 5);
        LocalDate fecha = LocalDate.of(2025, 12, 25);
        miSistema.registrarEvento("EVT1", "Concierto", 2, fecha);

        miSistema.comprarEntrada("11111111", "EVT1");
        miSistema.comprarEntrada("11111111", "EVT1");

        miSistema.devolverEntrada("11111111", "EVT1");

        Retorno r = miSistema.comprasDeCliente("11111111");

        System.out.println("Test con devoluciones - valorString: " + r.valorString);
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertTrue(r.valorString.contains("EVT1-N"));
        assertTrue(r.valorString.contains("EVT1-D"));
    }

    @Test
    public void testComprasMultiplesEventos() {
        miSistema.registrarCliente("22222222", "Martu");
        miSistema.registrarSala("Sala A", 3);
        miSistema.registrarSala("Sala B", 3);

        LocalDate fecha1 = LocalDate.of(2025, 10, 10);
        LocalDate fecha2 = LocalDate.of(2025, 11, 15);

        miSistema.registrarEvento("TEC43", "Tech Talk", 1, fecha1);
        miSistema.registrarEvento("CUC11", "Cultura Show", 1, fecha2);

        miSistema.comprarEntrada("22222222", "TEC43");
        miSistema.comprarEntrada("22222222", "CUC11");

        miSistema.devolverEntrada("22222222", "CUC11");

        Retorno r = miSistema.comprasDeCliente("22222222");

        System.out.println("Test múltiples eventos - valorString: " + r.valorString);

        // Verificamos formato esperado
        assertEquals(Retorno.Resultado.OK, r.resultado);
        assertEquals("TEC43-N#CUC11-D", r.valorString);
    }

    @Test
    public void testComprasDeClienteError1() {

        Retorno r = miSistema.comprasDeCliente("99999999");

        System.out.println("Test cliente inexistente - valorString: " + r.valorString);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    // Requerimiento 2.10
    @Test
    public void testComprasXDiaDosComprasMismoDia() {
        miSistema.registrarSala("SalaX", 10);
        miSistema.registrarCliente("12345678", "Ana");
        miSistema.registrarCliente("98765432", "Juan"); // ← esto faltaba
        miSistema.registrarEvento("EVT1", "Evento Test", 5, LocalDate.now());

        // Dos compras válidas
        miSistema.comprarEntrada("12345678", "EVT1");
        miSistema.comprarEntrada("98765432", "EVT1");

        int mesActual = LocalDate.now().getMonthValue();
        int diaActual = LocalDate.now().getDayOfMonth();

        Retorno r = miSistema.comprasXDia(mesActual);
        System.out.println("Resultado comprasXDia: " + r.valorString);

        assertEquals(diaActual + "-2", r.valorString);
    }

    @Test
    public void testComprasXDiaMesInvalido() {
        Retorno r = miSistema.comprasXDia(0);
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);
    }

    @Test
    public void testEliminarSalaRemueveSalaDelListado() {
        miSistema.registrarSala("Sala Fantasma", 50);

        Retorno listadoAntes = miSistema.listarSalas();
        System.out.println("ANTES de eliminar: " + listadoAntes.valorString);
        assertTrue(listadoAntes.valorString.toLowerCase().contains("sala fantasma"));

        miSistema.eliminarSala("Sala Fantasma");

        Retorno listadoDespues = miSistema.listarSalas();
        System.out.println("DESPUÉS de eliminar: " + listadoDespues.valorString);
        assertFalse(listadoDespues.valorString.toLowerCase().contains("sala fantasma"));
    }
}
