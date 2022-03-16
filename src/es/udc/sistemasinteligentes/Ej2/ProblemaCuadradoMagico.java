package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado {

        private int[][] Cuadrado;
        private double ValorCuadrado;


        public EstadoCuadradoMagico(int [][] cuadrado) {
            int n = cuadrado.length;

            this.ValorCuadrado = n * (Math.pow(n,2) + 1) / 2;
            this.Cuadrado = cuadrado;
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
            double result = ValorCuadrado;

            return (31 * (int)result + 7);
        }
    }

    public static class AccionCuadradoMagico extends Accion {
        private int PonerNumero;

        public AccionCuadradoMagico(int numerosDisponibles) {
            this.PonerNumero = numerosDisponibles;
        }

        @Override
        public String toString() {
            return Integer.toString(PonerNumero);
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

            for (i = 0;i < nuevoCuadrado.length; i++){
                for (int j = 0;j < nuevoCuadrado.length; j++){
                    nuevoCuadrado[i][j] = esCu.Cuadrado[i][j];
                }
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

    public ArrayList<Integer> CalcularNumerosDisponibles(EstadoCuadradoMagico estado){
        ArrayList<Integer> numerosDisponibles = new ArrayList<>();
        ArrayList<Integer> numerosUsados = new ArrayList<>();
        int x,i,j;
        int total = (int) Math.pow(estado.Cuadrado.length,2);

        for (i = 0; i < estado.Cuadrado.length; i++) {            //Guardamos los numeros ya usados en el estadoInicial.
            for (j = 0; j < estado.Cuadrado.length; j++) {
                if (estado.Cuadrado[i][j] != 0){
                    numerosUsados.add(estado.Cuadrado[i][j]);
                }
            }
        }

        for (i = 0; i < total; i++) {                      //Llenamos el array con todos los números posibles en el cuadrado.
            if (!numerosUsados.contains(i+1))
                numerosDisponibles.add(i+1);
        }

        return numerosDisponibles;
    }

    public ProblemaCuadradoMagico(ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        ArrayList<Integer> numerosDisponibles;
        ArrayList<AccionCuadradoMagico> acciones = new ArrayList<>();
        int x = 6;

        numerosDisponibles = CalcularNumerosDisponibles(estadoInicial);

        for (int i = 0; i < numerosDisponibles.size(); i++){
            acciones.add(new AccionCuadradoMagico(numerosDisponibles.get(i)));
        }
        listaAcciones = acciones.toArray(new Accion[0]);
    }

    public Accion[] acciones(Estado es){
        EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;
        ArrayList<Integer> numerosDisponibles;
        ArrayList<AccionCuadradoMagico> acciones = new ArrayList<>();
        int x=6;

        numerosDisponibles = CalcularNumerosDisponibles(esCu);

        for (int i = 0; i < numerosDisponibles.size(); i++){
            acciones.add(new AccionCuadradoMagico(numerosDisponibles.get(i)));
        }

        listaAcciones = acciones.toArray(new Accion[0]);

        return listaAcciones;
    }


    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;
        double numeroF, fila = 0, numeroC, columna = 0,
                numeroD1, diagonal1 = 0, numeroD2, diagonal2 = 0, magico = esCu.ValorCuadrado;


        for (int i = 0;i < esCu.Cuadrado.length; i++){
            fila = 0;
            columna = 0;

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
            if (fila != magico || columna != magico)
                return false;
        }
        return (fila == magico && columna == magico && diagonal1 == magico && diagonal2 == magico);
    }
}