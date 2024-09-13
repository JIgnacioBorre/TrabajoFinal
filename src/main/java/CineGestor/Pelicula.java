package CineGestor;

import java.io.*;
import java.util.*;

public class Pelicula {
    private List<String> titulos = new ArrayList<>();

    public Pelicula() {
        cargarTitulos();
    }

    private void cargarTitulos() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Peliculas"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Usar punto y coma como delimitador
                String[] partes = linea.split(";", 2); // Separar por el primer punto y coma
                if (partes.length > 1) {
                    // La segunda parte contiene el título y el resto de los datos
                    String resto = partes[1];
                    String[] restoPartes = resto.split(";", 2); // Separar el título de los otros datos
                    if (restoPartes.length > 0) {
                        String titulo = restoPartes[0].trim(); // Obtener el título
                        titulos.add(titulo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerTitulos() {
        return titulos;
    }
}











