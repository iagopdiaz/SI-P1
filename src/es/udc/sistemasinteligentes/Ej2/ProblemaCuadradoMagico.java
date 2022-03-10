package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Ej1.ProblemaAspiradora;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadradoMagico extends Estado {

        public int[][] Cuadrado;
        public int ValorCuadrado;

        public EstadoCuadradoMagico(int [][] cuadro) {
            int n = cuadro.length;

            this.ValorCuadrado = n*(n^2-1)/2;
            this.Cuadrado = cuadro;
        }

        public int[][] getCuadrado() {
            return Cuadrado;
        }

        public int getValorCuadrado() {
            return ValorCuadrado;
        }

        @Override
        public String toString() {
            StringBuilder aux = new StringBuilder();
            aux.append("[");

            for (int i = 0;i < Cuadrado.length; i++){
                aux.append("(");
                for (int j = 0;j < Cuadrado.length; j++){
                    aux.append(Cuadrado[i][j]).append(",");
                }
                aux.append(")");
            }
            aux.append("]");
            return aux.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return o != null && getClass() == o.getClass();
        }

        @Override
        public int hashCode() {
            int result = ValorCuadrado;
            return (31 * result + 7);
        }
    }

    public static class AccionCuadradoMagico extends Accion {
        public int PonerNumero;

        public AccionCuadradoMagico(int i) {
            this.PonerNumero = i;
        }

        @Override
        public String toString() {
            return String.valueOf(PonerNumero);
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            int[][] aux = es.getCuadrado();


        }
    }

    //Como toda las acciones se pueden aplicar en cualquier estado y son pocas,
    //podemos mantenerlas en un array para cuando nos las pidan con el método acciones.
    private Accion[] listaAcciones;


}
