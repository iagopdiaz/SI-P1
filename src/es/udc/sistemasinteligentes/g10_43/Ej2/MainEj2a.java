package es.udc.sistemasinteligentes.g10_43.Ej2;

import es.udc.sistemasinteligentes.g10_43.EstrategiaBusqueda;

import java.util.Arrays;

public class MainEj2a {
    public static void main(String[] args) throws Exception {
        int[][] cuadradoPrueba = { {4,9,2},{3,5,0},{0,1,0} };

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(cuadradoPrueba);

        ProblemaCuadradoMagico cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaAnchura();

        System.out.println("Estrategia Anchura:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado)));

        System.out.println("\n");

        buscador = new EstrategiaBusquedaProfundidad();

        System.out.println("Estrategia Profundidad:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado)));
    }
}