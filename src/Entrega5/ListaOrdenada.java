
package Entrega5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListaOrdenada<E> extends AgregadoLineal<E> implements Iterable<E> {

    private Comparator<E> comparator;
    protected List<E> Elementos;

    public ListaOrdenada(Comparator<E> comparator) {
        this.comparator = comparator;
        this.Elementos = new ArrayList<>();
    }

    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
        return new ListaOrdenada<>(comparator);
    }

    public void add(E e) {
        int index = indexOrder(e);
        Elementos.add(index, e);
    }

    private int indexOrder(E e) {
        for (int i = 0; i < Elementos.size(); i++) {
            if (comparator.compare(e, Elementos.get(i)) < 0) {
                return i;
            }
        }
        return Elementos.size();
    }

    @Override
    public Iterator<E> iterator() {
        return Elementos.iterator();
    }



    
   
    public static void main(String[] args) {
        // Test with integers
    	Comparator<Integer> intComparator = Integer::compareTo;
        ListaOrdenada<Integer> lista = ListaOrdenada.of(intComparator);

        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3");
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(3);

        System.out.println("Elementos en la lista: " + lista.Elementos);
        System.out.println("Tamaño de la lista: " + lista.Elementos.size());

        System.out.println("Eliminando el primer elemento: " + lista.Elementos.remove(0));
        System.out.println("Elementos después de eliminar: " + lista.Elementos);

        System.out.println("Añadiendo elementos en lote: 4, 6, 7");
        lista.add(4);
        lista.add(6);
        lista.add(7);
        System.out.println("Elementos después de añadir lote: " + lista.Elementos);

        System.out.println("Eliminando todos los elementos: " + lista.Elementos);
        lista.Elementos.clear();
        System.out.println("¿Está vacía? " + lista.Elementos.isEmpty());

        // Test with strings
        Comparator<String> stringComparator = String::compareTo;
        ListaOrdenada<String> listaStrings = ListaOrdenada.of(stringComparator);
        listaStrings.add("banana");
        listaStrings.add("apple");
        listaStrings.add("date");
        listaStrings.add("cherry");

        System.out.println("Elementos ordenados: " + listaStrings.Elementos);
    }

}
