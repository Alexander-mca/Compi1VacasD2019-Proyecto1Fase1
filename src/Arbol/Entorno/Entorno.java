/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Entorno;

import java.util.HashMap;

/**
 *
 * @author alexa
 */
public class Entorno {

    public Entorno anterior;
    public HashMap<String, Simbolo> tabla;

    public Entorno(Entorno anterior) {
        this.anterior = anterior;
        this.tabla = new HashMap<>();
    }

    public void insertar(String nombre, Simbolo sim, int linea, int columna, String cadenaerror) {
        if (tabla.containsKey(nombre)) {
            System.out.println("Error Semantico:" + cadenaerror + " '" + nombre + "'ya existe");
            return;
        }
        tabla.put(nombre, sim);
    }

    public Simbolo buscar(String nombre, int linea, int columna, String cadenaerror) {
        Entorno e = this;
        while (e != null) {
            Simbolo sim = e.tabla.get(nombre);
            if (sim != null) {
                return sim;
            } else {
                e = e.anterior;

            }

        }
//        for(Entorno e=this; e!=null;e=e.anterior){
//            Simbolo sim=e.tabla.get(nombre);
//            return sim;
//        }
        System.out.println("Error Semántico" + cadenaerror + " '" + nombre + "' No existe");
        return null;
    }
}
