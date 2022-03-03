package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class Estrategia4 {

    public Estrategia4() {
    }

    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> nodos = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoInicial = new Nodo(estadoActual,null,null);
        nodos.add(nodoInicial);

        int i = 1;
        int posPadre = 0;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                posPadre = nodos.size() - 1;
                Nodo nodoPadre = nodos.get(posPadre);
                Nodo nodoActual = new Nodo(sc,nodoPadre,acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!nodos.contains(nodoActual)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    nodos.add(nodoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return nodos;
    }
}
