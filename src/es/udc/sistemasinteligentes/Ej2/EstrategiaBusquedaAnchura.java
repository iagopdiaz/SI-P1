package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {
    public EstrategiaBusquedaAnchura(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){
        ArrayList<Nodo> hijos = new ArrayList<>();
        Nodo nodoAux = new Nodo(nodo.getEstado(),nodo.getPadre(),nodo.getAccion());

        Accion[] disponibles = p.acciones(nodo.getEstado());
        for (Accion acc : disponibles){
            Estado sc = p.result(nodoAux.getEstado(), acc);
            System.out.println(sc);
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

        if (p.esMeta(p.getEstadoInicial())){
            return new Nodo[]{new Nodo(p.getEstadoInicial(),null,null)};
        }

        Queue<Nodo> frontera = new LinkedList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Estado estadoActual = nodoActual.getEstado();
        frontera.add(nodoActual);
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){
            nodoActual = frontera.remove();
            estadoActual = nodoActual.getEstado();
            nodosExplorados.add(nodoActual);
            hijos = hijos(p,nodoActual);

            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            for (Nodo hijo : hijos){
                if(!p.esMeta(estadoActual)){
                    System.out.println((i++) + " - " + estadoActual + " no es meta");
                    if (frontera.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado())) && nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado()))) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                        frontera.add(hijo);
                    }
                    else
                        System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }else{
                    System.out.println((i) + " - FIN - " + estadoActual);
                    ArrayList<Nodo> sol = recontruye_solucion(nodoActual);
                    Collections.reverse(sol);
                    return sol.toArray(new Nodo[0]);
                }
            }

        }
        throw new Exception("No se ha podido encontrar una solución");
    }
}
