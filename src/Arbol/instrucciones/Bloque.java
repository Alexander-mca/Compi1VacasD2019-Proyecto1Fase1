/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.MetodosyFunciones.Return;
import Arbol.Nodo;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Bloque extends Instruccion{
    LinkedList<Nodo> intruccion;
   public Bloque(LinkedList<Nodo> lista){
      this.intruccion=lista;
//      this.linea=linea;
//      this.columna=columna;
      
   }
    @Override
    public Object ejecutar(Entorno ent) {
        
        for(Nodo instruccion:intruccion){
           if(instruccion instanceof Expresion){
           ((Expresion)instruccion).getValor(ent);
            
        }else if(instruccion instanceof Return){
            Object obj=((Instruccion)instruccion).ejecutar(ent);
            return obj;
        }else{
             Object obj= ((Instruccion)instruccion).ejecutar(ent);
           if(obj instanceof Break || obj instanceof Continue ){
                return obj;
            }
        }
        }
        return null;
    }
    
    
}
