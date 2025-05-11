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
        Retorno resultado = miSistema.registrarCliente("12345", "Juan"); // menos de 8 dígitos
        assertEquals(Retorno.Resultado.ERROR_1, resultado.resultado);
        
        Retorno resultado2 = miSistema.registrarCliente("123451596123", "Pedro"); // más de 8 dígitos
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.resultado);
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
    public void testRegistrarSala() {
        Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	Retorno ret2 = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret2.resultado);
    }

    @Test
    public void testRegistrarSala_ERROR1() {
	Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

 	ret = miSistema.registrarSala("Sala B", 10);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.registrarSala("Sala A", 100);
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testRegistrarSala_ERROR2() {

	Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        Retorno ret2 = miSistema.registrarSala("Sala B", 0);
        assertEquals(Retorno.Resultado.ERROR_2, ret2.resultado);

	Retorno ret3 = miSistema.registrarSala("Sala B", -10);
        assertEquals(Retorno.Resultado.ERROR_2, ret3.resultado);
    }

    @Test
    public void testEliminarSala() {
        Retorno ret = miSistema.registrarSala("Sala A", 50);
        assertEquals(Retorno.Resultado.OK, ret.resultado);

        ret = miSistema.eliminarSala("Sala A");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarEvento2() {
        Retorno ret = miSistema.registrarSala("Sala A", 100);
	assertEquals(Retorno.Resultado.OK, ret.resultado);

        LocalDate fecha = LocalDate.of(2025, 5, 10);
        Retorno ret2 = miSistema.registrarEvento("EVT01", "Concierto", 80, fecha);
        assertEquals(Retorno.Resultado.OK, ret2.resultado);
    }

    @Test
    public void testRegistrarCliente() {
        Retorno ret = miSistema.registrarCliente("12345678", "Juan Pérez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);

	ret = miSistema.registrarCliente("12345444", "Martina Gutierrez");
        assertEquals(Retorno.Resultado.OK, ret.resultado);
    }

    @Test
    public void testRegistrarCliente_ERROR1() {
        Retorno ret = miSistema.registrarCliente("1234", "Juan Pérez");
        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);

//	ret = miSistema.registrarCliente("AA345444", "Martina Gutierrez");
//        assertEquals(Retorno.Resultado.ERROR_1, ret.resultado);
    }

    @Test
    public void testListarSalas2() {
        miSistema.registrarSala("Sala A", 50);
        miSistema.registrarSala("Sala B", 70);
        miSistema.registrarSala("Sala C", 100);
        Retorno ret = miSistema.listarSalas();
        assertEquals(Retorno.Resultado.OK, ret.resultado);
        assertEquals("Sala C-100#Sala B-70#Sala A-50", ret.valorString);
    }

    @Test
    public void testListarClientes() {
        miSistema.registrarCliente("45678992", "Micaela Ferrez");
    	miSistema.registrarCliente("23331111", "Martina Rodríguez");
    	miSistema.registrarCliente("35679992", "Ramiro Perez");

    	Retorno ret = miSistema.listarClientes();
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("23331111-Martina Rodríguez#35679992-Ramiro Perez#45678992-Micaela Ferrez", ret.valorString);
    }

    @Test
    public void testEsSalaOptima2() {
        String[][] vistaSala = {
        {"#", "#", "#", "#", "#", "#", "#"},
        {"#", "#", "X", "X", "X", "X", "#"},
        {"#", "O", "O", "X", "X", "X", "#"},
        {"#", "O", "O", "O", "O", "X", "#"},
        {"#", "O", "O", "X", "O", "O", "#"},
        {"#", "O", "O", "O", "O", "O", "#"},
        {"#", "X", "X", "O", "O", "O", "O"},
        {"#", "X", "X", "O", "O", "O", "X"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "X", "X", "O", "X", "X", "#"},
        {"#", "#", "#", "O", "#", "#", "#"},
        {"#", "#", "#", "O", "#", "#", "#"}
    	};


	Retorno ret = miSistema.esSalaOptima(vistaSala);
    	assertEquals(Retorno.Resultado.OK, ret.resultado);
    	assertEquals("Es óptimo", ret.valorString);
    }

}
