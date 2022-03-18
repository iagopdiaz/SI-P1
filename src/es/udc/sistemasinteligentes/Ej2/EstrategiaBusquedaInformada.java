package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaInformada implements es.udc.sistemasinteligentes.EstrategiaBusquedaInformada {
    public EstrategiaBusquedaInformada(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){
        ArrayList<Nodo> hijos = new ArrayList<>();
        Nodo.Heuristica heuristica = new Nodo.Heuristica();

        Accion[] disponibles = p.acciones(nodo.getEstado());
        for (Accion acc : disponibles){
            Estado sc = p.result(nodo.getEstado(), acc);
            hijos.add(new Nodo(sc, nodo, acc, (nodo.getCoste() + heuristica.evalua(nodo.getEstado()))));
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
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica heuristica) throws Exception{

        ArrayList<Nodo> frontera = new ArrayList<>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null, 0);
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
                if (nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))) {
                    if (frontera.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                        frontera.add(0,hijo);
                    }else{
                        if (frontera.get(0).getCoste() > hijo.getCoste()){
                            frontera.remove(0);
                            frontera.add(0,hijo);
                        }
                    }
                }else
                    System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
            }
        }
        System.out.println((i) + " - FIN - " + estadoActual);
        System.out.println((i++) + " - Heuristica - " + nodoActual.getCoste());
        ArrayList<Nodo> sol = recontruye_solucion(nodoActual);
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
