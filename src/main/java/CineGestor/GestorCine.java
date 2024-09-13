package CineGestor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorCine {
    private List<Pelicula> peliculas;
    private List<Sala> salas;
    private List<Reserva> reservas;

    public GestorCine() {
        peliculas = new ArrayList<>();
        salas = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }
    public void agregarSala(Sala sala) {
        salas.add(sala);
    }
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }
    public List<Sala> getSalas() {
        return salas;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }

    public static void  mostrarPeliculas() {
        List<String> lineasDelArchivo = new ArrayList<>();
        List<Pelicula> peliculas  = new ArrayList<>();
        try{
                File archivoConLineas = new File("src/main/resources/Peliculas");
            Scanner LectorDeArchivo = new Scanner(archivoConLineas);
            while (LectorDeArchivo.hasNextLine()) {
                String Linea = LectorDeArchivo.nextLine();
                lineasDelArchivo.add(Linea);
            }
            LectorDeArchivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
            e.printStackTrace();
        }
        for(String linea:lineasDelArchivo){
            System.out.println(linea);
        }
    }
    public void mostrarSalas() {
        for (Sala sala : salas) {
            System.out.println(sala);
        }
    }
    public void mostrarReservas() {
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    public void hacerReserva(Reserva reserva) {
    }
}
