/*
  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
  * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sistemaAutogestion;

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

    }

    @Test
    public void testRegistrarSalaError1() {

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.OK, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarSala("Sala Norte", 100);
        assertEquals(Retorno.Resultado.ERROR_1, resultado2.resultado);

    }

    @Test
    public void testRegistrarSalaError2() {

        Retorno resultado1 = miSistema.registrarSala("Sala Norte", 0);
        assertEquals(Retorno.Resultado.ERROR_2, resultado1.resultado);

        Retorno resultado2 = miSistema.registrarSala("Sala Sur", -100);
        assertEquals(Retorno.Resultado.ERROR_2, resultado2.resultado);

    }

    // Requerimiento 1.3
    @Test
    public void testEliminarSalaOK() {
        // Caso 1: eliminar una sala existente
        Retorno r1 = miSistema.registrarSala("Sala Este", 80);
        assertEquals(Retorno.Resultado.OK, r1.resultado);

        Retorno r2 = miSistema.eliminarSala("Sala Este");
        assertEquals(Retorno.Resultado.OK, r2.resultado);
    }

    @Test
    public void testEliminarSalaError1() {
        // Caso 2: eliminar una sala que no existe
        Retorno r = miSistema.eliminarSala("Sala Norte");
        assertEquals(Retorno.Resultado.ERROR_1, r.resultado);

        // Caso 3: eliminar con nombre inválido (solo espacios) - Para validar el trim()
        Retorno r2 = miSistema.eliminarSala("   ");
        assertEquals(Retorno.Resultado.ERROR_1, r2.resultado);
    }

    // Requerimiento 1.4
    // Este falta completarlo en sistema y en test
    @Test
    public void testRegistrarEvento() {
        //Completar para primera entrega
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
    }

    @Test
    public void testRegistrarClienteError2() {
        // Primero lo registramos
        Retorno r1 = miSistema.registrarCliente("12345678", "Ana");
        assertEquals(Retorno.Resultado.OK, r1.resultado);

        // Intentamos registrar la misma cédula
        Retorno r2 = miSistema.registrarCliente("12345678", "Ana");
        assertEquals(Retorno.Resultado.ERROR_2, r2.resultado);
    }

    @Test
    public void testListarSalas() {
        //Completar para primera entrega
    }

    @Test
    public void testListarEventos() {
        //Completar para primera entrega
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
    }

}
