package es.udc.sistemasinteligentes.g10_43.Ej2;

import es.udc.sistemasinteligentes.g10_43.*;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaBusquedaInformada implements es.udc.sistemasinteligentes.g10_43.EstrategiaBusquedaInformada {
    public EstrategiaBusquedaInformada(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){
        ArrayList<Nodo> hijos = new ArrayList<>();              //
        Nodo.Heuristica heuristica = new Nodo.Heuristica();

        Accion[] disponibles = p.acciones(nodo.getEstado());
        for (Accion acc : disponibles){
            Estado sc = p.result(nodo.getEstado(), acc);
            hijos.add(new Nodo(sc, nodo, acc, (nodo.getCoste() + heuristica.evalua(nodo.getEstado()))));
        }
        return hijos;
    }

    private ArrayList<Nodo> reconstruye_sol(Nodo nodo){
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

        while (!frontera.isEmpty()){            //Mientras la frontera no está vacía guarda en nodo actual la primera posición de la frontera
                                                // ,elimina esa posición de la frontera y guarda el estado del nodo actual.
            nodoActual = frontera.get(0);
            frontera.remove(0);
            estadoActual = nodoActual.getEstado();
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (p.esMeta(estadoActual)){        //Si el estado es meta salimos del while
                break;
            }

            nodosExplorados.add(nodoActual);        //Si no es meta añadimos el nodo a explorados y calculamos sus sucesores
            hijos = hijos(p,nodoActual);
            System.out.println((i++) + " - " + estadoActual + " no es meta");

            for (Nodo hijo : hijos){        //Miramos para todos los sucesores si no está el estado del sucesor explorado
                if (nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))) {
                    if (frontera.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                        frontera.add(0,hijo);   //Si no está ni explorado ni en la frontera se añade a la frontera
                    }else{                            //Si no está explorado pero si en la frontera se mira si el coste del sucesor es meno al de la primera posición de la frontera
                        if (frontera.get(0).getCoste() > hijo.getCoste()){
                            frontera.remove(0);         //Si el coste es menor se quita la primera posición de la frontera y se añade el sucesor
                            frontera.add(0,hijo);
                        }
                    }
                }else
                    System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
            }
        }
        System.out.println((i) + " - FIN - " + estadoActual);                       //Devolvemos el camino de nodos para la solución y su heuristica
        System.out.println((i++) + " - Heuristica - " + nodoActual.getCoste());
        ArrayList<Nodo> sol = reconstruye_sol(nodoActual);
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
