package Santiago;

import java.util.Comparator;

public class ComparadorCorrida implements Comparator<CorridaAutobus> {
    @Override
    public int compare(CorridaAutobus c1, CorridaAutobus c2) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Los objetos CorridaAutobus no pueden ser nulos");
        }

        int comparacionFecha = c1.getFecha().compareTo(c2.getFecha());
        if (comparacionFecha != 0) {
            return comparacionFecha;
        }

        int comparacionOrigen = c1.getOrigen().compareToIgnoreCase(c2.getOrigen());
        if (comparacionOrigen != 0) {
            return comparacionOrigen;
        }

        return c1.getDestino().compareToIgnoreCase(c2.getDestino());
    }
}
