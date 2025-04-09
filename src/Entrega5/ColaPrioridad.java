package Entrega5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {
    

    public ColaPrioridad() {
        
    }

    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }

    @Override
    public void add(PriorityElement<E, P> element) {
        Elementos.add(element);
        Elementos.sort(Comparator.comparing(PriorityElement::priority));
    }

    public void add(E value, P priority) {
        add(new PriorityElement<>(value, priority));
    }

    public List<E> valuesAsList() {
        List<E> values = new ArrayList<>();
        for (PriorityElement<E, P> element : Elementos) {
            values.add(element.value());
        }
        return values;
    }

    public void decreasePriority(E value, P newPriority) {
        for (PriorityElement<E, P> element : new ArrayList<>(Elementos)) {
            if (element.value().equals(value)) {
                Elementos.remove(element);
                add(value, newPriority);
                break;
            }
        }
    }

    public E removeValue() {
        if (Elementos.isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }
        return Elementos.remove(0).value();
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(value, priority);
        }
    }

    
    
    	public static void main(String[] args) {
            
            System.out.println("----- Prueba de ColaPrioridad -----");
            
            ColaPrioridad<String, Integer> cola = ColaPrioridad.ofPriority();
            
            
            System.out.println("Añadiendo elementos con prioridad:");
            cola.add("Crítico", 1);
            System.out.println("'Crítico' con prioridad 1");
            cola.add("Normal", 3);
            System.out.println("'Normal' con prioridad 3");
            cola.add("Urgente", 2);
            System.out.println("'Urgente' con prioridad 2");
            cola.add("Bajo", 4);
            System.out.println("'Bajo' con prioridad 4");
            
           
            System.out.println("Elementos en la cola por prioridad: " + cola.valuesAsList());
            System.out.println("Elementos con sus prioridades: " + cola.Elementos);
            System.out.println("Tamaño de la cola: " + cola.Elementos.size());
            
           
            System.out.println("Cambiando la prioridad de 'Normal' de 3 a 1:");
            cola.decreasePriority("Normal", 1);
            System.out.println("Elementos con prioridad actualizada: " + cola.valuesAsList());
            
            
            System.out.println("Desencolando elementos por prioridad:");
            while (!cola.Elementos.isEmpty()) {
                String value = cola.removeValue();
                System.out.println("Desencolado: " + value);
                System.out.println("Cola restante: " + cola.valuesAsList());
            }
            
            
            System.out.println("¿Está vacía? " + cola.Elementos.isEmpty());
            
            
            try {
                cola.removeValue();
            } catch (Exception e) {
                System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
            }
            
            
            System.out.println("\nPrueba con addAll:");
            List<String> tareas = Arrays.asList("Tarea A", "Tarea B", "Tarea C");
            cola.addAllValues(tareas, 2);
            System.out.println("Elementos añadidos en lote con prioridad 2: " + tareas);
            
            
            cola.add("Tarea Urgente", 1);
            System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + cola.valuesAsList());
        }
    
   
}

