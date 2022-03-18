package es.udc.sistemasinteligentes.g10_43;
import es.udc.sistemasinteligentes.g10_43.Ej2.ProblemaCuadradoMagico;

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
            aux = aux + ", coste=" + getCoste();             //Como se reutiliza la clase Nodo para varios ejercicios solo devuelve el coste si no es 0
                                                             //No mostramos por pantalla el padre para mayor claridad a la hora de ver el estado meta
        aux = aux + '}';

        return aux;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static class Heuristica extends es.udc.sistemasinteligentes.g10_43.Heuristica {
        public Heuristica() {                       //Clase del Ej2B para poder calcular la heurística de un estado dado
        }

        @Override
        public float evalua(Estado e){
            ProblemaCuadradoMagico.EstadoCuadradoMagico esCu = (ProblemaCuadradoMagico.EstadoCuadradoMagico)e;
            int[][] cuadrado = esCu.getCuadrado();
            float heuristica = 0;

            for (int i = 0; i < cuadrado.length; i++){     //Recorremos la matriz hasta encontrar un 0 donde devolveremos el valor de la Heuristica como está definida.
                for (int j = 0; j < cuadrado.length; j++){
                    heuristica++;
                    if (cuadrado[i][j] == 0)
                        return heuristica;             //La heuristica está definida siendo la posición del numero su coste
                }                                      // (posicion 0 0 coste 1, pos 0 1 coste 2)
            }
            return 0;
        }
    }
}
