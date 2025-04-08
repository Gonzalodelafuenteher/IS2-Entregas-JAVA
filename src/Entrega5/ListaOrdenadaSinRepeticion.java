package Entrega5;

import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {

    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
        super(comparator);
    }

    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
        return new ListaOrdenadaSinRepeticion<>(comparator);
    }

    @Override
    public void add(E e) {
        if (!Elementos.contains(e)) {
            super.add(e);
        }
    }
    
    public static void main(String[] args) {
        // Test with integers
    	
    	Comparator<Integer> intComparator = Integer::compareTo;
        ListaOrdenadaSinRepeticion<Integer> lista = ListaOrdenadaSinRepeticion.of(intComparator);

        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        lista.add(1);
        lista.add(1);
        lista.add(3);
        lista.add(5);
        lista.add(2);

        System.out.println("Elementos en la lista: " + lista.Elementos);
        System.out.println("Tamaño de la lista: " + lista.Elementos.size());
        System.out.println("Se esperan 5 elementos únicos ordenados");

        System.out.println("Eliminando el primer elemento: " + lista.Elementos.remove(0));
        System.out.println("Elementos después de eliminar: " + lista.Elementos);

        System.out.println("Añadiendo elementos en lote: 4, 6, 7, 4");
        lista.add(4);
        lista.add(6);
        lista.add(7);
        lista.add(4);
        System.out.println("Elementos después de añadir lote: " + lista.Elementos);
        System.out.println("Se espera que el 4 aparezca solo una vez");
    }
}