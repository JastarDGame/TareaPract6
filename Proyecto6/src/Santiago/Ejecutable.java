package Santiago;

import javax.swing.JOptionPane;

public class Ejecutable {
    public static void main(String[] args) {
        menu2("Hacer Consulta,Crear nueva linea,Salir");
    }

    public static String desplegable(String menu){
        String valores[] = menu.split(",");
        return (String)JOptionPane.showInputDialog(null, "M E N U", "Selecciona una opción:",
                JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }

    public static void menu2(String menu) {
        CargarDatos uwu = new CargarDatos();
        String sel;

        try {
            do {
                sel = desplegable(menu);
                if (sel == null) {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    continue;  // Si la selección es null, continúa pidiendo la opción
                }
                switch (sel) {
                    case "Hacer Consulta":
                        Main.main(new String[0]);
                        break;
                    case "Crear nueva linea":
                        uwu.crearLinea();
                        break;
                    case "Salir":
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } while (!"Salir".equals(sel)); // Usamos equals() para evitar errores de null

        } catch (Exception e) {
            System.out.println("Opción no válida. Intenta de nuevo.");
            e.printStackTrace();  // Esto nos dará detalles del error
        }
    }

}

