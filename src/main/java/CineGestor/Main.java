package CineGestor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorCine gestor = new GestorCine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar Película");
            System.out.println("2. Agregar Sala");
            System.out.println("3. Hacer Reserva");
            System.out.println("4. Mostrar Películas");
            System.out.println("5. Mostrar Salas");
            System.out.println("6. Mostrar Reservas");
            System.out.println("7. Mostrar Menu");
            System.out.println("8. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el título de la película:");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese el director de la película:");
                    String director = scanner.nextLine();
                    System.out.println("Ingrese el género de la película:");
                    String genero = scanner.nextLine();
                    System.out.println("Ingrese la duración de la película (en minutos):");
                    int duracion = scanner.nextInt();
                    System.out.println("Ingrese el año de la película:");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                    Pelicula pelicula = new Pelicula(titulo, director, genero, duracion, year);
                    gestor.agregarPelicula(pelicula);
                    break;

                case 2:
                    System.out.println("Ingrese el número de la sala:");
                    int numero = scanner.nextInt();
                    System.out.println("Ingrese la capacidad de la sala:");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                    Sala sala = new Sala(numero, capacidad);
                    gestor.agregarSala(sala);
                    break;

                case 3:
                    System.out.println("Ingrese el título de la película para la reserva:");
                    String tituloReserva = scanner.nextLine();
                    Pelicula peliculaReserva = null;
                    for (Pelicula p : gestor.getPeliculas()) {
                        if (p.getTitulo().equals(tituloReserva)) {
                            peliculaReserva = p;
                            break;
                        }
                    }
                    if (peliculaReserva == null) {
                        System.out.println("Película no encontrada.");
                        break;
                    }
                    System.out.println("Ingrese el número de la sala para la reserva:");
                    int numeroSala = scanner.nextInt();
                    Sala salaReserva = null;
                    for (Sala s : gestor.getSalas()) {
                        if (s.getNumero() == numeroSala) {
                            salaReserva = s;
                            break;
                        }
                    }
                    if (salaReserva == null) {
                        System.out.println("Sala no encontrada.");
                        break;
                    }
                    scanner.nextLine(); // Consumir nueva línea
                    System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD):");
                    String fecha = scanner.nextLine();
                    System.out.println("Ingrese el número de asientos reservados:");
                    int asientosReservados = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                    Reserva reserva = new Reserva(peliculaReserva, salaReserva, fecha, asientosReservados);
                    gestor.hacerReserva(reserva);
                    break;

                case 4:
                    System.out.println("Películas:");
                    gestor.mostrarPeliculas();
                    break;

                case 5:
                    System.out.println("Salas:");
                    gestor.mostrarSalas();
                    break;

                case 6:
                    System.out.println("Reservas:");
                    gestor.mostrarReservas();
                    break;

                case 7:
                    System.out.println("Menu:");
                    gestor.mostrarMenù();
                    break;

                case 8:
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }
}
