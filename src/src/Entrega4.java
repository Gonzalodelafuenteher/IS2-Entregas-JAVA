package src;
import java.io.BufferedReader;
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
	
public class LecturaArchivo {
    public static void main(String[] args) {
        try (
            // BufferedReader es una herramienta que nos ayuda a leer archivos
            // FileReader le dice a Java qué archivo queremos leer
            BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))
        ) {
            // Variable para guardar cada línea que leamos
            String linea;
            
            // Mientras haya líneas por leer...
            // readLine() lee una línea del archivo
            while ((linea = br.readLine()) != null) {
                // Mostramos la línea en pantalla
                System.out.println(linea);
            }
        } 
        // IOException es el tipo de error que puede ocurrir al trabajar con archivos
        catch (IOException e) {
            // Si hay un error (por ejemplo, si el archivo no existe)
            // printStackTrace muestra información sobre el error
            e.printStackTrace();
        }
    }
    
    
}   



	public static void main(String[] args) {
		System.out.println(funcion1(4,2));
		System.out.println(funcion2(3,5,2));
		System.out.println(funcion3(4,2));
		System.out.println(funcion4(4,2));
		
		java.util.function.Function<Float, Float> f = x -> 2 * x * x; 
        java.util.function.Function<Float, Float> fPrima = x -> 4 * x; 

        float a = 3.0f; // Initial guess
        float epsilon = 0.001f; // Error tolerance
        float raiz = funcion5(f, fPrima, a, epsilon);
        System.out.println("La raíz encontrada es: " + raiz);
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        

	}

}
