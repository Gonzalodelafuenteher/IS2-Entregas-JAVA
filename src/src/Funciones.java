
package src;

public class Funciones {
    public static int calcularProducto(int n, int k) {
        int resultado = 1;
        if (n > k) {
            for (int i = 0; i < k; i++) {
                resultado *= (n - i + 1);
            }
        }
        return resultado;
    }

    public static int productoSecuenciaGeometrica(int a1, int r, int n) {
        int producto = 1;
        for (int i = 1; i <= n; i++) {
            double an = (a1 * Math.pow(r, i - 1));
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

    public static int calcularNumeroCombinatorio(int a, int b) {
        return factorial(a) / (factorial(b) * factorial(a - b));
    }

    public static double calcularS(int n, int k) {
        double primera = 1.0 / factorial(k);
        double resultado = 0;

        for (int i = 0; i < k; i++) {
            int combinatorio = factorial(k + 1) / (factorial(i + 1) * factorial(k - i));
            resultado += Math.pow(-1, i) * combinatorio * Math.pow(k - i, n);
        }

        return primera * resultado;
    }

    public static float metodoNewton(java.util.function.Function<Float, Float> f,
                                     java.util.function.Function<Float, Float> fPrima, float a, float epsilon) {
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

    public static void main(String[] args) {
        System.out.println("################################################");
        System.out.println("\nTEST DE LA calcularProducto:\n");
        System.out.println("El producto de 4 y 2 es: " + calcularProducto(4, 2));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE LA productoSecuenciaGeometrica:\n");
        System.out.println("El producto de la secuencia geométrica con a1 = 3, r = 5 y k = 2 es: " + productoSecuenciaGeometrica(3, 5, 2));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE LA calcularNumeroCombinatorio:\n");
        System.out.println("El número combinatorio de 4 y 2 es: " + calcularNumeroCombinatorio(4, 2));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE calcularS:\n");
        System.out.println("El número S(n,k) siendo n = 4 y k = 2 es: " + calcularS(4, 2));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE LA metodoNewton:\n");

        java.util.function.Function<Float, Float> f = x -> 2 * x * x;
        java.util.function.Function<Float, Float> fPrima = x -> 4 * x;

        float a = 3.0f;
        float epsilon = 0.001f;
        float raiz = metodoNewton(f, fPrima, a, epsilon);
        System.out.println("Resultado de la función 5 con a = " + a + " y e = " + epsilon + ", f(x) = 2x^2 y f'(x) = 4x: " + raiz);
        System.out.println("\n################################################");
    }
}

