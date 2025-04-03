package Entrega5;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {
    public ListaOrdenadaSinRepeticion(Comparator<E> comparator);
    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator);
    @Override
    public void add(E e);
}
