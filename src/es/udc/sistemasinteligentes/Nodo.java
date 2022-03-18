package es.udc.sistemasinteligentes;
import es.udc.sistemasinteligentes.Ej2.ProblemaCuadradoMagico;

import java.lang.Comparable;

public class Nodo implements Comparable {           //Nodo en el que se guardan los valores del estado, padre y acción.
    private Estado estado;                          //A mayores para el ej2B para calcular la heurística guarda tambien el coste
    private Nodo padre;                             //por eso hay dos constructores Nodo definidos
    private Accion accion;
    private float coste;

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Nodo(Estado estado, Nodo padre, Accion accion, float coste) {
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

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        String aux = "Nodo{" + "estado=" + getEstado() + ", accion=" + getAccion();

        if (getCoste() != 0)
            aux = aux + ", coste=" + getCoste();

        aux = aux + '}';

        return aux;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static class Heuristica extends es.udc.sistemasinteligentes.Heuristica {
        public Heuristica() {
        }

        @Override
        public float evalua(Estado e){
            ProblemaCuadradoMagico.EstadoCuadradoMagico esCu = (ProblemaCuadradoMagico.EstadoCuadradoMagico)e;
            int[][] cuadrado = esCu.getCuadrado();
            float heuristica = 0;

            for (int i = 0; i < cuadrado.length; i++){
                for (int j = 0; j < cuadrado.length; j++){
                    heuristica++;
                    if (cuadrado[i][j] == 0)
                        return heuristica;             //La heuristica está definida como el coste de recorrer las posiciones (posicion 0 0 coste 1, pos 0 1 coste 2)
                }
            }
            return 0;
        }
    }
}
