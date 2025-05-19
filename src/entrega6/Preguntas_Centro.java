package entrega6;

import static org.mockito.ArgumentMatchers.anyMap;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.aeropuerto.Vuelo;
import us.lsi.centro.*;


public class Preguntas_Centro {

	public static double promedioEdadProfesoresFuncional(String dniAlumno) {
	    Centro centro = Centro.of();
	    return centro.asignaciones().todas().stream()
	            .filter(a -> centro.matriculas().todas().stream()
	                    .filter(m -> m.dni().equals(dniAlumno))
	                    .map(Matricula::grupo)
	                    .collect(Collectors.toSet())
	                    .contains(a.grupo()))
	            .map(Asignacion::dni)
	            .distinct()
	            .map(centro.profesores()::profesor)
	            .filter(profesor -> profesor != null)
	            .mapToInt(Profesor::edad)
	            .average()
	            .orElse(0.0);
	}


public static double promedioEdadProfesoresImperativo(String dniAlumno) {
    Centro centro = Centro.of();
    Set<Grupo> gruposAlumno = new HashSet<>();

  
    for (Matricula matricula : centro.matriculas().todas()) {
        if (matricula.dni().equals(dniAlumno)) {
            gruposAlumno.add(matricula.grupo());
        }
    }

    Set<String> dnisProfesores = new HashSet<>();

    
    for (Asignacion asignacion : centro.asignaciones().todas()) {
        if (gruposAlumno.contains(asignacion.grupo())) {
            dnisProfesores.add(asignacion.dni());
        }
    }

    int sumaEdades = 0;
    int contador = 0;

    
    for (String dniProfesor : dnisProfesores) {
        Profesor profesor = centro.profesores().profesor(dniProfesor);
        if (profesor != null) {
            sumaEdades += profesor.edad();
            contador++;
        }
    }

    
    return contador == 0 ? 0.0 : (double) sumaEdades / contador;
}

//////////////////////////////////////////////////////////////////////



	public static Grupo grupoMayorDiversidadEdad() {
	    Centro centro = Centro.of();
	    Grupo grupoConMayorDiversidad = null;
	    int mayorDiferenciaEdad = -1;
	
	    for (Grupo grupo : centro.grupos().todos()) {
	        int edadMinima = Integer.MAX_VALUE;
	        int edadMaxima = Integer.MIN_VALUE;
	
	        for (Matricula matricula : centro.matriculas().todas()) {
	            if (matricula.grupo().equals(grupo)) {
	                Alumno alumno = centro.alumnos().alumno(matricula.dni());
	                if (alumno != null) {
	                    int edad = alumno.edad();
	                    if (edad < edadMinima) {
	                        edadMinima = edad;
	                    }
	                    if (edad > edadMaxima) {
	                        edadMaxima = edad;
	                    }
	                }
	            }
	        }
	
	        int diferenciaEdad = edadMaxima - edadMinima;
	        if (diferenciaEdad > mayorDiferenciaEdad) {
	            mayorDiferenciaEdad = diferenciaEdad;
	            grupoConMayorDiversidad = grupo;
	        }
	    }
	
	    return grupoConMayorDiversidad;
	}
	

	public static Grupo grupoMayorDiversidadEdadFuncional() {
	    Centro centro = Centro.of();
	    return centro.grupos().todos().stream()
	            .max(Comparator.comparing(grupo -> {
	                List<Integer> edades = centro.matriculas().todas().stream()
	                        .filter(matricula -> matricula.grupo().equals(grupo))
	                        .map(matricula -> centro.alumnos().alumno(matricula.dni()))
	                        .filter(alumno -> alumno != null)
	                        .map(Alumno::edad)
	                        .collect(Collectors.toList());
	                return edades.isEmpty() ? 0 : (edades.stream().max(Integer::compare).orElse(0) -
	                        edades.stream().min(Integer::compare).orElse(0));
	            }))
	            .orElse(null);
	}
	
	
	
//////////////////////////////////////////////////////////////////////

	public static Alumno alumnoMasMatriculas() {
	    Centro centro = Centro.of();
	    return centro.matriculas().todas().stream()
	            .collect(Collectors.groupingBy(Matricula::dni, Collectors.counting()))
	            .entrySet().stream()
	            .max(Comparator.comparingLong(Map.Entry::getValue))
	            .map(entry -> centro.alumnos().alumno(entry.getKey()))
	            .orElse(null);
	}
	

