package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class EstadoCuadradoMagico extends Estado {

        private int[][] Cuadrado;               //Guardamos en el estado del problema la matriz del Cuadrado y el valor que tienen que sumar filas,columnas y diagonales.
        private double ValorCuadrado;


        public EstadoCuadradoMagico(int [][] cuadrado) {
            int n = cuadrado.length;

            this.ValorCuadrado = n * (Math.pow(n,2) + 1) / 2;  //Formula para calcular el valor de la suma de filas, columnas y diagonales
            this.Cuadrado = cuadrado;
        }

        public int[][] getCuadrado() {
            return Cuadrado;
        }

        public void setCuadrado(int[][] cuadrado) {
            Cuadrado = cuadrado;
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
        private int PonerNumero;                    //Guardamos la acción, en este caso poner un número, que se quiere aplicar al problema

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
        public Estado aplicaA(Estado es) {                          //Recorremos la matriz del estado dado para insertar la acción dada
            EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;   //en la primera posición vacía.
            int[][] nuevoCuadrado = new int[esCu.Cuadrado.length][esCu.Cuadrado.length];
            int i,j;

            for (i = 0; i < nuevoCuadrado.length; i++) {
                for (j = 0; j < nuevoCuadrado.length; j++) {            //Creamos una copia del cuadrado del estado dado para no pasar el valor
                    nuevoCuadrado[i][j] = esCu.Cuadrado[i][j];          //por referencia.
                }
            }

            for (i = 0;i < nuevoCuadrado.length; i++){                 //Recorremos el cuadrado hasta encontrar una posición vacía
                for ( j = 0;j < nuevoCuadrado.length; j++){            //para insertar el numero dado.
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
        int i,j;
        int total = (int) Math.pow(estado.Cuadrado.length,2);

        for (i = 0; i < estado.Cuadrado.length; i++) {            //Guardamos los numeros ya usados del estado dado.
            for (j = 0; j < estado.Cuadrado.length; j++) {
                if (estado.Cuadrado[i][j] != 0){
                    numerosUsados.add(estado.Cuadrado[i][j]);
                }
            }
        }

        for (i = 0; i < total; i++) {                      //Recorremos el array hasta el total de posiciones
            if (!numerosUsados.contains(i+1))              //Si encontramos que el número no está usado en el estado dado añade el numero a la lista de acciones posibles.
                numerosDisponibles.add(i+1);
        }

        return numerosDisponibles;
    }

    public ProblemaCuadradoMagico(ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        ArrayList<Integer> numerosDisponibles;
        ArrayList<AccionCuadradoMagico> acciones = new ArrayList<>();

        numerosDisponibles = CalcularNumerosDisponibles(estadoInicial);     //Creamos el problema con el estado dado y generamos las acciones disponibles para el.

        for (Integer numerosDisponible : numerosDisponibles) {
            acciones.add(new AccionCuadradoMagico(numerosDisponible));
        }
        listaAcciones = acciones.toArray(new Accion[0]);
    }

    public Accion[] acciones(Estado es){                        //Calcula la lista de aciones posibles de un estado dado.
        EstadoCuadradoMagico esCu = (EstadoCuadradoMagico)es;
        ArrayList<Integer> numerosDisponibles;
        ArrayList<AccionCuadradoMagico> acciones = new ArrayList<>();

        numerosDisponibles = CalcularNumerosDisponibles(esCu);

        for (Integer numerosDisponible : numerosDisponibles) {
            acciones.add(new AccionCuadradoMagico(numerosDisponible));
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