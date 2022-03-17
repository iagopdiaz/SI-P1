package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;

import java.util.Arrays;

public class MainEj2B {
    public static void main(String[] args) throws Exception {
        int[][] cuadradoPrueba = { {4,9,2},{3,5,0},{0,1,0} };

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(cuadradoPrueba);

        ProblemaCuadradoMagico cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        es.udc.sistemasinteligentes.EstrategiaBusquedaInformada buscador = new EstrategiaBusquedaInformada();

        Nodo.Heuristica heuristica = new  Nodo.Heuristica();

        System.out.println("Estrategia Profundidad:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado,heuristica)));
    }
}
