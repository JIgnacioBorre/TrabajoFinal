package CineGestor;

public class Pelicula {
    private String titulo;
    private String director;
    private String genero;
    private int duracion;
    private int year;

    public Pelicula(String titulo, String director, String genero, int duracion, int year) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
        this.year = year;
    }

    public String getTitulo() {return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDirector() {return director; }
    public void setDirector(String director) { this.director = director; }

    public String getGenero() {return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getDuracion() {return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public int getYear() {return year; }
    public void setYear(int year) { this.year = year; }

    public String toString() {
        return titulo + " (" + year + ")";
    }









}
