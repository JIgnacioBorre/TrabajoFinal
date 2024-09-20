package CineGestor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
                System.out.println("Formato: " + peliculaSeleccionada.getFormato());

                // Mostrar la descripción de la película
                String descripcion = vincularPeliculasConDescripciones.obtenerDescripcion(peliculaSeleccionada.getId());
                System.out.println("Descripción: " + descripcion);

                // Seleccionar formato para ver la película
                String formato;
                boolean formatoValido = false;

                while (!formatoValido) {
                    System.out.println("Selecciona el formato de la película:");
                    System.out.println("1. 2D Doblada");
                    System.out.println("2. 2D Subtitulada");
                    System.out.println("3. 3D Doblada");
                    System.out.println("4. 3D Subtitulada");

                    int opcionFormato = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    switch (opcionFormato) {
                        case 1:
                            formato = "2D Doblada";
                            formatoValido = true;
                            break;
                        case 2:
                            formato = "2D Subtitulada";
                            formatoValido = true;
                            break;
                        case 3:
                            formato = "3D Doblada";
                            formatoValido = true;
                            break;
                        case 4:
                            formato = "3D Subtitulada";
                            formatoValido = true;
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, elige de nuevo.");
                    }
                }

                // Mostrar el formato seleccionado
                System.out.println("Formato seleccionado: " + formatoValido);

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
                    UbicacionAsientos ubicacionAsientos = new UbicacionAsientos(); // Crear instancia de UbicacionAsientos para la sala seleccionada

                    // Mostrar información del horario y sala
                    System.out.println("Has seleccionado el horario: " + horarioSeleccionado);
                    System.out.println("Sala asignada: " + salaAsignada);

                    // Mostrar tipos de entrada disponibles
                    System.out.println("Tipos de entrada disponibles:");
                    System.out.println("1. Standard - Precio: $" + Entrada.PRECIO_STANDARD);
                    System.out.println("2. Premium - Precio: $" + Entrada.PRECIO_PREMIUM);
                    System.out.println("3. 2x1 Standard - Precio: $" + Entrada.PRECIO_2X1_STANDARD);
                    System.out.println("4. 2x1 Premium - Precio: $" + Entrada.PRECIO_2X1_PREMIUM);

                    // Solicitar el tipo de entrada
                    System.out.print("Introduce el número del tipo de entrada (1 para 'standard', 2 para 'premium', 3 para '2x1 standard', 4 para '2x1 premium(Los beneficios 2x1 incluyen cada uno dos entradas, seleccione solo 1 para obtener 2 entradas.)'): ");
                    int tipoEntradaCodigo = scanner.nextInt();
                    scanner.nextLine();  // Consumir el salto de línea después del número

                    // Mostrar asientos disponibles para el tipo de entrada seleccionado
                    ubicacionAsientos.mostrarAsientos(tipoEntradaCodigo);

                    // Solicitar la cantidad de entradas
                    System.out.print("Introduce la cantidad de entradas que deseas comprar: ");
                    int cantidadEntradas = scanner.nextInt();
                    scanner.nextLine();  // Consumir el salto de línea después del número

                    // Ajustar la cantidad para entradas 2x1
                    if (tipoEntradaCodigo == 3 || tipoEntradaCodigo == 4) { // 2x1 Standard o 2x1 Premium
                        cantidadEntradas *= 2; // Contar cada entrada como 2
                    }

                    // Verificar que la cantidad de entradas no exceda la capacidad de la sala
                    if (cantidadEntradas > capacidadSala) {
                        System.out.println("La cantidad de entradas no puede ser mayor que la capacidad de la sala (" + capacidadSala + ").");
                        continue;  // Volver a solicitar la cantidad
                    }

                    // Verificar que haya suficientes asientos disponibles
                    int asientosDisponibles = contarAsientosDisponibles(ubicacionAsientos, tipoEntradaCodigo);
                    if (cantidadEntradas > asientosDisponibles) {
                        System.out.println("No hay suficientes asientos disponibles para el tipo de entrada seleccionado.");
                        continue;  // Volver a mostrar los asientos
                    }

                    // Solicitar y seleccionar asientos
                    int asientosAsignados = 0;
                    while (asientosAsignados < cantidadEntradas) {
                        System.out.print("Introduce el número de la fila (1 para la fila 1, 2 para la fila 2, en caso de entrada premiun elegir nro 5 o 6): ");
                        int filaSeleccionada = scanner.nextInt();
                        System.out.print("Introduce el número de la columna (1 para la columna 1, 2 para la columna 2, etc.): ");
                        int columnaSeleccionada = scanner.nextInt();

                        if (ubicacionAsientos.seleccionarAsiento(filaSeleccionada, columnaSeleccionada)) {
                            System.out.println("Asiento seleccionado en la fila " + filaSeleccionada + ", columna " + columnaSeleccionada);
                            asientosAsignados++;
                        } else {
                            System.out.println("El asiento seleccionado ya está ocupado o fuera de rango. Por favor, elige otro.");
                        }
                    }

                    // Mostrar todos los asientos con la selección actual
                    ubicacionAsientos.mostrarTodosLosAsientos();

                    System.out.println("Compra realizada. ¡Disfruta de la película!");
                }
            }
        }

        scanner.close();
    }

    // Método para contar asientos disponibles según el tipo de entrada
    private static int contarAsientosDisponibles(UbicacionAsientos ubicacionAsientos, int tipoEntrada) {
        int filasInicio = 0;
        int filasFin = ubicacionAsientos.getAsientos().length;

        switch (tipoEntrada) {
            case 1: // Standard
            case 3: // 2x1 Standard
                filasFin = 5; // Filas 1 a 5 (filas 0 a 4 en la matriz)
                break;
            case 2: // Premium
            case 4: // 2x1 Premium
                filasInicio = 4; // Filas 5 a 6 (filas 5 a 6 en la matriz)
                filasFin = 6;   // Limitar a filas 5 y 6 (filas 5 y 6 en la matriz)
                break;
        }

        int asientosDisponibles = 0;
        for (int fila = filasInicio; fila < filasFin; fila++) {
            for (int columna = 0; columna < ubicacionAsientos.getAsientos()[fila].length; columna++) {
                if (!ubicacionAsientos.getAsientos()[fila][columna]) {
                    asientosDisponibles++;
                }
            }
        }
        return asientosDisponibles;
    }
}








