package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public EstrategiaBusquedaGrafo(){
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
        Queue<Nodo> frontera = new LinkedList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Estado estadoActual = nodoActual.getEstado();
        frontera.add(nodoActual);
        ArrayList<Nodo> nodos_explorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){
            nodoActual = frontera.remove();
            estadoActual = nodoActual.getEstado();
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (!p.esMeta(estadoActual)){
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                nodos_explorados.add(nodoActual);
                hijos = hijos(p, nodoActual);
                for (Nodo hijo: hijos) {
                   if (frontera.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado())) && nodos_explorados.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado()))) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                        frontera.add(hijo);
                    }
                    else
                        System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }
            }else{
                System.out.println((i) + " - FIN - " + estadoActual);
                ArrayList<Nodo> sol = recontruye_solucion(nodoActual);
                Collections.reverse(sol);
                return sol.toArray(new Nodo[0]);
            }
        }
        throw new Exception("No se ha podido encontrar una solución");
    }
}
