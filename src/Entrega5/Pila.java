
package Entrega5;

public class Pila<E> extends AgregadoLineal<E> {

    @Override
    public void add(E e) {
        Elementos.add(0, e); 
    }

    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return Elementos.get(0);
    }

    

    public static void main(String[] args) {
        Pila<String> Pila1 = new Pila<>();
        Pila1.add("Element1");
        Pila1.add("Element2");
        Pila1.add("Element3");
        System.out.println(Pila1.Elementos);

        System.out.println(Pila1.top());
        
        
        Pila<Double> pila = new Pila<>();

        System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
        pila.add(1.1);
        pila.add(2.2);
        pila.add(3.3);

        System.out.println("Elementos en la pila: " + pila.Elementos());
        System.out.println("Tamaño de la pila: " + pila.Elementos.size());
        System.out.println("Elemento en el tope: " + pila.top());

        System.out.println("Desapilando elementos:");
        System.out.println("Desapilado: " + pila.remove());
        System.out.println("Pila restante: " + pila.Elementos);

        System.out.println("Desapilado: " + pila.remove());
        System.out.println("Pila restante: " + pila.Elementos);

        System.out.println("Desapilado: " + pila.remove());
        System.out.println("Pila restante: " + pila.Elementos);

        System.out.println("¿Está vacía? " + pila.isEmpty());

        try {
            pila.top();
        } catch (IllegalStateException e) {
            System.out.println("Excepción correctamente lanzada al intentar acceder al tope de una pila vacía: " + e.getMessage());
        }
    }
  }
    
    




