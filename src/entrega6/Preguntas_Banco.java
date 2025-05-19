package entrega6;

import java.time.LocalDate;
import java.util.Set;

import us.lsi.bancos.Banco;
import us.lsi.bancos.Prestamo;
import us.lsi.ejemplos_b1_tipos.Persona;

public class Preguntas_Banco {

    public static Double valorTotalPrestamos(Banco banco, Integer e, Double a, Double b, LocalDate f) {

        if (e <= 18) {
            throw new IllegalArgumentException("La edad debe ser mayor a 18.");
        }
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Los valores a y b deben ser positivos.");
        }
        if (a >= b) {
            throw new IllegalArgumentException("El valor de a debe ser menor que b.");
        }

        
        return banco.personas().todos().stream()
            .filter(persona -> persona.edad() < e) 
            .flatMap(persona -> banco.prestamosDeCliente(persona.dni()).stream()) 
            .filter(prestamo -> prestamo.cantidad() >= a && prestamo.cantidad() <= b) 
            .filter(prestamo -> prestamo.fechaComienzo().isAfter(f)) 
            .mapToDouble(Prestamo::cantidad) 
            .sum(); 
    }
    
    public static Double valorTotalPrestamosImperativo(Banco banco, Integer e, Double a, Double b, LocalDate f) {
        
        if (e <= 18) {
            throw new IllegalArgumentException("La edad debe ser mayor a 18.");
        }
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Los valores a y b deben ser positivos.");
        }
        if (a >= b) {
            throw new IllegalArgumentException("El valor de a debe ser menor que b.");
        }

        
        double total = 0.0;
        Set<Persona> clientes = banco.personas().todos();

        for (Persona cliente : clientes) {
            if (cliente.edad() < e) { 
                Set<Prestamo> prestamos = banco.prestamosDeCliente(cliente.dni());
                for (Prestamo prestamo : prestamos) {
                    if (prestamo.cantidad() >= a && prestamo.cantidad() <= b && prestamo.fechaComienzo().isAfter(f)) {
                        total += prestamo.cantidad(); 
                    }
                }
            }
        }

        return total;
    }
}



