package es.udc.sistemasinteligentes.g10_43.Ej1;

import es.udc.sistemasinteligentes.g10_43.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g10_43.ProblemaBusqueda;

import java.util.Arrays;

public class MainEj1 {
    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS); //Inicializamos el estado inicial del problema
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();

        System.out.println("Estrategia 4:");            //Devolvemos el array de nodos solución al aplicar la estrategia4

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(aspiradora)));

        System.out.println("\n");

        System.out.println("Estrategia Busqueda Grafo :");          //Devolvemos el array de nodos solución al aplicar Estrategia busqueda grafo
        buscador = new EstrategiaBusquedaGrafo();
        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(aspiradora)));
    }
}
