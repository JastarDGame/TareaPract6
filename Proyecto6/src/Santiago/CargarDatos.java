package Santiago;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

import MisMetodos.TJOption;
import ed.ito.ArbolBinario;
import ed.ito.ExcepcionNodoRepetido;

public class CargarDatos {
    private ArbolBinario<CorridaAutobus> owo; 

    // Constructor sin parámetros, inicializa el árbol binario
    public CargarDatos() {
        this.owo = new ArbolBinario<CorridaAutobus>(); // Inicializamos el árbol binario aquí
    }
    
    // Constructor con un árbol binario ya inicializado
    public CargarDatos(ArbolBinario<CorridaAutobus> owo) {
        this.owo = owo;
    }

    // Método estático para cargar datos desde un archivo
    public static void cargarArchivo(String nombreArchivo, ArbolBinario<CorridaAutobus> arbol) throws ExcepcionNodoRepetido {
        String linea;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 7) {
                    String lineaAutobus = datos[0];
                    LocalDate fecha = LocalDate.parse(datos[1], formatoFecha);
                    String hora = datos[2];
                    String origen = datos[3];
                    String destino = datos[4];
                    String tiempo = datos[5];
                    double costo = Double.parseDouble(datos[6]);

                    CorridaAutobus corrida = new CorridaAutobus(lineaAutobus, fecha, hora, origen, destino, tiempo, costo);
                    arbol.add(corrida);  // Añadir al árbol binario
                } else {
                    System.err.println("Línea con datos incompletos: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (ExcepcionNodoRepetido e) {
            System.err.println("Error: El nodo ya existe en el árbol.");
        } catch (Exception e) {
            System.err.println("Error al procesar los datos: " + e.getMessage());
        }
    }

    // Método para crear una nueva línea de autobús
    public void crearLinea() {
        // Obtenemos la línea de autobús del usuario
        String linea = TJOption.desplegableLineas("ADO,Estrella de Oro,Primera Plus,Estrella Blanca,Flecha Amarilla,Metro Politano,Ormeño,Expreso Brasilia");
        
        // Obtenemos la fecha de la corrida
        LocalDate fecha = obtenerFecha("Fecha de partida");
        
        // Obtenemos la hora de la corrida
        String hora = TJOption.leerString("Ingresa a qué hora llega: ");
        
        // Validamos la hora
        if (!esHoraValida(hora)) {
            JOptionPane.showMessageDialog(null, "Hora no válida. El formato debe ser HH:mm.");
            return;
        }

        // Obtenemos el origen de la corrida
        TJOption.imprimePantalla("Ingrese el Origen");
        String origen = TJOption.menuOrigenYDestino("Orizaba,Cordoba,Mendoza,Veracruz,Oaxaca,Edo. Mex.,Queretaro,San Juan del Rio,Chiapas,Tijuana");

        // Obtenemos el destino, asegurándonos de que sea diferente al origen
        String destino = obtenerDestinoDiferenteDeOrigen(origen);

        // Obtenemos el tiempo estimado y el costo del viaje
        String tiempo = TJOption.leerString("Ingresa el tiempo que se tarda el autobús");
        double costo = TJOption.leerDouble("Ingresa el costo total del Viaje");

        // Validamos que el costo sea positivo
        if (costo < 0) {
            JOptionPane.showMessageDialog(null, "El costo no puede ser negativo.");
            return;
        }

        // Creamos la nueva instancia de CorridaAutobus
        CorridaAutobus e = new CorridaAutobus(linea, fecha, hora, origen, destino, tiempo, costo);

        // Intentamos agregar la corrida al árbol
        agregar(e);

        // Guardamos la corrida en el archivo "lineas.txt"
        try {
            e.guardarEnArchivo("lineas.txt"); // Guardamos la corrida en el archivo "lineas.txt"
            JOptionPane.showMessageDialog(null, "Línea agregada y guardada exitosamente.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la corrida en el archivo: " + ex.getMessage());
        }
    }

    // Método para verificar si la hora ingresada es válida
    private boolean esHoraValida(String hora) {
        try {
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Método para obtener el destino diferente al origen
    private String obtenerDestinoDiferenteDeOrigen(String origen) {
        String destino;
        boolean bandera = true;  
        do {
            TJOption.imprimePantalla("Ingrese el Destino");
            destino = TJOption.menuOrigenYDestino("Orizaba,Cordoba,Mendoza,Veracruz,Oaxaca,Edo. Mex.,Queretaro,San Juan del Rio,Chiapas,Tijuana");
            
            if (!origen.equalsIgnoreCase(destino)) {
                bandera = false;
            } else {
                TJOption.imprimeError("Selecciona un Destino diferente al Origen");
            }
        } while (bandera);        
        return destino; 
    }

    // Método para obtener la fecha de la corrida
    private static LocalDate obtenerFecha(String tipo) {
        while (true) {
            try {
                int mes = TJOption.menuMeses("Enero,Febrero,Marzo,Abril,Mayo,Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre");
                int dia = Integer.parseInt(TJOption.menuDias(mes));
                int año = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año"));

                return LocalDate.of(año, mes, dia);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, "Fecha no válida. Por favor ingrese una fecha correcta.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato de número incorrecto. Intenta de nuevo.");
            }
        }
    }

    // Método para agregar una corrida al árbol binario
    private void agregar(CorridaAutobus e) {
        if (e != null) {
            try {
                owo.add(e); // Intentamos agregar al árbol binario
            } catch (ExcepcionNodoRepetido ex) {
                JOptionPane.showMessageDialog(null, "Error: El nodo con la corrida ya existe en el árbol.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: La corrida no puede ser nula.");
        }
    }
}
