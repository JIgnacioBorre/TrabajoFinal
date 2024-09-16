package CineGestor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VincularPeliculasConDescripciones {

    private Map<Integer, String> descripcionesMap;

    public VincularPeliculasConDescripciones(String descripcionesFile) {
        this.descripcionesMap = new HashMap<>();
        cargarDescripcionesDesdeArchivo(descripcionesFile);
    }

    private void cargarDescripcionesDesdeArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String descripcion = parts[1].trim();
                        descripcionesMap.put(id, descripcion);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir el ID a número en descripciones: " + parts[0]);
                    }
                } else {
                    System.err.println("Formato inválido en descripciones.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerDescripcion(int id) {
        return descripcionesMap.getOrDefault(id, "Descripción no disponible");
    }
}
