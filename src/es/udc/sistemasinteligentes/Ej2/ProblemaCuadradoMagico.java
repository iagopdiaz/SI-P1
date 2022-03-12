package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public ProblemaCuadradoMagico(ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
    }

    public static class EstadoCuadradoMagico extends Estado {

        private int[][] Cuadrado;
        private int[] NumerosUsados = {};
        private int ValorCuadrado;


        public EstadoCuadradoMagico(int [][] cuadrado) {
            int n = cuadrado.length;
            int x = 0;

            for(int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (cuadrado[i][j] != 0){
                        this.NumerosUsados[x] = cuadrado[i][j];
                        x++;
                    }
                }
            }

            this.ValorCuadrado = n*(n^2-1)/2;
            this.Cuadrado = cuadrado;
        }

        public void setNumerosUsados(int[] numerosUsados) {
            this.NumerosUsados = numerosUsados;
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
        private int PonerNumero;

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
            EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;
            int[][] nuevoCuadrado = esCu.Cuadrado;
            int i;

            for (i = 0; i < esCu.NumerosUsados.length; i++){           //Comprobamos que el número a insertar no está usado.
                if (PonerNumero == esCu.NumerosUsados[i])
                    return new EstadoCuadradoMagico(nuevoCuadrado);
            }

            for (i = 0;i < nuevoCuadrado.length; i++){                 //Recorremos el cuadrado hasta encontrar una posición vacía
                for (int j = 0;j < nuevoCuadrado.length; j++){         //para insertar el numero dado.
                   if(nuevoCuadrado[i][j] == 0){
                       nuevoCuadrado[i][j] = PonerNumero;
                       return new EstadoCuadradoMagico(nuevoCuadrado);
                   }
                }
            }

            return new EstadoCuadradoMagico(nuevoCuadrado);
        }
    }

    private Accion[] listaAcciones;

    public Accion[] acciones(Estado es){
        //No es necesario generar las acciones dinámicamente a partir del estado porque todas las acciones se pueden
        //aplicar a todos los estados
        return listaAcciones;
    }


    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;
        int numeroF, fila = 0, numeroC, columna = 0,
            numeroD1, diagonal1 = 0, numeroD2, diagonal2 = 0, magico = esCu.ValorCuadrado;

        for (int i = 0;i < esCu.Cuadrado.length; i++){
            for (int j = 0;j < esCu.Cuadrado.length; j++){
                numeroF = esCu.Cuadrado[i][j];
                numeroC = esCu.Cuadrado[j][i];
                fila = numeroF + fila;
                columna = numeroC + columna;

                if (i == j){
                    numeroD1 = esCu.Cuadrado[i][j];
                    diagonal1 = numeroD1 + diagonal1;
                }

                if ( (i + j) == (esCu.Cuadrado.length - 1)){
                    numeroD2 = esCu.Cuadrado[i][j];
                    diagonal2 = numeroD2 + diagonal2;
                }
            }
        }

        return (fila == magico && columna == magico && diagonal1 == magico && diagonal2 == magico);
    }
}
