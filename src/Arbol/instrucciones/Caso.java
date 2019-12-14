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
public class Caso extends Instruccion{
    public Expresion value;
    public Bloque bloque;

    public Caso(Expresion value, Bloque bloque) {
        this.value = value;
        this.bloque = bloque;
    }
    

    @Override
    public Object ejecutar(Entorno ent) {
        Expresion resultado=value.getValor(ent);
        boolean ejecutado=false;
        
        if(resultado!=null){
//            boolean caso=Boolean.parseBoolean(resultado.getValor(ent).toString());
     
            ejecutado=true;
         Object obj= bloque.ejecutar(ent);
            if(obj instanceof Break || obj instanceof Continue){
                return obj;
            }
        }    
        
            
       return ejecutado;
    }
    
}
