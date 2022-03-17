package es.udc.sistemasinteligentes;
import java.lang.Comparable;

public class Nodo implements Comparable {
    private Estado estado;
    private Nodo padre;
    private Accion accion;
    private float coste;

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Nodo(Estado estado, Nodo padre, Accion accion,float coste) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.coste = coste;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "estado=" + getEstado() +
                ", padre=" + getPadre() +
                ", accion=" + getAccion() +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
