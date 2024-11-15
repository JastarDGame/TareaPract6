package ed.ito;

import java.util.ArrayList;
import java.util.List;
import Santiago.ComparadorCorrida;
import Santiago.CorridaAutobus;

public class ArbolBinario<E> implements Arbol<E> {
    private NodoArbol<E> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    private NodoArbol<E> add(NodoArbol<E> r, E item) throws ExcepcionNodoRepetido {
        if (r == null) return new NodoArbol<>(item);

        @SuppressWarnings("unchecked")
        Comparable<E> c = (Comparable<E>) item;

        if (c.compareTo(r.getItem()) == 0)
            throw new ExcepcionNodoRepetido("El item ya se localiza en el árbol");
        
        if (c.compareTo(r.getItem()) < 0)
            r.setIzquierdo(add(r.getIzquierdo(), item));
        else
            r.setDerecho(add(r.getDerecho(), item));

        return r;
    }

    @Override
    public void add(E item) throws ExcepcionNodoRepetido {
        this.raiz = this.add(this.raiz, item);
    }

    private NodoArbol<E> delete(NodoArbol<E> r, E item) throws ExcepcionNodoNoLocalizado {
        @SuppressWarnings("unchecked")
        Comparable<E> c = (Comparable<E>) item;
        NodoArbol<E> retorno = r;

        if (r == null)
            throw new ExcepcionNodoNoLocalizado("Item no encontrado para eliminar!!");

        if (c.compareTo(r.getItem()) == 0) {
            if (r.getIzquierdo() == null && r.getDerecho() == null)
                retorno = null;
            else if (r.getIzquierdo() == null)
                retorno = r.getDerecho();
            else {
                retorno = r.getIzquierdo();
                if (r.getDerecho() != null) {
                    NodoArbol<E> aux = retorno;
                    while (aux.getDerecho() != null)
                        aux = aux.getDerecho();
                    aux.setDerecho(r.getDerecho());
                }
            }
        } else if (c.compareTo(r.getItem()) < 0)
            r.setIzquierdo(this.delete(r.getIzquierdo(), item));
        else
            r.setDerecho(this.delete(r.getDerecho(), item));

        return retorno;
    }

    @Override
    public void delete(E item) throws ExcepcionNodoNoLocalizado {
        if (this.isFree())
            throw new ExcepcionNodoNoLocalizado("El árbol está vacío");
        this.raiz = this.delete(this.raiz, item);
    }

    @Override
    public boolean isFree() {
        return this.raiz == null;
    }

    public List<CorridaAutobus> search(ComparadorCorrida criterio, CorridaAutobus comodin) {
        List<CorridaAutobus> resultados = new ArrayList<>();
        buscarRecursivo(raiz, criterio, resultados, comodin);
        return resultados;
    }

    private void buscarRecursivo(NodoArbol<E> nodo, ComparadorCorrida criterio, List<CorridaAutobus> resultados, CorridaAutobus comodin) {
        if (nodo != null) {
            if (criterio.compare(comodin, (CorridaAutobus) nodo.getItem()) == 0) {
                resultados.add((CorridaAutobus) nodo.getItem());
            }
            buscarRecursivo(nodo.getIzquierdo(), criterio, resultados, comodin);
            buscarRecursivo(nodo.getDerecho(), criterio, resultados, comodin);
        }
    }

    private int lengthHojas(NodoArbol<E> r) {
        if (r == null)
            return 0;
        if (r.getIzquierdo() == null && r.getDerecho() == null)
            return 1;
        return lengthHojas(r.getIzquierdo()) + lengthHojas(r.getDerecho());
    }

    @Override
    public int lengthHojas() {
        return lengthHojas(this.raiz);
    }

    private int getAltura(NodoArbol<E> r) {
        if (r == null)
            return 0;
        int izq = getAltura(r.getIzquierdo());
        int der = getAltura(r.getDerecho());
        return 1 + (izq > der ? izq : der);
    }

    @Override
    public int getAltura() {
        return getAltura(this.raiz);
    }
}

