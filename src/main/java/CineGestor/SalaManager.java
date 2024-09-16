package CineGestor;

import java.util.*;

public class SalaManager {
    private List<String> horarios;
    private List<Integer> salas;
    private Map<String, Set<Integer>> asignacionesPorHorario;
    private final int capacidadSala;

    // Constructor que acepta la capacidad de la sala
    public SalaManager(int capacidadSala) {
        this.horarios = Arrays.asList("16:30", "19:30", "22:30");
        this.salas = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        this.asignacionesPorHorario = new LinkedHashMap<>();
        this.capacidadSala = capacidadSala;

        // Inicializar asignaciones por horario
        for (String horario : horarios) {
            asignacionesPorHorario.put(horario, new HashSet<>(salas));
        }
    }

    // Método para asignar salas a una película
    public Map<String, Sala> asignarSalasParaPelicula() {
        Map<String, Sala> asignaciones = new LinkedHashMap<>();

        for (String horario : horarios) {
            Set<Integer> salasDisponibles = asignacionesPorHorario.get(horario);
            if (salasDisponibles.isEmpty()) {
                System.err.println("No hay suficientes salas disponibles.");
                break;
            }

            // Asignar una sala aleatoria disponible
            Integer salaAsignada = obtenerSalaAleatoria(salasDisponibles);
            asignaciones.put(horario, new Sala(salaAsignada, capacidadSala));

            // Eliminar la sala de las disponibles
            salasDisponibles.remove(salaAsignada);
        }

        return asignaciones;
    }

    // Método para obtener una sala aleatoria de un conjunto
    private Integer obtenerSalaAleatoria(Set<Integer> salasDisponibles) {
        List<Integer> salasList = new ArrayList<>(salasDisponibles);
        Collections.shuffle(salasList);
        return salasList.get(0);
    }
}
