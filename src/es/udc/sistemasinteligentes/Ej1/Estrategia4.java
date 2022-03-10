package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    private ArrayList<Nodo> construye_nodo(Nodo nodo){
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
        ArrayList<Nodo> nodos_explorados = new ArrayList<>();
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual,null,null);
        nodos_explorados.add(nodoActual);

        int i = 1;


        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            nodoActual = nodos_explorados.get(nodos_explorados.size() - 1);
            estadoActual = nodoActual.getEstado();
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (nodos_explorados.stream().noneMatch(nodo -> nodo.getEstado().equals(sc))) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    Nodo nodo_aux = new Nodo(estadoActual,nodoActual,acc);
                    nodos_explorados.add(nodo_aux);
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
        ArrayList<Nodo> sol = construye_nodo(nodoActual);
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
