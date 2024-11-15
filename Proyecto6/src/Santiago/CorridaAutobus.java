package Santiago;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CorridaAutobus implements Comparable<CorridaAutobus> {
    private String linea;
    private LocalDate fecha;
    private String hora;
    private String origen;
    private String destino;
    private String tiempo;
    private double costo;
           
    public CorridaAutobus(String linea, LocalDate fecha, String hora, String origen, String destino, String tiempo, double costo) {
        this.linea = linea;
        this.fecha = fecha;
        this.hora = hora;
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;
        this.costo = costo;
    }

    public CorridaAutobus(String origen, String destino, LocalDate fecha) {
        this.origen = origen;
        this.fecha = fecha;
        this.destino = destino;     
        this.linea = null;
        this.hora = null;
        this.tiempo = null;
        this.costo = 0;
    }

    public String getLinea() { return linea; }
    public LocalDate getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public String getTiempo() { return tiempo; }
    public double getCosto() { return costo; }

    // Método para guardar la corrida en un archivo de texto
    public void guardarEnArchivo(String nombreArchivo) throws IOException {
        // Formato para fecha
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Abrir el archivo en modo append para no sobrescribir
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            String lineaCorrida = this.linea + "," + 
                                  this.fecha.format(formatoFecha) + "," + 
                                  this.hora + "," + 
                                  this.origen + "," + 
                                  this.destino + "," + 
                                  this.tiempo + "," + 
                                  this.costo;
            writer.write(lineaCorrida);
            writer.newLine();  // Escribir nueva línea
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Línea: %s, Fecha: %s, Hora: %s, Origen: %s, Destino: %s, Tiempo: %s, Costo: $%.2f", 
                             this.linea, 
                             this.fecha.format(formatter), 
                             this.hora, 
                             this.origen, 
                             this.destino, 
                             this.tiempo, 
                             this.costo);
    }
  
    @Override
    public int compareTo(CorridaAutobus otra) {
        int comparacionFecha = this.fecha.compareTo(otra.fecha);
        if (comparacionFecha != 0) {
            return comparacionFecha;
        }

        int comparacionHora = this.hora.compareTo(otra.hora);
        if (comparacionHora != 0) {
            return comparacionHora;
        }
       
        int comparacionOrigen = this.origen.compareTo(otra.origen);
        if (comparacionOrigen != 0) {
            return comparacionOrigen;
        }

        return this.destino.compareTo(otra.destino);
    }
}

