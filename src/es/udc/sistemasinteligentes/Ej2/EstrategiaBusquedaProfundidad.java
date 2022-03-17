package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaProfundidad implements EstrategiaBusqueda {
    public EstrategiaBusquedaProfundidad(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){
        ArrayList<Nodo> hijos = new ArrayList<>();

        Accion[] disponibles = p.acciones(nodo.getEstado());
        for (Accion acc : disponibles){
            Estado sc = p.result(nodo.getEstado(), acc);
            hijos.add(new Nodo(sc, nodo, acc));
        }
        return hijos;
    }

    private ArrayList<Nodo> recontruye_solucion(Nodo nodo){
        ArrayList<Nodo> sol = new ArrayList<>();
        Nodo actual = nodo;

        while (actual != null){
            sol.add(actual);
            actual = actual.getPadre();
        }
        return sol;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{

        ArrayList<Nodo> frontera = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Estado estadoActual = nodoActual.getEstado();
        frontera.add(nodoActual);
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){

            nodoActual = frontera.get(0);
            frontera.remove(0);
            estadoActual = nodoActual.getEstado();
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (p.esMeta(estadoActual)){
                break;
            }

            nodosExplorados.add(nodoActual);
            hijos = hijos(p,nodoActual);
            System.out.println((i++) + " - " + estadoActual + " no es meta");

            for (Nodo hijo : hijos){
                if (frontera.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))
                        && nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString())) ) {
                    System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                    frontera.add(0,hijo);
                }else
                    System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }
        }
        System.out.println((i) + " - FIN - " + estadoActual);
        ArrayList<Nodo> sol = recontruye_solucion(nodoActual);
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
