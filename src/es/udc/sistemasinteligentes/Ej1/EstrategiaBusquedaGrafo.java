package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {
    public EstrategiaBusquedaGrafo(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){       //Para el estado dado  devolvemos una lista de nodos hijo que son el
        ArrayList<Nodo> hijos = new ArrayList<>();                      //resultado de aplicar todas las acciones posibles del estado actual

        Accion[] disponibles = p.acciones(nodo.getEstado());
        for (Accion acc : disponibles){
            Estado sc = p.result(nodo.getEstado(), acc);
            hijos.add(new Nodo(sc, nodo, acc));
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
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        Queue<Nodo> frontera = new LinkedList<>();                                   //Inicializamos la frontera, el nodo inicial, el estado inicial
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);    //del problema, añadimos el nodo inicial a la frontera
        Estado estadoActual = nodoActual.getEstado();                                 //e inicializamos nodos explorados e hijos
        frontera.add(nodoActual);
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){
            nodoActual = frontera.remove();                                 //Si la frontera no está vacía quitamos la cabeza de la cola
            estadoActual = nodoActual.getEstado();                          //cambiamos el estado actual al del nodo actual
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (!p.esMeta(estadoActual)){                                   //Si el estado actual no es meta añadimos el nodo con ese estado a explorados
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                nodosExplorados.add(nodoActual);
                hijos = hijos(p, nodoActual);               //Calculamos todos los estados hijo posibles del nodo actual
                for (Nodo hijo: hijos) {
                   if (frontera.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado())) && nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().equals(hijo.getEstado()))) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado"); //Si el estado no existe ni en la frontera ni en explorados
                        frontera.add(hijo);                                                     //Lo añadimos a la frontera
                    }
                    else
                        System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }
            }else{
                System.out.println((i) + " - FIN - " + estadoActual);
                ArrayList<Nodo> sol = reconstruye_sol(nodoActual);      //Devolvemos el array de nodos solución
                Collections.reverse(sol);
                return sol.toArray(new Nodo[0]);
            }
        }
        throw new Exception("No se ha podido encontrar una solución ");     //Si la frontera está vacía y no se devolvió una solución
    }
}