	public static Alumno alumnoMasMatriculasImperativo() {
	    Centro centro = Centro.of();
	    Map<String, Integer> matriculasPorAlumno = new HashMap<>();
	
	    // Count matriculas for each student
	    for (Matricula matricula : centro.matriculas().todas()) {
	        String dni = matricula.dni();
	        matriculasPorAlumno.put(dni, matriculasPorAlumno.getOrDefault(dni, 0) + 1);
	    }
	
	    String dniConMasMatriculas = null;
	    int maxMatriculas = -1;
	
	    // Find the student with the most matriculas
	    for (Map.Entry<String, Integer> entry : matriculasPorAlumno.entrySet()) {
	        if (entry.getValue() > maxMatriculas) {
	            maxMatriculas = entry.getValue();
	            dniConMasMatriculas = entry.getKey();
	        }
	    }
	
	    // Return the student with the most matriculas
	    return dniConMasMatriculas != null ? centro.alumnos().alumno(dniConMasMatriculas) : null;
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Map<String,Map<String,Integer>> rangosEdadPorAlumnoImperativo(String rangoStr) {
		Centro c = Centro.of();
        Map<String,Map<String,Integer>> rangosEdadAlumnos = new HashMap<>();
        String[] rangos = rangoStr.split(",");

        for (String r : rangos) {
            // r = r.replaceAll("\s+", "");
            if (!r.matches("\\d+-\\d+")) {
                throw new IllegalArgumentException("Formato incorrecto: " + r);
            }

            String[] valores = r.split("-");
            Integer min = Integer.parseInt(valores[0].strip());
            Integer max = Integer.parseInt(valores[1].strip());
            Map<String,Integer> alumnoEdad = new HashMap<>();

            for (Alumno a : c.alumnos().todos()) {
                if (a.edad() >= min && a.edad() <= max) {
                    alumnoEdad.put(a.nombre(), a.edad());
                }
            }
            rangosEdadAlumnos.put(r,alumnoEdad);
        }

        return rangosEdadAlumnos;
    }


	
	public static Map<String, Map<String, Integer>> rangosEdadPorAlumnoFuncional(String rangoStr) {
		Centro c = Centro.of();
        return Arrays.stream(rangoStr.split(",")) // Convierte la cadena de rangos en un stream de Strings, separando por comas
            .peek(r -> {
                // r = r.replaceAll("\s+", ""); // Quita espacios en blanco
                if (!r.matches("\\d+-\\d+")) { // Verifica que el formato sea "número-número"
                    throw new IllegalArgumentException("Formato incorrecto: " + r);
                }
            })
            .collect(Collectors.toMap( // Convierte el rango "min-max" en un array de enteros [min, max]
                r -> r,
                r -> {
                    String[] valores = r.split("-");
                    int min = Integer.parseInt(valores[0].strip());
                    int max = Integer.parseInt(valores[1].strip());
                    return c.alumnos().todos().stream()
                        .filter(a -> a.edad() >= min && a.edad() <= max)
                        .collect(Collectors.toMap(
                                Alumno::nombre, 
                                Alumno::edad,
                                (a,b)->a // Evita nombres duplicados
                                ));
                    }
            ));
    }

	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static String nombreProfesorMasGruposFuncional(Integer min, Integer max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("La edad mínima debe ser menor que la máxima");
	    }

	    Centro c = Centro.of();
	    Profesores pr = c.profesores();
	    Asignaciones as = c.asignaciones();

	    // Obtenemos los dnis de los profesores en el rango de edad
	    Set<String> dnisProfesoresFiltrados = pr.todos().stream()
	        .filter(p -> p.edad() >= min && p.edad() <= max)
	        .map(Profesor::dni)
	        .collect(Collectors.toSet());

	    // Contamos cuántas asignaciones tiene cada profesor filtrado
	    Map<String, Long> conteo = as.todas().stream()
	        .map(Asignacion::dni)
	        .filter(dnisProfesoresFiltrados::contains)
	        .collect(Collectors.groupingBy(dni -> dni, Collectors.counting()));

	    // Buscamos el dni con más asignaciones
	    return conteo.entrySet().stream()
	        .max(Map.Entry.comparingByValue())
	        .map(entry -> pr.profesor(entry.getKey()).nombreCompleto())
	        .orElse(null);
	}
	
	public static String nombreProfesorMasGruposimperativo(Integer min, Integer max) {
		Centro c = Centro.of();
		Profesores pr = c.profesores();
		Asignaciones as = c.asignaciones();
		

		Map<String, Integer> contadorGrupos = new HashMap<>();

		if (min >= max) {
			throw new IllegalArgumentException("La edad mínima debe ser menor que la máxima");
		}
		for (Profesor prf : pr.todos()) {
			if (prf.edad() >= min && prf.edad() <= max) {// comprobamos q la edad del profesor está dentro del rango,
															// porque tiene q estar dentro del rg
				contadorGrupos.put(prf.dni(), 0);
			}
		}
		for (Asignacion asg : as.todas()) {
			String dniProf = asg.dni();
			if (contadorGrupos.containsKey(dniProf)) {
				contadorGrupos.put(dniProf, contadorGrupos.get(dniProf) + 1);
			}
		}
		String dniMax = null;
		int maxGrupos = -1;
		for (Map.Entry<String, Integer> entry : contadorGrupos.entrySet()) {
			if (entry.getValue() > maxGrupos) {
				maxGrupos = entry.getValue();
				dniMax = entry.getKey();
			}
		}

		if (dniMax == null)
			return null;

		Profesor profMax = pr.profesor(dniMax);
		return profMax.nombreCompleto();

	}


	
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////

	public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, LocalDateTime anio) {
		Centro c = Centro.of();
		Alumnos alums = c.alumnos();
		ArrayList<String> AlumnosTop = new ArrayList<>();

		if (n == null) {
			throw new IllegalArgumentException("La nota de los alumnos no puede ser null");
		}
		for (Alumno alum : alums.todos()) {
			if (alum.fechaDeNacimiento().isAfter(anio) && n < 10 && n > 1) {// pasamos la fecha de nacimiento
																						// a LocalDate y vemos si está
																						// despues de la fecha "a" y
																						// comporbamos q esta dentro del
																						// limite
				if (alum.nota() >= n) {
					AlumnosTop.add(alum.nombre());
				}
			}
		}
		return AlumnosTop;
	}

	public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, LocalDateTime anio) {
		Centro c = Centro.of();
		Alumnos alums = c.alumnos();
		if (n == null) {
			throw new IllegalArgumentException("La nota de los alumnos no puede ser null ");
		}
		if (n < 10 && n > 1) {
			throw new IllegalArgumentException("La nota de los alumnos debe estar entre 2 y 9 ");
		}

		return alums.todos().stream().filter(alumno -> alumno.fechaDeNacimiento().isAfter(anio))
				.filter(alumno -> alumno.nota() >= n).map(alumno -> alumno.nombre()).collect(Collectors.toList());
	}



	
}
