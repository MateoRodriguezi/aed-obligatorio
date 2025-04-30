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
 
     @Test
     public void testCrearSistemaDeGestion() {
         //Completar para primera entrega
     }
 
     @Test
     public void testRegistrarSala() {
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
        
        Retorno resultado2 = miSistema.registrarSala("Sala Sur",-100);
        assertEquals(Retorno.Resultado.ERROR_2, resultado2.resultado);
      
     }
     
     
 
     @Test
     public void testEliminarSala() {
         //Completar para primera entrega
     }
 
     @Test
     public void testRegistrarEvento() {
         //Completar para primera entrega
     }
 
     @Test
     public void testRegistrarCliente() {
         //Completar para primera entrega
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
     public void testListarClientes() {
         //Completar para primera entrega
     }
 
     @Test
     public void testEsSalaOptima() {
         //Completar para primera entrega
     }
 
 }