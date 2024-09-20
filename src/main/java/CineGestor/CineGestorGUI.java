package CineGestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class CineGestorGUI extends JFrame {
    private JComboBox<Pelicula> peliculasComboBox;
    private JTextArea infoTextArea;
    private JComboBox<String> formatoComboBox;
    private JComboBox<String> horariosComboBox; // Nuevo JComboBox para horarios
    private JButton comprarButton;

    private final SalaManager salaManager;
    private final int capacidadSala = 50; // Capacidad de todas las salas

    public CineGestorGUI(SalaManager salaManager) {
        this.salaManager = salaManager;

        setTitle("Cine Gestor");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Cargar películas en el comboBox
        peliculasComboBox = new JComboBox<>(Pelicula.getPeliculas().toArray(new Pelicula[0]));
        add(peliculasComboBox);

        infoTextArea = new JTextArea(10, 40);
        infoTextArea.setEditable(false);
        add(new JScrollPane(infoTextArea));

        formatoComboBox = new JComboBox<>(new String[]{"2D Doblada", "2D Subtitulada", "3D Doblada", "3D Subtitulada"});
        add(formatoComboBox);

        horariosComboBox = new JComboBox<>(); // Inicializar el JComboBox para horarios
        add(horariosComboBox);

        comprarButton = new JButton("Comprar Entrada");
        add(comprarButton);

        // Acción para el botón
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });

        // Inicializar la interfaz
        mostrarInfoPelicula();
        peliculasComboBox.addActionListener(e -> mostrarInfoPelicula());
    }

    private void mostrarInfoPelicula() {
        Pelicula peliculaSeleccionada = (Pelicula) peliculasComboBox.getSelectedItem();
        if (peliculaSeleccionada != null) {
            StringBuilder info = new StringBuilder();
            info.append("Título: ").append(peliculaSeleccionada.getTitulo()).append("\n")
                    .append("Año: ").append(peliculaSeleccionada.getAnio()).append("\n")
                    .append("Duración: ").append(peliculaSeleccionada.getDuracion()).append(" minutos\n")
                    .append("Actores: ").append(peliculaSeleccionada.getActores()).append("\n")
                    .append("Género: ").append(peliculaSeleccionada.getGenero()).append("\n")
                    .append("Director: ").append(peliculaSeleccionada.getDirector()).append("\n")
                    .append("Formato: ").append(peliculaSeleccionada.getFormato()).append("\n");

            infoTextArea.setText(info.toString());
            mostrarHorariosDisponibles(peliculaSeleccionada);
        }
    }

    private void mostrarHorariosDisponibles(Pelicula pelicula) {
        horariosComboBox.removeAllItems(); // Limpiar horarios anteriores
        Map<String, Sala> horariosConSalas = pelicula.obtenerHorariosConSalas();
        for (Map.Entry<String, Sala> entry : horariosConSalas.entrySet()) {
            horariosComboBox.addItem(entry.getKey()); // Añadir cada horario al JComboBox
        }
    }

    private void realizarCompra() {
        Pelicula peliculaSeleccionada = (Pelicula) peliculasComboBox.getSelectedItem();
        String formatoSeleccionado = (String) formatoComboBox.getSelectedItem();
        String horarioSeleccionado = (String) horariosComboBox.getSelectedItem();

        // Pedir el tipo de entrada primero
        String[] opcionesEntrada = {"Standard", "Premium", "2x1 Standard", "2x1 Premium"};
        String tipoEntrada = (String) JOptionPane.showInputDialog(
                this,
                "Selecciona el tipo de entrada:",
                "Tipo de Entrada",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEntrada,
                opcionesEntrada[0] // Valor por defecto
        );

        if (tipoEntrada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un tipo de entrada.");
            return;
        }

        // Pedir la cantidad de entradas
        String cantidadStr = JOptionPane.showInputDialog(this, "¿Cuántas entradas deseas comprar?");
        int cantidadEntradas;

        try {
            cantidadEntradas = Integer.parseInt(cantidadStr);
            if (cantidadEntradas <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Entrada no válida. Introduce un número.");
            return;
        }

        if (horarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un horario.");
            return;
        }

        // Lógica para seleccionar asientos
        seleccionarAsientos(tipoEntrada, horarioSeleccionado, formatoSeleccionado, cantidadEntradas);
    }

    private void seleccionarAsientos(String tipoEntrada, String horarioSeleccionado, String formatoSeleccionado, int cantidadEntradas) {
        UbicacionAsientos ubicacionAsientos = new UbicacionAsientos(); // Asegúrate de tener esta clase disponible

        // Definir rangos de filas según el tipo de entrada
        int filasInicio = 0, filasFin = 0;

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

        // Mostrar todos los asientos y permitir la selección
        for (int i = 0; i < cantidadEntradas; i++) {
            String mensaje = String.format("Selecciona el asiento %d de %d:\n", i + 1, cantidadEntradas);
            int fila = Integer.parseInt(JOptionPane.showInputDialog(this, mensaje + "Introduce la fila (" + (filasInicio + 1) + " a " + (filasFin + 1) + "):"));
            int columna = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce la columna (1 a 5):"));

            // Validar fila y columna
            if (fila < filasInicio + 1 || fila > filasFin + 1 || columna < 1 || columna > 5) {
                JOptionPane.showMessageDialog(this, "Fila o columna fuera de rango. Por favor, elige otra.");
                i--; // Repetir selección para este asiento
                continue;
            }

            // Verificar selección del asiento
            if (ubicacionAsientos.seleccionarAsiento(fila - 1, columna - 1)) {
                JOptionPane.showMessageDialog(this, "Asiento seleccionado: Fila " + fila + ", Columna " + columna);
            } else {
                JOptionPane.showMessageDialog(this, "Asiento ya ocupado o fuera de rango. Por favor, elige otro.");
                i--; // Repetir selección para este asiento
            }
        }

        // Mostrar un resumen de la compra
        JOptionPane.showMessageDialog(this, "Compra realizada para " + cantidadEntradas + " entradas de " + formatoSeleccionado +
                " en el horario " + horarioSeleccionado + " con tipo de entrada " + tipoEntrada);
    }

    public static void main(String[] args) {
        // Cargar películas desde el archivo
        Pelicula.cargarPeliculasDesdeArchivo("src/main/resources/Peliculas");

        // Asignar horarios y salas a cada película
        SalaManager salaManager = new SalaManager(50);
        for (Pelicula pelicula : Pelicula.getPeliculas()) {
            Map<String, Sala> asignaciones = salaManager.asignarSalasParaPelicula();
            pelicula.asignarHorariosConSalas(asignaciones);
        }

        // Crear y mostrar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            CineGestorGUI gui = new CineGestorGUI(salaManager);
            gui.setVisible(true);
        });
    }
}
