
package Tests_Entrega6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entrega6.Preguntas_Aeropuerto;

public class Test_Aeropuerto_Entrega {

    public static void main(String[] args) {
        // Define el rango de fechas para el test
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaInicio = LocalDateTime.parse("2020-01-01 00:00:00", formatter);
        LocalDateTime fechaFin = LocalDateTime.parse("2020-12-31 23:59:59", formatter);

        
        String ciudadMayorFacturacion = Preguntas_Aeropuerto.ciudadAeropuertoMayorFacturacion(fechaInicio, fechaFin);

        
        System.out.println("La ciudad con mayor facturación en el rango de fechas es: " + ciudadMayorFacturacion);
    }
}

	
