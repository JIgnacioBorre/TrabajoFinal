package CineGestor;

public class Sala {
    private final int numero;
    private final int capacidad;
    private UbicacionAsientos ubicacionAsientos; // Añadido

    public Sala(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacionAsientos = new UbicacionAsientos(); // Inicialización
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public UbicacionAsientos getUbicacionAsientos() {
        return ubicacionAsientos;
    }

    @Override
    public String toString() {
        return "Sala " + numero + " (Capacidad: " + capacidad + ")";
    }
}