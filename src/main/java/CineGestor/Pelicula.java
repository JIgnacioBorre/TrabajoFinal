package CineGestor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Pelicula {
    private int id;
    private String titulo;
    private int anio;
    private int duracion;
    private String actores;
    private String genero;
    private String director;
    private Map<String, Sala> horariosConSalas;

    private static List<Pelicula> peliculas = new ArrayList<>();

    // Constructor con argumentos
    public Pelicula(int id, String titulo, int anio, int duracion, String actores, String genero, String director) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.duracion = duracion;
        this.actores = actores;
        this.genero = genero;
        this.director = director;
        this.horariosConSalas = new LinkedHashMap<String, Sala>(); // Mantener el orden de inserción
    }

    // Constructor sin argumentos
    public Pelicula() {
        this.horariosConSalas = new LinkedHashMap<String, Sala>(); // Mantener el orden de inserción
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getActores() {
        return actores;
    }

    public String getGenero() {
        return genero;
    }

    public String getDirector() {
        return director;
    }

    public Map<String, Sala> obtenerHorariosConSalas() {
        return horariosConSalas;
    }

    public void asignarHorariosConSalas(Map<String, Sala> asignaciones) {
        this.horariosConSalas = asignaciones;
    }

    public static void cargarPeliculasDesdeArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 7);
                if (parts.length == 7) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String titulo = parts[1].trim();
                        int anio = Integer.parseInt(parts[2].trim());
                        int duracion = Integer.parseInt(parts[3].trim());
                        String actores = parts[4].trim();
                        String genero = parts[5].trim();
                        String director = parts[6].trim();

                        Pelicula pelicula = new Pelicula(id, titulo, anio, duracion, actores, genero, director);
                        peliculas.add(pelicula);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir los datos numéricos en el archivo de películas: " + line);
                    }
                } else {
                    System.err.println("Formato inválido en el archivo de películas: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pelicula> getPeliculas() {
        return peliculas;
    }


}















