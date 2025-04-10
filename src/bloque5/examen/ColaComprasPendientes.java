
package bloque5.examen;

import Entrega5.Cola;
import java.util.List;
import java.util.stream.Collectors;

public class ColaComprasPendientes extends Cola<Compra> {

    
    public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : Elementos) { 
            if (compra.getDescripcion().contains(desc)) {
                return compra;
            }
        }
        return null; 
    }

    
    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return Elementos.stream()
                .filter(compra -> compra.getCliente().equals(cliente) && compra.getDescripcion().contains(producto))
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

       
        ColaComprasPendientes cola = new ColaComprasPendientes();
        cola.add(c1);
        cola.add(c2);
        cola.add(c3);
        cola.add(c4);

        // Test buscarCompraPorDescripcion
        System.out.println("Buscar compra por descripción 'Taza':");
        Compra compraEncontrada = cola.buscarCompraPorDescripcion("Taza");
        System.out.println(compraEncontrada);

        // Test filtrarPorClienteYProducto
        System.out.println("\nFiltrar compras de Ana con producto 'Agenda':");
        List<Compra> comprasFiltradas = cola.filtrarPorClienteYProducto(ana, "Agenda");
        for (Compra compra : comprasFiltradas) {
            System.out.println(compra);
        }
    }
}
