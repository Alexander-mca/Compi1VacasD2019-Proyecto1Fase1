/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.MetodosyFunciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Nodo;
import Arbol.instrucciones.Bloque;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class DataF {
     public Entorno entorno;
     public String tipoF;

        LinkedList<String> profundidad;
    public LinkedList<Nodo> parametros;
    public Bloque instrucciones;
//    public Tipo tipo;
     public DataF(Entorno entorno, String tipoF, LinkedList<String> profundidad, LinkedList<Nodo> parametros, Bloque instrucciones) {
        this.entorno = entorno;
        this.tipoF = tipoF;
        this.profundidad = profundidad;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

   
    

   

    
}
