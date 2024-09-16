package CineGestor;

public class UbicacionAsientos {
    private boolean[][] asientos; // Matriz de asientos

    public UbicacionAsientos() {
        int filas = 10;
        int columnas = 5;
        asientos = new boolean[filas][columnas]; // Inicializar todos los asientos como disponibles
    }

    public void mostrarAsientos() {
        for (int fila = 0; fila < asientos.length; fila++) {
            for (int columna = 0; columna < asientos[fila].length; columna++) {
                System.out.print((asientos[fila][columna] ? "[X]" : "[ ]") + " ");
            }
            System.out.println();
        }
    }

    public boolean seleccionarAsiento(int fila, int columna) {
        if (fila < 1 || fila > asientos.length || columna < 1 || columna > asientos[0].length) {
            return false; // Asiento fuera de rango
        }
        fila--; // Ajuste de índice para matrices 0-basadas
        columna--;
        if (!asientos[fila][columna]) {
            asientos[fila][columna] = true; // Marcar como ocupado
            return true;
        }
        return false; // Asiento ya ocupado
    }
}
