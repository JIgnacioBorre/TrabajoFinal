package CineGestor;

public class UbicacionAsientos {
    private boolean[][] asientos; // Matriz de asientos

    public UbicacionAsientos() {
        int filas = 10;
        int columnas = 5;
        asientos = new boolean[filas][columnas]; // Inicializar todos los asientos como disponibles
    }

    // Mostrar los asientos disponibles con números de fila
    public void mostrarAsientos(int tipoEntrada) {
        int filasInicio;
        int filasFin;

        switch (tipoEntrada) {
            case 1: // Standard
            case 3: // 2x1 Standard
                System.out.println("Asientos disponibles para Standard:");
                // Mostrar filas 1 a 4
                filasInicio = 0; // 0 para la fila 1
                filasFin = 4; // 4 para la fila 4
                for (int fila = filasInicio; fila < filasFin; fila++) {
                    System.out.print("Fila " + (fila + 1) + ": ");
                    for (int columna = 0; columna < asientos[fila].length; columna++) {
                        System.out.print((asientos[fila][columna] ? "[X]" : "[ ]") + " ");
                    }
                    System.out.println();
                }
                // Mostrar filas 7 a 10
                filasInicio = 6; // 6 para la fila 7
                filasFin = 10; // 10 para la fila 10
                for (int fila = filasInicio; fila < filasFin; fila++) {
                    System.out.print("Fila " + (fila + 1) + ": ");
                    for (int columna = 0; columna < asientos[fila].length; columna++) {
                        System.out.print((asientos[fila][columna] ? "[X]" : "[ ]") + " ");
                    }
                    System.out.println();
                }
                break;
            case 2: // Premium
            case 4: // 2x1 Premium
                filasInicio = 4; // Filas 5 a 6 (filas 4 a 5 en la matriz)
                filasFin = 6;   // Limitar a filas 5 y 6 (filas 4 y 5 en la matriz)
                System.out.println("Asientos disponibles para Premium:");
                for (int fila = filasInicio; fila < filasFin; fila++) {
                    System.out.print("Fila " + (fila + 1) + ": ");
                    for (int columna = 0; columna < asientos[fila].length; columna++) {
                        System.out.print((asientos[fila][columna] ? "[X]" : "[ ]") + " ");
                    }
                    System.out.println();
                }
                break;
        }
    }

    // Mostrar todos los asientos con el estado (ocupado o libre)
    public void mostrarTodosLosAsientos() {
        System.out.println("Todos los asientos:");
        for (int fila = 0; fila < asientos.length; fila++) {
            System.out.print("Fila " + (fila + 1) + ": "); // Mostrar el número de fila
            for (int columna = 0; columna < asientos[fila].length; columna++) {
                System.out.print((asientos[fila][columna] ? "[X]" : "[ ]") + " ");
            }
            System.out.println();
        }
    }

    // Seleccionar un asiento
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

    // Getter para los asientos (opcional)
    public boolean[][] getAsientos() {
        return asientos;
    }
}


