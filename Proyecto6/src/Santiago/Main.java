package Santiago;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import MisMetodos.TJOption;
import ed.ito.ArbolBinario;
import ed.ito.ExcepcionNodoRepetido;

public class Main {
    public static void main(String[] args) throws ExcepcionNodoRepetido {
        ArbolBinario<CorridaAutobus> arbol = new ArbolBinario<>();
        ComparadorCorrida comparador = new ComparadorCorrida();  
        CorridaAutobus obj0;

        // Cargar datos del archivo
        CargarDatos.cargarArchivo("corridas.txt", arbol);

        TJOption.imprimePantalla("Bienvenido a AD-ITO");
        
        while (true) {
            LocalDate fecha = obtenerFecha("Fecha de partida");
            
            TJOption.imprimePantalla("Ingrese el Origen");
            String origen = TJOption.menuOrigenYDestino("Orizaba,Cordoba,Mendoza,Veracruz,Oaxaca,Edo. Mex.,Queretaro,San Juan del Rio,Chiapas,Tijuana");
            String destino = null;
            boolean bandera = true;
            do {
                TJOption.imprimePantalla("Ingrese el Destino");
                String destino1 = TJOption.menuOrigenYDestino("Orizaba,Cordoba,Mendoza,Veracruz,Oaxaca,Edo. Mex.,Queretaro,San Juan del Rio,Chiapas,Tijuana");
                if(!origen.equalsIgnoreCase(destino1)) {
                    destino = destino1;
                    bandera = false;
                } else {
                    TJOption.imprimeError("Selecciona un Destino diferente");
                }
            } while (bandera);
            
            obj0 = new CorridaAutobus(origen, destino, fecha);
            List<CorridaAutobus> resultados = arbol.search(comparador, obj0);

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron corridas.");
            } else {
                System.out.println("Corridas encontradas:");
                for (CorridaAutobus corrida : resultados) {
                    System.out.println(corrida);
                    System.out.println("-------------");
                }
            }

            String opcion = TJOption.leerString("¿Desea realizar otra consulta? (s/n): ");
            if (!opcion.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

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
}