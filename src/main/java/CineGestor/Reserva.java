package CineGestor;

public class Reserva {
    private Pelicula pelicula;
    private Sala sala;
    private String fecha;
    private int asientosReservados;

    public Reserva(Pelicula pelicula, Sala sala, String fecha, int asientosReservados) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.asientosReservados = asientosReservados;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(int asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public String toString() {
        return pelicula.toString() + "en" + sala + "el" + fecha + " - Asientos reservados: " + asientosReservados;
    }

}