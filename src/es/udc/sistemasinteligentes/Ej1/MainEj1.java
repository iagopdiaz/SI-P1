package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import java.util.Arrays;

public class MainEj1 {
    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();

        System.out.println("Estrategia 4:");

        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(aspiradora)));

        System.out.println("Estrategia Busqueda Grafo :");
        buscador = new EstrategiaBusquedaGrafo();
        System.out.println("Estado Meta: "+ Arrays.toString(buscador.soluciona(aspiradora)));
    }
}
