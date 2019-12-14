/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Expresion;
import Arbol.Instruccion;

/**
 *
 * @author alexa
 */
public class Asignacion extends Instruccion {
    String id;
    Expresion valor;

    public Asignacion(String id, int linea,int columna,Expresion valor) {
        this.linea=linea;
        this.columna=columna;
        this.id = id;
        this.valor = valor;
    }
    @Override
    public Object ejecutar(Entorno ent){
        Simbolo sim=ent.buscar(id,linea,columna,"La variable");
        if(sim!=null){
            Expresion resultado=valor.getValor(ent);
            switch(sim.tipo.tipo){
                case entero:
                    switch(resultado.tipo.tipo){
                        case entero:
                            sim.valor=resultado.valor;
                          
                            return null;
                        case caracter:
                            int ascii=(int)resultado.valor.toString().charAt(0);
                            sim.valor=ascii;
                           
                            return null;
                    }
                    break;
                case doble:
                    switch(resultado.tipo.tipo){
                        case entero:
                            sim.valor=(double)resultado.valor;
                        
                            return null;
                        case doble:
                            
                            sim.valor=resultado.valor;
                         
                            return null;
                    }
                    
                    break;
                case caracter:
                    switch(resultado.tipo.tipo){
                        case caracter:
                            sim.valor=resultado.valor;
                       
                   
                            return null;
                        case entero:
                            
                            sim.valor=(char)resultado.valor;
                            
                            return null;
                    }
                    break;
                case booleano:
                     switch(resultado.tipo.tipo){
                        case booleano:
                            sim.valor=resultado.valor;                  
                   
                            return null;
                     }
                    break;
                case cadena:
                     switch(resultado.tipo.tipo){
                        case caracter:
                            sim.valor=String.valueOf(resultado.valor);
                          
                   
                            return null;
                        case cadena:
                        
                      
                            sim.valor=resultado.valor;
                            
                            return null;
                    }
                    break;
            }
            System.out.println("El tipo de dato que se le quiere asignar a la variable "+id+" es incorrecto"+" Tipo:"+sim.tipo+" !=" +resultado.tipo);
                   
        }
        return null;
    }
    
}
