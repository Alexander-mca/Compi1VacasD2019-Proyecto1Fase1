/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.MetodosyFunciones;

import Arbol.Entorno.Entorno;
import Arbol.Nodo;
import Arbol.instrucciones.Bloque;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class MDatos {
   public Entorno entorno;
   public LinkedList<Nodo> parametros;
   public Bloque instrucciones;

    public MDatos(Entorno entorno, LinkedList<Nodo> parametros, Bloque instrucciones) {
        this.entorno = entorno;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    public MDatos(Entorno entorno, Bloque instrucciones) {
        this.entorno = entorno;
        this.instrucciones = instrucciones;
    }
    
    
}
