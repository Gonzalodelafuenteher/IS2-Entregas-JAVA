
package bloque5.examen;

import Entrega5.Pila;
import java.util.List;
import java.util.stream.Collectors;

public class HistorialCompras extends Pila<Compra> {

	public double totalGastadoPor(Cliente cliente) {
	    return Elementos.stream()
	        .filter(compra -> compra.getCliente().equals(cliente))
	        .mapToDouble(Compra::getImporte)
	        .sum();
	}


	public List<Compra> comprasMayoresA(double cantidad) {
	    return Elementos.stream()
	        .filter(compra -> compra.getImporte() > cantidad)
	        .collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
	    
	    Cliente ana = Cliente.of("Ana", 5);
	    Cliente juan = Cliente.of("Juan", 2);
	    Cliente luis = Cliente.of("Luis", 7);

	    
	    Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
	    Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
	    Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
	    Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

	    
	    HistorialCompras historial = new HistorialCompras();
	    historial.Elementos.add(c1);
	    historial.Elementos.add(c2);
	    historial.Elementos.add(c3);
	    historial.Elementos.add(c4);

	    // Test totalGastadoPor
	    System.out.println("Total gastado por Ana:");
	    double totalAna = historial.totalGastadoPor(ana);
	    System.out.println(totalAna);

	    // Test comprasMayoresA
	    System.out.println("\nCompras mayores a 20.0:");
	    List<Compra> comprasMayores = historial.comprasMayoresA(20.0);
	    for (Compra compra : comprasMayores) {
	        System.out.println(compra);
	    }
	}
}



