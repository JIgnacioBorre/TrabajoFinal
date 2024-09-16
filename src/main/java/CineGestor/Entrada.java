package CineGestor;

public class Entrada {
    public static final double PRECIO_STANDARD = 5000.0;
    public static final double PRECIO_PREMIUM = 5500.0;
    public static final double PRECIO_2X1_STANDARD = 8000.0; // Precio ya descontado para 2x1
    public static final double PRECIO_2X1_PREMIUM = 9000.0; // Precio ya descontado para 2x1

    private int tipo;
    private Pelicula pelicula;
    private String horario;

    public Entrada(int tipo, Pelicula pelicula, String horario) {
        this.tipo = tipo;
        this.pelicula = pelicula;
        this.horario = horario;
    }

    public double calcularPrecioUnitario() {
        switch (tipo) {
            case 1: // Standard
                return PRECIO_STANDARD;
            case 2: // Premium
                return PRECIO_PREMIUM;
            case 3: // 2x1 Standard
                return PRECIO_2X1_STANDARD;
            case 4: // 2x1 Premium
                return PRECIO_2X1_PREMIUM;
            default:
                throw new IllegalArgumentException("Tipo de entrada no válido");
        }
    }

    public boolean es2x1() {
        return tipo == 3 || tipo == 4;
    }
}

