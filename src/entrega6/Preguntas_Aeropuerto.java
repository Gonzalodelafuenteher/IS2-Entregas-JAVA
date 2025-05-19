
package entrega6;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.aeropuerto.Aeropuertos;
import us.lsi.aeropuerto.VueloProgramado;
import us.lsi.aeropuerto.Vuelos;
import us.lsi.aeropuerto.VuelosProgramados;

public class Preguntas_Aeropuerto {

    public static String ciudadAeropuertoMayorFacturacion(LocalDateTime a, LocalDateTime b) {

       
        Map<String, Double> facturacionPorAeropuerto = Vuelos.of().todas().stream()
            .filter(vuelo -> !vuelo.fecha().isBefore(a) && !vuelo.fecha().isAfter(b))
            .collect(Collectors.groupingBy(
                vuelo -> vuelo.vueloProgramado().codigoDestino(),
                Collectors.summingDouble(vuelo -> vuelo.vueloProgramado().precio() * vuelo.numPasajeros())
            ));

       
        String codigoAeropuertoMayorFacturacion = facturacionPorAeropuerto.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow(() -> new IllegalStateException("No hay vuelos en el rango de fechas especificado."));

        
        return Aeropuertos.of().ciudadDeAeropuerto(codigoAeropuertoMayorFacturacion)
            .orElseThrow(() -> new IllegalStateException("No se encontró la ciudad del aeropuerto."));
    }
    
    public static String ciudadAeropuertoMayorFacturacionImperativo() {
        
        Set<VueloProgramado> vuelosProgramados = VuelosProgramados.of().todos();
        Map<String, Double> facturacionPorCiudad = new HashMap<>();

        
        for (VueloProgramado vuelo : vuelosProgramados) {
            String ciudadDestino = Aeropuertos.of().ciudadDeAeropuerto(vuelo.codigoDestino()).orElse("Desconocido");
            double facturacion = vuelo.precio() * vuelo.numPlazas();

            facturacionPorCiudad.put(ciudadDestino, 
                facturacionPorCiudad.getOrDefault(ciudadDestino, 0.0) + facturacion);
        }

        
        String ciudadMayorFacturacion = null;
        double mayorFacturacion = 0.0;

        for (Map.Entry<String, Double> entry : facturacionPorCiudad.entrySet()) {
            if (entry.getValue() > mayorFacturacion) {
                mayorFacturacion = entry.getValue();
                ciudadMayorFacturacion = entry.getKey();
            }
        }

        return ciudadMayorFacturacion;
    }
    
	public static void main(String[] args) {
		System.out.println("Clase Preguntas_Aeropuerto ejecutada correctamente.");
	}
    
    
}

