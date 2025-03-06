package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Lecturas {
	public static String contarpalabras(String fichero, String separador, String cadena) {
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
		return "La palabra " + cadena + " aparece " + contador + " veces";
	}

	public static void filtrarLineasPorTexto(String fichero, String separador, String cadena) {
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

	public static HashSet<String> encontrarPalabrasUnicas(String fichero) {
		HashSet<String> palabrauni = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] palabras = linea.split(" ");
				for (String palabra : palabras) {
					palabrauni.add(palabra);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return palabrauni;

	}

	public static double longitudMediaLineasCSV(String fichero) {
		double contlineas = 0;
		double contpalabra = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] palabras = linea.trim().split(",");
				contlineas++;
				contpalabra += palabras.length;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contpalabra / contlineas;
	}
	
	public static void main(String[] args) {
		System.out.println(contarpalabras("src/datos-entregable-01 (1)/lin_quijote.txt", " ", "quijote"));
		System.out.println("///");
		filtrarLineasPorTexto("src/datos-entregable-01 (1)/lin_quijote.txt", " ", "quijote");
		System.out.println("///");
		System.out.println(encontrarPalabrasUnicas("src/datos-entregable-01 (1)/archivo_palabras.txt"));
		System.out.println("///");
		System.out.println(longitudMediaLineasCSV("src/datos-entregable-01 (1)/palabras_random.csv"));
		
		System.out.println("################################################");
        System.out.println("\nTEST DE LA contarpalabras:\n");
        System.out.println("El número de veces que aparece la palabra quijote en el fichero src/datos-entregable-01 (1)/lin_quijote.txt es: " + contarpalabras("src/datos-entregable-01 (1)/lin_quijote.txt", " ", "quijote"));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE LA filtrarLineasPorTexto:\n");
        System.out.println("Las líneas en las que aparece la palabra quijote son:");
        filtrarLineasPorTexto("src/datos-entregable-01 (1)/lin_quijote.txt", " ", "quijote");
        System.out.println("\n################################################");
        System.out.println("\nTEST DE LA encontrarPalabrasUnicas:\n");
        System.out.println("Las palabras únicas en el fichero datos-entregable-01 (1)/archivo_palabras.txt son: " + encontrarPalabrasUnicas("src/datos-entregable-01 (1)/archivo_palabras.txt"));
        System.out.println("\n################################################");
        System.out.println("\nTEST DE longitudMediaLineasCSV:\n");
        System.out.println("La longitud promedio de las líneas del fichero datos-entregable-01 (1)/palabras_random.csv es: " + longitudMediaLineasCSV("src/datos-entregable-01 (1)/palabras_random.csv"));
        System.out.println("\n################################################");
	}

}
