package CineGestor;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int capacidadSala = 50; // Capacidad de todas las salas
        SalaManager salaManager = new SalaManager(capacidadSala);
        VincularPeliculasConDescripciones vincularPeliculasConDescripciones = new VincularPeliculasConDescripciones("src/main/resources/Descripciones");

        // Cargar datos
        Pelicula.cargarPeliculasDesdeArchivo("src/main/resources/Peliculas");

        // Asignar horarios y salas a cada película
        for (Pelicula pelicula : Pelicula.getPeliculas()) {
            Map<String, Sala> asignaciones = salaManager.asignarSalasParaPelicula();
            pelicula.asignarHorariosConSalas(asignaciones);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar títulos
            List<Pelicula> titulos = Pelicula.getPeliculas();
            System.out.println("Películas disponibles:");
            for (int i = 0; i < titulos.size(); i++) {
                System.out.println((i + 1) + ". " + titulos.get(i).getTitulo());
            }

            // Solicitar la elección del usuario
            System.out.print("Introduce el número de la película que deseas seleccionar (0 para salir): ");
            int eleccion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea después del número

            if (eleccion == 0) {
                // Salir del programa
                System.out.println("Saliendo del programa...");
                break;
            }

            // Validar la entrada del usuario
            if (eleccion < 1 || eleccion > titulos.size()) {
                System.out.println("Opción no válida.");
                continue;  // Volver a mostrar los títulos
            } else {
                // Obtener el título de la película seleccionada
                Pelicula peliculaSeleccionada = titulos.get(eleccion - 1);
                System.out.println("Has seleccionado: " + peliculaSeleccionada.getTitulo());

                // Mostrar información completa de la película
                System.out.println("ID: " + peliculaSeleccionada.getId());
                System.out.println("Título: " + peliculaSeleccionada.getTitulo());
                System.out.println("Año: " + peliculaSeleccionada.getAnio());
                System.out.println("Duración: " + peliculaSeleccionada.getDuracion() + " minutos");
                System.out.println("Actores: " + peliculaSeleccionada.getActores());
                System.out.println("Género: " + peliculaSeleccionada.getGenero());
                System.out.println("Director: " + peliculaSeleccionada.getDirector());

                // Mostrar la descripción de la película
                String descripcion = vincularPeliculasConDescripciones.obtenerDescripcion(peliculaSeleccionada.getId());
                System.out.println("Descripción: " + descripcion);

                // Mostrar horarios disponibles con asignación de salas
                System.out.println("Horarios disponibles:");
                Map<String, Sala> horariosConSalas = peliculaSeleccionada.obtenerHorariosConSalas();
                int horarioIndex = 1;
                for (Map.Entry<String, Sala> entry : horariosConSalas.entrySet()) {
                    String horario = entry.getKey();
                    Sala sala = entry.getValue();
                    System.out.println(horarioIndex + ". " + horario + " - " + sala);
                    horarioIndex++;
                }

                // Solicitar la selección del horario
                System.out.print("Introduce el número del horario que deseas seleccionar (0 para volver): ");
                int horarioEleccion = scanner.nextInt();
                scanner.nextLine();  // Consumir el salto de línea después del número

                if (horarioEleccion == 0) {
                    continue;  // Volver a la lista de películas
                }

                if (horarioEleccion < 1 || horarioEleccion > horariosConSalas.size()) {
                    System.out.println("Opción no válida.");
                    continue;  // Volver a mostrar los horarios
                } else {
                    // Obtener el horario seleccionado
                    String horarioSeleccionado = (String) horariosConSalas.keySet().toArray()[horarioEleccion - 1];
                    Sala salaAsignada = horariosConSalas.get(horarioSeleccionado);

                    // Mostrar información del horario y sala
                    System.out.println("Has seleccionado el horario: " + horarioSeleccionado);
                    System.out.println("Sala asignada: " + salaAsignada);
                }
            }

            // Preguntar al usuario si desea volver a la lista de películas
            System.out.print("¿Deseas seleccionar otra película? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                System.out.println("Saliendo del programa...");
                break;
            }
        }

        scanner.close();
    }
}
