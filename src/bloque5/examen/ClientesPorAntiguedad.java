
package bloque5.examen;

import Entrega5.ListaOrdenada;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

   
    public ClientesPorAntiguedad() {
        super(Comparator.comparingInt(Cliente::getAntiguedad).reversed());
    }

    
    public List<Cliente> topClientes(int n) {
        List<Cliente> resultado = new ArrayList<>();
        int contador = 0;

        for (Cliente cliente : this) { 
            if (contador >= n) break;
            resultado.add(cliente);
            contador++;
        }

        return resultado;
    }


    public static void main(String[] args) {
        // Create clients
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        
        ClientesPorAntiguedad lista = new ClientesPorAntiguedad();
        lista.add(ana);
        lista.add(juan);
        lista.add(luis);

        
        System.out.println("Clientes ordenados por antigüedad:");
        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }

        // Validate topClientes method
        System.out.println("\nTop 2 clientes:");
        List<Cliente> top2Clientes = lista.topClientes(2);
        for (Cliente cliente : top2Clientes) {
            System.out.println(cliente);
        }

        System.out.println("\nTop 1 cliente:");
        List<Cliente> top1Cliente = lista.topClientes(1);
        for (Cliente cliente : top1Cliente) {
            System.out.println(cliente);
        }

        System.out.println("\nTop 5 clientes :");
        List<Cliente> top5Clientes = lista.topClientes(5);
        for (Cliente cliente : top5Clientes) {
            System.out.println(cliente);
        }
    }
}

