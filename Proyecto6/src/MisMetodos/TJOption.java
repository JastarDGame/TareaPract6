package MisMetodos;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class TJOption {

    public static int leerInt(String msje) {
        return Integer.parseInt(JOptionPane.showInputDialog(null, msje, "[int]", JOptionPane.QUESTION_MESSAGE));
    }

    public static long leerLong(String msje) {
        while (true) {
            try {
                return Long.parseLong(JOptionPane.showInputDialog(null, msje, "[long]", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                imprimeError("Solo se admiten enteros en el rango " + Long.MIN_VALUE + " y " + Long.MAX_VALUE);
            }
        }
    }

    public static float leerFloat(String msje) {
        while (true) {
            try {
                return Float.parseFloat(JOptionPane.showInputDialog(null, msje, "[float]", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                imprimeError("Solo se admiten números en el rango " + Float.MIN_VALUE + " y " + Float.MAX_VALUE);
            }
        }
    }

    public static String leerString(String msje) {
        return JOptionPane.showInputDialog(null, msje, "[String]", JOptionPane.QUESTION_MESSAGE);
    }

    public static double leerDouble(String msje) {
        while (true) {
            try {
                return Double.parseDouble(JOptionPane.showInputDialog(null, msje, "[double]", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                imprimeError("Solo se admiten números en el rango " + Double.MIN_VALUE + " y " + Double.MAX_VALUE);
            }
        }
    }

    public static void imprimePantalla(String msje) {
        JOptionPane.showMessageDialog(null, msje, "Salida", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void imprimeError(String msje) {
        JOptionPane.showMessageDialog(null, msje, "¡¡¡ERROR!!!", JOptionPane.ERROR_MESSAGE);
    }

    public static void panelScroll(String cad) {
        JTextArea areaSalida = new JTextArea(10, 4);
        JScrollPane scroller = new JScrollPane(areaSalida);
        Font font = new Font("Arial", Font.BOLD, 15);
        areaSalida.setFont(font);
        areaSalida.setForeground(Color.black);
        areaSalida.setBackground(Color.WHITE);
        areaSalida.append(" " + cad);
        JOptionPane.showMessageDialog(null, scroller, "Datos: ", JOptionPane.PLAIN_MESSAGE);
    }

    public static String boton(String menu) {
        String[] valores = menu.split(",");
        int n = JOptionPane.showOptionDialog(null, "SELECCIONA DANDO CLICK", "M E N U", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
        return valores[n];
    }
    
    public static String desplegableLugares(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "L U G A R E S", "Selecciona una opcion: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }

    public static String desplegableEspecialidades(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "E S P E C I A L I D A D E S", "Selecciona una opcion: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }
    
    public static String desplegableLineas(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "L I N E A S", "Selecciona una opcion: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }
    
    public static String desplegableAreasInteres(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "A R E A S", "Selecciona una opcion: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }
    
    public static String desplegableMeses(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "M E S E S", "Selecciona un mes: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }
    
    public static String desplegableDias(String menu) {
        String[] valores = menu.split(",");
        return (String) JOptionPane.showInputDialog(null, "D I A S", "Seleccione un dia: ", JOptionPane.QUESTION_MESSAGE, null, valores, valores[0]);
    }    
    
    public static String menuEspecialidades(String menu) {
        String sel;
        boolean seleccionado = false;
        String[] puestosValidos = {
            "Biología Ambiental",
            "Ecología",
            "Ingeniería Ambiental",
            "Gestión de Recursos Naturales",
            "Cambio Climático",
            "Conservación de la Biodiversidad",
            "Geología Ambiental",
            "Política Ambiental",
            "Educación Ambiental",
            "Restauración Ecológica"
        };

        do {
            sel = desplegableEspecialidades(menu);
            if (esPuestoValido(sel, puestosValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona un puesto válido. Opciones válidas: " + String.join(", ", puestosValidos));
            }
        } while (!seleccionado);
        return sel;
    }
    
    public static String menuLineasAutobus(String menu) {
        String sel;
        boolean seleccionado = false;
        String[] puestosValidos = {
            "ADO",
            "Estrella de Oro",
            "Primera Plus",
            "Estrella Blanca",
            "Flecha Amarilla",
            "Metro Politano",
            "Ormeño",
            "Expreso Brasilia"
        };

        do {
            sel = desplegableLineas(menu);
            if (esPuestoValido(sel, puestosValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona una linea válida. Opciones válidas: " + String.join(", ", puestosValidos));
            }
        } while (!seleccionado);
        return sel;
    }
    
    public static String menuAreasInteres(String menu) {
        String sel;
        boolean seleccionado = false;
        String[] puestosValidos = {
            "Energías Renovables",
            "Gestión de Residuos",
            "Calidad del Aire y Agua",
            "Agricultura Sostenible",
            "Zonas Protegidas",
            "Especies en Peligro de Extinción",
            "Contaminación del Suelo",
            "Restauración de Ecosistemas",
            "Sostenibilidad Urbana",
            "Cambio Climático y Adaptación"
        };

        do {
            sel = desplegableAreasInteres(menu);
            if (esPuestoValido(sel, puestosValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona una Area de Interes válida. Opciones válidas:\n" + String.join(", ", puestosValidos));
            }
        } while (!seleccionado);
        return sel;
    }
    
    public static int menuMeses(String menu) {
        String sel;
        int res = 0;
        boolean seleccionado = false;

        String[] mesesValidos = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        do {
            sel = desplegableMeses(menu);
            
            if (esPuestoValido(sel, mesesValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona un mes válido. Opciones válidas:\n" + String.join(", ", mesesValidos));
            }
        } while (!seleccionado);

        switch (sel) {
            case "Enero": res = 1; break;
            case "Febrero": res = 2; break;
            case "Marzo": res = 3; break;
            case "Abril": res = 4; break;
            case "Mayo": res = 5; break;
            case "Junio": res = 6; break;
            case "Julio": res = 7; break;
            case "Agosto": res = 8; break;
            case "Septiembre": res = 9; break;
            case "Octubre": res = 10; break;
            case "Noviembre": res = 11; break;
            case "Diciembre": res = 12; break;
        }
        return res;
    }


    public static String menuDias(int mes) {
        String sel;
        boolean seleccionado = false;
        int diasMaximos;

        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                diasMaximos = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                diasMaximos = 30;
                break;
            case 2:
                diasMaximos = 28;
                break;
            default:
                diasMaximos = 0; 
                break;
        }

        String[] diasValidos = new String[diasMaximos];
        for (int i = 0; i < diasMaximos; i++) {
            diasValidos[i] = String.valueOf(i + 1);
        }

        do {
            sel = (String) JOptionPane.showInputDialog(null,
                "Seleccione un día (1-" + diasMaximos + "):",
                "Seleccione un día",
                JOptionPane.QUESTION_MESSAGE,
                null,
                diasValidos,
                diasValidos[0]);
            
            if (sel != null && esPuestoValido(sel, diasValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona un día válido. Opciones válidas: " + String.join(", ", diasValidos));
            }
        } while (!seleccionado);

        return sel;
    }



    private static boolean esPuestoValido(String seleccion, String[] puestosValidos) {
        for (String puesto : puestosValidos) {
            if (puesto.equals(seleccion)) {
                return true;
            }
        }
        return false;
    }

    public static String menuOrigenYDestino(String menu) {
        String sel;
        boolean seleccionado = false;
        String[] puestosValidos = {
            "Orizaba",
            "Cordoba",
            "Mendoza",
            "Veracruz",
            "Oaxaca",
            "Edo. Mex.",
            "Queretaro",
            "San Juan del Rio",
            "Chiapas",
            "Tijuana"
        };

        do {
            sel = desplegableLugares(menu);
            if (esPuestoValido(sel, puestosValidos)) {
                seleccionado = true;
            } else {
                imprimeError("Selecciona un Lugar válido. Opciones válidas:\n" + String.join(", ", puestosValidos));
            }
        } while (!seleccionado);
        return sel;
    }   
}