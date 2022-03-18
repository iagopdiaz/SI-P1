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

        Accion[] disponibles = p.acciones(nodo.getEstado());        //Creamos una lista de sucesores del nodo dado aplicandole cada accion disponible del nodo padre
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

        if (p.esMeta(p.getEstadoInicial())){                                            //Si el estado inicial es meta devuelve el nodo inicial.
            return new Nodo[]{new Nodo(p.getEstadoInicial(),null,null)};
        }

        Queue<Nodo> frontera = new LinkedList<>();                                  //Inicializamos la frontera, los nodos explorados, la lista de hijos
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);   //y creamos un nodo y estado actual a partir del estado inicial
        Estado estadoActual = nodoActual.getEstado();
        frontera.add(nodoActual);
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){            //Mientras la frontera no está vacía quitamos el nodo actual de la frontera, actualizamos el estado
            nodoActual = frontera.remove();     // ,añadimos el nodo actual a nodos explorados y guardamos los hijos del nodo actual
            estadoActual = nodoActual.getEstado();
            nodosExplorados.add(nodoActual);
            hijos = hijos(p,nodoActual);

            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
            for (Nodo hijo : hijos){                //Recorremos la lista de hijos del estado actual viendo si alguno de ellos es meta.
                if(!p.esMeta(hijo.getEstado())){
                    System.out.println((i++) + " - " + estadoActual + " no es meta");

                    if (frontera.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))
                            && nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString())) ) {
                        System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                        frontera.add(hijo);         //Si el hijo no está en la frontera ni está explorado , se añade a la frontera
                    }
                    else
                        System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }else{
                    System.out.println((i) + " - FIN - " + hijo.getEstado());
                    ArrayList<Nodo> sol = reconstruye_sol(hijo);
                    Collections.reverse(sol);
                    return sol.toArray(new Nodo[0]);        //Si un hijo es meta se devuelve la lista de nodos solución.
                }
            }

        }
        throw new Exception("No se ha podido encontrar una solución"); //Si la frontera está vacía, y no se encontró el estado meta, no hay solución
    }
}
