/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Instruccion;
import Arbol.MetodosyFunciones.Metodo;
import Arbol.Nodo;

import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Clase extends Instruccion{
public LinkedList<Nodo> instrucciones;
public String nombre;


    public Clase(LinkedList<Nodo> instrucciones, String nombre) {
        this.instrucciones = instrucciones;
        this.nombre = nombre;
    }
    @Override
    public Object ejecutar(Entorno ent) {
       nombre+="@";
      
       ent.insertar(nombre, new Simbolo(new Tipo(Tipo.EnumTipo.clase),instrucciones), linea, columna,"La Clase ");
       return null;
    }
    
}
