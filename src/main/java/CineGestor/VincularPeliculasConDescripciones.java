package CineGestor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VincularPeliculasConDescripciones {

    public static void main(String[] args) {
        String peliculasFile = "src/main/resources/Peliculas";
        String descripcionesFile = "src/main/resources/Descripciones";

        // Map para almacenar las descripciones con ID como clave
        Map<Integer, String> descripcionesMap = new HashMap<>();

        // Leer el archivo de descripciones y almacenar en el Map
        try (BufferedReader reader = new BufferedReader(new FileReader(descripcionesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String descripcion = parts[1].trim();
                        descripcionesMap.put(id, descripcion);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir el ID a número en descripciones.txt: " + parts[0]);
                    }
                } else {
                    System.err.println("Formato inválido en descripciones.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de películas y mostrar información
        try (BufferedReader reader = new BufferedReader(new FileReader(peliculasFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Asegúrate de usar el delimitador correcto, en este caso, la coma
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

                        // Obtener descripción desde el mapa si existe
                        String descripcionFromMap = descripcionesMap.get(id);

                        // Mostrar la información
                        System.out.println("ID: " + id);
                        System.out.println("Título: " + titulo);
                        System.out.println("Año: " + anio);
                        System.out.println("Duración: " + duracion + " minutos");
                        System.out.println("Actores: " + actores);
                        System.out.println("Género: " + genero);
                        System.out.println("Director: " + director);
                        System.out.println("Descripción: " + (descripcionFromMap != null ? descripcionFromMap : "No disponible"));
                        System.out.println();
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir los datos numéricos en peliculas.txt: " + line);
                    }
                } else {
                    System.err.println("Formato inválido en peliculas.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
