package Entrega5;

public class Cola<E> extends AgregadoLineal<E> {
    public static <E> Cola<E> of() {
        return new Cola<>();
    }

    @Override
    public void add(E e) {
        this.AgregadoLineal.addLast(e);
    }

    public static void main(String[] args) {
        Cola<Integer> cola1 =  Cola.of();
        cola1.add(2);
        cola1.add(3);
        cola1.add(5);
        System.out.println(cola1.elements());
        cola1.remove();
        System.out.println(cola1.elements());
        
        
        Cola<String> cola = Cola.of();

        System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        cola.add("primero");
        cola.add("segundo");
        cola.add("tercero");

        System.out.println("Elementos en la cola: " + cola.elements());
        System.out.println("Tamaño de la cola: " + cola.size());

        System.out.println("Desencolando elementos:");
        System.out.println("Desencolado: " + cola.remove());
        System.out.println("Cola restante: " + cola.elements());

        System.out.println("Desencolado: " + cola.remove());
        System.out.println("Cola restante: " + cola.elements());

        System.out.println("Desencolado: " + cola.remove());
        System.out.println("Cola restante: " + cola.elements());

        System.out.println("¿Está vacía? " + cola.isEmpty());

        try {
            cola.remove();
        } catch (IllegalStateException e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }
    }
  
        
        
        }
    

    
	
