/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Contexto.TipoInstruccion;
import Interfaz.CError;
import static Interfaz.Editor.lista_ciclos;
import static Interfaz.Editor.lista_errores;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class While extends Instruccion{
   Expresion condicion;
  Bloque lista_instrucciones;
  int veces=1;
   public While(Expresion condicion,Bloque lista_instrucciones){
       this.condicion=condicion;
       this.lista_instrucciones=lista_instrucciones;
       lista_ciclos.add(TipoInstruccion.ciclo);
   }
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno actual=new Entorno(ent);
        Expresion resultado=condicion.getValor(actual);
        boolean ejecutado=false;
        if(resultado!=null && Boolean.parseBoolean(resultado.valor.toString())){
            if(veces==1){
            lista_ciclos.add(TipoInstruccion.ciclo);
            }
            
            if(lista_instrucciones!=null){
          for(Nodo instruccion:lista_instrucciones.intruccion){
             
              if(instruccion instanceof Instruccion){
               Object obj= ((Instruccion)instruccion).ejecutar(actual); 
               if(obj instanceof Break){
                  ejecutado=true;
                  break;
//                  return null;
              
              }else if(obj instanceof Continue){
                  ejecutado=false;
                  break;
//                  return null;
              }
              }else if(instruccion instanceof Expresion){
                  ((Expresion)instruccion).getValor(actual);
              }
             
          }
            }else{
               lista_errores.add(new CError("Ejecucion","Bloque While vacío. Bucle infinito.",linea,columna));
               return null;
            }
          
          if(!ejecutado){
              veces++;
             ejecutar(actual);
          }
        }
       return null;
    }
    
}
