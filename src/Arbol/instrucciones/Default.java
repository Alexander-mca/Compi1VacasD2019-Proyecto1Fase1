/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Instruccion;

/**
 *
 * @author alexa
 */
public class Default extends Instruccion{
    public Bloque bloque;
    public Default(Bloque bloque){
        this.bloque=bloque;
    }

    @Override
    public Object ejecutar(Entorno ent) {     
            
     Object obj=   bloque.ejecutar(ent);
                 if(obj instanceof Break || obj instanceof Continue){
                return obj;
            }
       return true;
    }
    
}
