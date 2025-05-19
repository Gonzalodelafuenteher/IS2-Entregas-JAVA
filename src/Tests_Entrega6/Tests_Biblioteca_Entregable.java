
package Tests_Entrega6;

import entrega6.Preguntas_Biblioteca;

import java.time.LocalDateTime;

public class Tests_Biblioteca_Entregable {

    public static void main(String[] args) {

        System.out.println(Preguntas_Biblioteca.masVecesPrestadoImperativo());
        System.out.println(Preguntas_Biblioteca.masVecesPrestadoFuncional());

        System.out.println(Preguntas_Biblioteca.librosPorAutorImperativo(null, null));
        System.out.println(Preguntas_Biblioteca.librosPorAutorFuncional(null, null));

        
    }
}

