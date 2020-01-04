/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.MetodosyFunciones.Funcion;
import Arbol.MetodosyFunciones.Llamada;
import Arbol.MetodosyFunciones.Metodo;
import Arbol.instrucciones.Clase;
import Arbol.instrucciones.Declaracion;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Objeto extends Expresion{
public Entorno entorno;
public String tipoObj;
    public Objeto(Entorno entorno,String tipoObj) {
      this.entorno=entorno;    
      this.tipoObj=tipoObj;
    }
    
 
    @Override
    public Expresion getValor(Entorno ent) {
                        
        return null;
    }
  
    
}
