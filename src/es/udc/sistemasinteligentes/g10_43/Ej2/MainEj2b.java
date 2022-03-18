package es.udc.sistemasinteligentes.g10_43.Ej2;

import es.udc.sistemasinteligentes.g10_43.Nodo;

import java.util.Arrays;

public class MainEj2b {
    public static void main(String[] args) throws Exception {
        int[][] cuadradoPrueba = { {4,9,2},{3,5,0},{0,1,0} };

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(cuadradoPrueba);

        ProblemaCuadradoMagico cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        es.udc.sistemasinteligentes.g10_43.EstrategiaBusquedaInformada buscador = new EstrategiaBusquedaInformada();

        Nodo.Heuristica heuristica = new  Nodo.Heuristica();

        System.out.println("Estrategia Profundidad:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado,heuristica)));
    }
}
