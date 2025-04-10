
package bloque5.examen;

import java.util.*;
import java.util.stream.Collectors;

public class EstadisticasClientes {

    
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream()
                .collect(Collectors.groupingBy(Compra::getCliente));
    }

 
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativa(List<Compra> compras) {
        Map<Cliente, List<Compra>> resultado = new HashMap<>();
        for (Compra compra : compras) {
            Cliente cliente = compra.getCliente();
            resultado.putIfAbsent(cliente, new ArrayList<>());
            resultado.get(cliente).add(compra);
        }
        return resultado;
    }

	public static void main(String[] args) {
	    
	    Cliente ana = Cliente.of("Ana", 5);
	    Cliente juan = Cliente.of("Juan", 2);
	    Cliente luis = Cliente.of("Luis", 7);
	
	    
	    Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
	    Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
	    Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
	    Compra c4 = Compra.of(luis, "Poster gigante", 80.0);
	    Compra c5 = Compra.of(ana, "Bolígrafo", 5.0);
	
	    
	    List<Compra> compras = Arrays.asList(c1, c2, c3, c4, c5);
	
	    // Probar agruparComprasPorClienteFuncional
	    System.out.println("Agrupación funcional:");
	    Map<Cliente, List<Compra>> agrupacionFuncional = EstadisticasClientes.agruparComprasPorClienteFuncional(compras);
	    agrupacionFuncional.forEach((cliente, listaCompras) -> {
	        System.out.println(cliente + ": " + listaCompras);
	    });
	
	    // Probar agruparComprasPorClienteImperativa
	    System.out.println("\nAgrupación imperativa:");
	    Map<Cliente, List<Compra>> agrupacionImperativa = EstadisticasClientes.agruparComprasPorClienteImperativa(compras);
	    agrupacionImperativa.forEach((cliente, listaCompras) -> {
	        System.out.println(cliente + ": " + listaCompras);
	    });
	
	    // Verificar que los resultados son iguales
	    System.out.println("\n¿Los resultados son iguales? " + agrupacionFuncional.equals(agrupacionImperativa));
	}

}


