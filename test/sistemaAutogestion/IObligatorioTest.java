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

        // Ahora Laura debería tener la entrada (fue reasignada automáticamente)
        Retorno r4 = miSistema.devolverEntrada("87654321", "EVT1");
        assertEquals(Retorno.Resultado.OK, r4.resultado);
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
