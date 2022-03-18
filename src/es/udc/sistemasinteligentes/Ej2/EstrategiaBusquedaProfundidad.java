package es.udc.sistemasinteligentes.Ej2;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaBusquedaProfundidad implements EstrategiaBusqueda {
    public EstrategiaBusquedaProfundidad(){
    }

    private ArrayList<Nodo> hijos(ProblemaBusqueda p, Nodo nodo){
        ArrayList<Nodo> hijos = new ArrayList<>();
                                            //Creamos una lista de sucesores del nodo dado aplicandole cada accion disponible del nodo padre
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

        ArrayList<Nodo> frontera = new ArrayList<>();       //Inicializamos la frontera como una cola, creamos el nodo y estado actual a partir del estado incial
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        Estado estadoActual = nodoActual.getEstado();       //Añadimos el nodo a la frontera e inicializamos explorados e hijos.
        frontera.add(nodoActual);
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();
        ArrayList<Nodo> hijos;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!frontera.isEmpty()){            //Mientras la frontera no está vacía, guardamos la primera posicion de la frontera en nodo actual
                                                //y se elimina esa posición de la frontera.
            nodoActual = frontera.get(0);
            frontera.remove(0);
            estadoActual = nodoActual.getEstado();      //Cogemos el estado del nodo actual.
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (p.esMeta(estadoActual)){        //Si el estado es meta salimos del while.
                break;
            }

            nodosExplorados.add(nodoActual);    //Si el nodo no es meta añadimos el nodo actual a explorados y guardamos en hijos sus posibles sucesores.
            hijos = hijos(p,nodoActual);
            System.out.println((i++) + " - " + estadoActual + " no es meta");

            for (Nodo hijo : hijos){        //Buscamos algun hijo que no esté ni en la frontera ni en explorados, si no está en ninguno se añade a la frontera.
                if (frontera.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString()))
                        && nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().toString().equals(hijo.getEstado().toString())) ) {
                    System.out.println((i++) + " - " + hijo.getEstado() + " NO explorado");
                    frontera.add(0,hijo);
                }else
                    System.out.println((i++) + " - " + hijo.getEstado() + " ya explorado");
                }
        }
        System.out.println((i) + " - FIN - " + estadoActual);
        ArrayList<Nodo> sol = reconstruye_sol(nodoActual);          //Devolvemos la lista de nodos solución
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
