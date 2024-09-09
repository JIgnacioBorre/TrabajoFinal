package CineGestor;

import java.util.ArrayList;
import java.util.List;

public class GestorCine {
    private List<Pelicula> peliculas;
    private List<Sala> salas;
    private List<Reserva> reservas;
    private List<Menu> menu;

    public GestorCine() {
        peliculas = new ArrayList<>();
        salas = new ArrayList<>();
        reservas = new ArrayList<>();
        menu = new ArrayList<>();
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
    public List<Menu> getMenù() {return menu}

    public void mostrarPeliculas() {
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula);
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

    public void mostrarMenù(){
        for (Menu menu :)
    }
}
