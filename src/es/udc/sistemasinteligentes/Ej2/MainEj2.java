package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.Ej1.Estrategia4;
import es.udc.sistemasinteligentes.Ej1.EstrategiaBusquedaGrafo;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import java.util.Arrays;

public class MainEj2 {
    public static void main(String[] args) throws Exception {
        int[][] cuadradoPrueba = { {4,9,2},{3,5,0},{0,1,0} };

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(cuadradoPrueba);

        ProblemaCuadradoMagico cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaAnchura();

        System.out.println("Estrategia Anchura:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado)));

        buscador = new EstrategiaBusquedaProfundidad();

        System.out.println("Estrategia Profundidad:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(cuadrado)));
    }
}