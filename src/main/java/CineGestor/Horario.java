package CineGestor;

import java.util.ArrayList;
import java.util.List;

public class Horario {

    // Lista de horarios disponibles
    private List<String> horarios;

    public Horario() {
        this.horarios = new ArrayList<>();
        inicializarHorarios();
    }

    private void inicializarHorarios() {
        // Horarios iniciales
        String[] horariosArray = {
                "16:30", "19:30", "22:30"
        };

        for (String horario : horariosArray) {
            horarios.add(horario);
        }
    }

    public List<String> obtenerHorarios() {
        return horarios;
    }
}

