package CineGestor;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Pelicula gestor = new Pelicula();

        // Mostrar títulos
        List<String> titulos = gestor.obtenerTitulos();
        System.out.println("Películas disponibles:");
        for (String titulo : titulos) {
            System.out.println("- " + titulo);
        }
    }
}