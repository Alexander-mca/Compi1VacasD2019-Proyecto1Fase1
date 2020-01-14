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
        boolean ejecutado=false;
        
            Entorno actual=new Entorno(ent);
              
        Expresion resultado=value.getValor(actual);
        
        
        if(resultado!=null){
//            boolean caso=Boolean.parseBoolean(resultado.getValor(ent).toString());
     
            ejecutado=true;
            if(bloque!=null){
         Object obj= bloque.ejecutar(actual);
            if(obj!=null){
                return obj;
            }
            }
        }  
        
        
       return ejecutado;
    }
    
}
