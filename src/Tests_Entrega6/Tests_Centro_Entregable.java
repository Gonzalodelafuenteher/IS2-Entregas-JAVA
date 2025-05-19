package Tests_Entrega6;



import us.lsi.centro.Centro;
import us.lsi.centro.Alumno;
import us.lsi.centro.Profesor;
import us.lsi.centro.Grupo;
import us.lsi.centro.Matricula;
import us.lsi.centro.Asignacion;
import us.lsi.centro.Asignatura;
import entrega6.Preguntas_Centro;

import java.time.LocalDateTime;


public class Tests_Centro_Entregable {
	
	
	public static void main(String[] args) {

        System.out.println(Preguntas_Centro.promedioEdadProfesoresFuncional("72074089R"));
        System.out.println(Preguntas_Centro.promedioEdadProfesoresImperativo("72074089R"));

        System.out.println(Preguntas_Centro.grupoMayorDiversidadEdad());
        System.out.println(Preguntas_Centro.grupoMayorDiversidadEdadFuncional());

        System.out.println(Preguntas_Centro.alumnoMasMatriculasImperativo());
        System.out.println(Preguntas_Centro.alumnoMasMatriculas());

        System.out.println(Preguntas_Centro.rangosEdadPorAlumnoImperativo("20-23,24-26,26-28"));
        System.out.println(Preguntas_Centro.rangosEdadPorAlumnoFuncional("20-23,24-26,26-28"));

        System.out.println(Preguntas_Centro.nombreProfesorMasGruposimperativo(26,27));
        System.out.println(Preguntas_Centro.nombreProfesorMasGruposFuncional(26,27));

        LocalDateTime anio = LocalDateTime.of(2003,1,1,0,0);
        System.out.println(Preguntas_Centro.nombresAlumnosMayorNotaImperativo(4,anio));
        System.out.println(Preguntas_Centro.nombresAlumnosMayorNotaFuncional(4,anio));

	}


}

