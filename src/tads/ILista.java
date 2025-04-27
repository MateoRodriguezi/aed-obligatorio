
package tads;

/**
 *
 * @author mateorodriguez
 */
public interface ILista<T> {


    public boolean esVacia();

    public void agregarInicio(T elemento);

    public void agregarFinal(T elemento);

    public void borrarInicio();

    public void borrarFinal();

    public void vaciar();

    public void mostrar();

    public T obtenerElemento(int indice);

    public void insertarOrdenado(T dato);
    
}
