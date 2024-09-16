package CineGestor;

public class Sala {
    private final int numero;
    private final int capacidad;

    // Constructor para inicializar número y capacidad de la sala
    public Sala(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Sala " + numero + " (Capacidad: " + capacidad + ")";
    }
}