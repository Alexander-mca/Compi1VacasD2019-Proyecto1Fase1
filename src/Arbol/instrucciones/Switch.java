/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Expresiones.relacionales.Igualque;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Contexto.TipoInstruccion;
import static Interfaz.Editor.lista_ciclos;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Switch extends Instruccion{
public Expresion exp;
public Bloque lista_casos;
private int veces=1;
private boolean ejecutado=false;
    public Switch(Expresion exp, Bloque lista_casos) {
        this.exp = exp;
        this.lista_casos = lista_casos;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        
       
            Entorno actual=new Entorno(ent);
           
            Expresion resultado=exp.getValor(actual);
           
        
        if(resultado!=null){
            if(veces==1){
            lista_ciclos.add(TipoInstruccion.rswitch);
            }
            if(lista_casos!=null){
            for(Nodo caso:lista_casos.intruccion){
                if(caso instanceof Caso){
                    if(!ejecutado){
                        Igualque igual=new Igualque(linea, columna, resultado,((Caso) caso).value);
                        Expresion variable1=igual.getValor(actual);
                    if(Boolean.parseBoolean(variable1.valor.toString())){
                        Object obj=((Instruccion)caso).ejecutar(actual);
                        ejecutado=true;  
                        if(obj instanceof Break){                                                     
                             break;
                        }
//                        else if(obj instanceof Continue){
//                  ejecutado=false;
//                  break;
////                  return null;
//              }                       
                    }else{
                        continue;
                    }
                    }else{
                        /*si ya se encontro el case con el valor de switch entonces se barre con el resto de casos 
                        hasta encontrar un caso con break o un default*/
                        
                         Object obj=((Instruccion)caso).ejecutar(actual);
                          
                        if(obj instanceof Break){                                                     
                             break;
                        }
                    }
                    
                }else if(caso instanceof Default){
                    
                    if(ejecutado){
                        
                         Object obj=((Instruccion)caso).ejecutar(actual);
                          
                        if(obj instanceof Break){                                                     
                             break;
                        } 
                    }else{
                        if(veces==2){
                            ejecutado=true;
                             Object obj=((Instruccion)caso).ejecutar(actual);
                          
                        if(obj instanceof Break){                                                     
                             break;
                        } 
                      
                        }
                    }                      
                        
                       
                        
                    } else if(caso instanceof Continue){                        
                        return caso;                
                }
            }
            }else{
                ejecutado=true;
            }
            if(!ejecutado){
                veces++;
                ejecutar(actual);
            }else{
                ejecutado=false;
            }
            
            
        
        }
        return null;
    }
    
}
