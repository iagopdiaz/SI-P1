package es.udc.sistemasinteligentes.Ej1;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    private ArrayList<Nodo> reconstruye_sol(Nodo nodo){
        ArrayList<Nodo> sol = new ArrayList<>();
        Nodo actual = nodo;

        while (actual != null){                      //Devolvemos el camino de nodos solucion como un array de nodos.
            sol.add(actual);                         //Más adelante en el código se le da la vuelta a este array para mayor claridad.
            actual = actual.getPadre();
        }
        return sol;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();               //Creamos un array de nodosExplorados, inicializamos el estado del problema,
        Estado estadoActual = p.getEstadoInicial();                        //inicializamos el nodo padre y lo añadimos a nodos explorados
        Nodo nodoActual = new Nodo(estadoActual,null,null);
        nodosExplorados.add(nodoActual);

        int i = 1;


        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){                                            //Mientras el estado actual no es meta se repite el bucle
            nodoActual = nodosExplorados.get(nodosExplorados.size() - 1);
            estadoActual = nodoActual.getEstado();                                  //Cambiamos en cada iteración el nodo y estado actual
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);                //Guardamos las acciones disponibles para el estado actual
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {                                 //Iteramos probando todas las acciones posibles para el estado actual
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (nodosExplorados.stream().noneMatch(nodo -> nodo.getEstado().equals(sc))) {  //Si no existe un nodo en nodos explorados con estado acutal
                    estadoActual = sc;                                                          //creamos un nuevo nodo con ese estado y lo añadimos a explorados
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    Nodo nodo_aux = new Nodo(estadoActual,nodoActual,acc);
                    nodosExplorados.add(nodo_aux);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");           //Si existe un nodo en nodos explorados con estado acutal
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución"); //Si no se añadió ningun nodo a explorados
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        ArrayList<Nodo> sol = reconstruye_sol(nodoActual);           //Reconstruimos el array de nodos solucion y le damos la vuelta a las posiciones
        Collections.reverse(sol);
        return sol.toArray(new Nodo[0]);
    }
}
