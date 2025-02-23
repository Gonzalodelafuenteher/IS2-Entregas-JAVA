package src;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;

public class Entrega4{
	
	public static int funcion1(int n,int k) {
		int resultado = 1;
		if (n>k) {
			for(int i=0;i<k;i++) {
				resultado *= (n-i+1);
			}
		}
		
		return resultado;
	}
		
	public static int funcion2(int a1,int r,int n) {
		int producto = 1;
		for (int i=1;i<=n;i++) {
			double an = (a1 * Math.pow(r, i-1));
			producto *= an;
		}
		return producto;
	}
	
	private static int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static int funcion3(int a, int b) {
        return factorial(a) / (factorial(b) * factorial(a - b));
    }
	
    public static double funcion4(int n, int k) {
        double primera = 1.0 / factorial(k);
        double resultado = 0; 
        
        for (int i = 0; i < k; i++) {
            int combinatorio = factorial(k + 1) / (factorial(i + 1) * factorial(k - i));
            resultado += Math.pow(-1, i) * combinatorio * Math.pow(k - i, n);
        }
        
        return primera * resultado;
		
	}
    
    public static float funcion5(java.util.function.Function<Float, Float> f, java.util.function.Function<Float, Float> fPrima, float a, float epsilon) {
        float xn = a;
        int iteraciones = 0;
        int maxIter = 1000;

        while (Math.abs(f.apply(xn)) > epsilon && iteraciones < maxIter) {
            float derivada = fPrima.apply(xn);

            if (derivada == 0) {
                throw new ArithmeticException("Derivada cero. El método de Newton no puede continuar.");
            }

            xn = xn - f.apply(xn) / derivada;
            iteraciones++;
        }

        if (iteraciones == maxIter) {
            throw new ArithmeticException("No se encontró una raíz en el número máximo de iteraciones.");
        }

        return xn;
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public static String funcion6(String fichero, String separador, String cadena) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(separador);
                for (String palabra : palabras) {
                    if (palabra.toLowerCase().equals(cadena.toLowerCase())) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return "La palabra "+cadena+" aparece "+contador+" veces";
    }
    
    public static void funcion7(String fichero, String separador, String cadena) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(separador);
                for (String palabra : palabras) {
                    if (palabra.toLowerCase().equals(cadena.toLowerCase())) {
                        System.out.println(linea);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    	
    
    
    	
    	public static HashSet<String> funcion8(String fichero) {
            HashSet<String> palabrauni = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                	String [] palabras = linea.split(" ");
                    for (String palabra : palabras) {
                        palabrauni.add(palabra);  
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        	return palabrauni;
    	
    	
    }
    	
    	
    	public static double funcion9(String fichero, String separador) {
            int longitud = 0;
            int contlineas = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    longitud += linea.length();
                    contlineas++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  longitud / contlineas ;
        }
    	
    
    
  



	public static void main(String[] args) {
		System.out.println(funcion1(4,2));
		System.out.println("///");
		System.out.println(funcion2(3,5,2));
		System.out.println("///");
		System.out.println(funcion3(4,2));
		System.out.println("///");
		System.out.println(funcion4(4,2));
		System.out.println("///");
		
		java.util.function.Function<Float, Float> f = x -> 2 * x * x; 
        java.util.function.Function<Float, Float> fPrima = x -> 4 * x; 

        float a = 3.0f; // Initial guess
        float epsilon = 0.001f; // Error tolerance
        float raiz = funcion5(f, fPrima, a, epsilon);
        System.out.println("La raíz encontrada es: " + raiz);
        
        System.out.println("/////////////////////////////////////////////////////////");
        System.out.println(funcion6("src/datos-entregable-01 (1)/lin_quijote.txt"," ","quijote"));
        System.out.println("///");
        funcion7("src/datos-entregable-01 (1)/lin_quijote.txt"," ","quijote");
        System.out.println("///");
        System.out.println(funcion8("src/datos-entregable-01 (1)/archivo_palabras.txt"));
        System.out.println("///");
        System.out.println(funcion9("src/datos-entregable-01 (1)/palabras_random.csv"," "));
	}

}
