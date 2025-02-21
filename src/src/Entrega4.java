package src;


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
	
	public static int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static int funcion3(int a, int b) {
        return factorial(a) / (factorial(b) * factorial(a - b));
    }
	
	 


	public static void main(String[] args) {
		System.out.println(funcion1(4,2));
		System.out.println(funcion2(3,5,2));

	}

}
