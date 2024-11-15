package Santiago;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);  // Método que compara dos objetos
}

//El método compare compara dos objetos del mismo tipo.
//Retorna:
//0: Si ambos objetos son iguales.
//Menor que 0: Si el primer objeto es menor que el segundo.
//Mayor que 0: Si el primer objeto es mayor que el segundo.