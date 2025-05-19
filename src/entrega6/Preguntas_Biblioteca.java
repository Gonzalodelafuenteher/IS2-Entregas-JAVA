package entrega6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import us.lsi.biblioteca.Prestamos;
import us.lsi.tools.File2;
import us.lsi.biblioteca.Libro;
import us.lsi.biblioteca.Libros;
import us.lsi.biblioteca.Prestamo;

public class Preguntas_Biblioteca {

	public static String masVecesPrestadoImperativo() {
	    Map<String, String> librosMap = new HashMap<>();
	    for (String line : File2.lineasDeFichero("./biblioteca/libros.txt", "utf-8")) {
	        String[] parts = line.split(",", 3);
	        librosMap.put(parts[0], parts[1]);
	    }

	    Prestamos prestamos = Prestamos.of("./biblioteca/Prestamos.txt");
	    Map<String, Integer> prestamosPorLibro = new HashMap<>();

	    for (Prestamo prestamo : prestamos.todos()) {
	        String isbn = prestamo.isbn();
	        prestamosPorLibro.put(isbn, prestamosPorLibro.getOrDefault(isbn, 0) + 1);
	    }

	    List<Map.Entry<String, Integer>> lista = new ArrayList<>(prestamosPorLibro.entrySet());
	    lista.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

	    String isbnMasPrestado = lista.get(0).getKey();
	    String titulo = librosMap.getOrDefault(isbnMasPrestado, "Título desconocido");

	    return String.format(" -  El libro más veces prestado es %s que se ha prestado %s veces", titulo, lista.get(0).getValue());
	}

	
	public static String masVecesPrestadoFuncional() {
	    Map<String, String> librosMap = File2.streamDeFichero("./biblioteca/libros.txt", "utf-8")
	            .map(line -> line.split(",", 3))
	            .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));

	    List<Map.Entry<String, Integer>> lista = Prestamos.of("./biblioteca/prestamos.txt")
	            .todos()
	            .stream()
	            .collect(Collectors.groupingBy(
	                    Prestamo::isbn,
	                    Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
	            ))
	            .entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
	            .collect(Collectors.toList());

	    String titulo = librosMap.getOrDefault(lista.getFirst().getKey(), "Título desconocido");

	    return String.format(" - El libro más veces prestado es %s que se ha prestado %s veces", titulo, lista.getFirst().getValue());
	}

	// Imperativo: Libros por autor
	public static Map<String, Set<String>> librosPorAutorImperativo(Libros libros, List<String> nombres) {
	    Libros ls = Libros.of("./biblioteca/libros.txt");
	    if (libros == null) {
	        libros = ls;
	    }

	    if (nombres == null) {
	        List<String> nombresList = new ArrayList<>();
	        for (Libro x : ls.todos()) {
	            nombresList.add(x.autor());
	        }
	        nombres = nombresList;
	    }

	    Map<String, Set<String>> sol = new HashMap<>();
	    for (Libro x : ls.todos()) {
	        if (nombres.contains(x.autor())) {
	            String key = x.autor();
	            Set<String> value = sol.getOrDefault(key, new HashSet<>());
	            if (libros.todos().contains(x)) {
	                value.add(x.titulo());
	            }
	            sol.put(key, value);
	        }
	    }

	    return sol;
	}


	public static Map<String, Set<String>> librosPorAutorFuncional(Libros libros, List<String> nombres) {
	    Libros librosFinal = (libros == null) ? Libros.of() : libros;

	    List<String> nombresList = (nombres == null)
	            ? librosFinal.todos().stream().map(Libro::autor).distinct().toList()
	            : nombres;

	    return librosFinal.todos().stream()
	            .filter(libro -> nombresList.contains(libro.autor()) && librosFinal.todos().contains(libro))
	            .collect(Collectors.groupingBy(
	                    Libro::autor,
	                    Collectors.mapping(Libro::titulo, Collectors.toSet())
	            ));
	}
}

				
