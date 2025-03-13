
package Entrega4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Defensa4 {
	
	public static long productoImpares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El parámetro n debe ser positivo y mayor que 0.");
        }

        long producto = 1;
        int numeroImpar = 1;

        for (int i = 0; i < n; i++) {
            producto *= numeroImpar;
            numeroImpar += 2;
        }

        return producto;
    }

    


	public static double sumaGeometricaAlternada(double a1, double r, int k) {
	    if (k <= 0) {
	        throw new IllegalArgumentException("k debe ser mayor que 0");
	    }
	    if (a1 <= 0 || r <= 0) {
	        throw new IllegalArgumentException("a1 y r deben ser positivos");
	    }
	
	    double suma = 0;
	    for (int n = 0; n < k; n++) {
	        suma += Math.pow(-1, n) * a1 * Math.pow(r, n);
	    }
	
	    return suma;
	}
	
	public static int combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual que k.");
        }
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("n y k deben ser valores positivos.");
        }

        int numerador = 1;
        int denominador1 = 1;
        int denominador2 = 1;

        
        for (int i = n; i > n - k; i--) {
            if (i % 3 != 0) { 
                numerador *= i;
            }
        }

        
        for (int i = k; i > 0; i--) {
            if (i % 3 != 0) { 
                denominador1 *= i;
            }
        }

        for (int i = (n - k); i > 0; i--) {
            if (i % 3 != 0) { 
                denominador2 *= i;
            }
        }

        return numerador / (denominador1 * denominador2);
    }
	
	    
	   public static void filtrarLineasConsecutivas(String fichero, String separador, List<String> palabrasClave) {
	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] palabras = linea.split(separador);
	                for (int i = 0; i < palabras.length - 1; i++) {
	                    if (palabras[i].toLowerCase().equals(palabrasClave.get(0).toLowerCase()) &&
	                        palabras[i + 1].toLowerCase().equals(palabrasClave.get(1).toLowerCase())) {
	                        System.out.println(linea);
	                        break;
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       }
	    
	
	
	
	public static void main(String[] args) {
        
        System.out.println("\n################################################");
     // Test productoImpares
        try {
            System.out.println("Producto de los primeros 5 números impares: " + productoImpares(5)); // Expected: 945
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        try {
            System.out.println("Producto de los primeros 0 números impares: " + productoImpares(0)); // Expected: Exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println("\n################################################");
        // Test sumaGeometricaAlternada
        try {
            System.out.println("Suma geométrica alternada de a1=2.0, r=3.0, k=5: " + sumaGeometricaAlternada(2.0, 3.0, 5)); // Expected: -40.0
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        try {
            System.out.println("Suma geométrica alternada de a1=0, r=3.0, k=5: " + sumaGeometricaAlternada(0, 3.0, 5)); // Expected: Exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println("\n################################################");

        // Test combinatorioSinMultiplosDeTres
        try {
            System.out.println("Combinatorio sin múltiplos de 3 para (5¦2): " + combinatorioSinMultiplosDeTres(5, 2)); // Expected: 10
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        try {
            System.out.println("Combinatorio sin múltiplos de 3 para (5¦6): " + combinatorioSinMultiplosDeTres(5, 6)); // Expected: Exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        System.out.println("\n################################################");
        
        String fichero = "src/datos-entregable-01 (1)/fichero.txt";
        String separador = "\\s+";
        List<String> palabrasClave = new ArrayList<>();
        palabrasClave.add("java");
        palabrasClave.add("programación");
        
        System.out.println("Líneas filtradas:");
        filtrarLineasConsecutivas(fichero, separador, palabrasClave);
	}
}
	
  


	


