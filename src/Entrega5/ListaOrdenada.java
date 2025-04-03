package Entrega5;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
    public ListaOrdenada(Comparator<E> comparator);
    public static <E> ListaOrdenada<E> of(Comparator<E> comparator);
    private int indexOrder(E e);
    @Override
    public void add(E e);
}
