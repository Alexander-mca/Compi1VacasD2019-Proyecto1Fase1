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
import static Interfaz.Editor.lista_ciclos;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Dowhile extends Instruccion{
   Expresion condicion;
  Bloque lista_instrucciones;
  int veces=1;
   public Dowhile(Expresion condicion,Bloque lista_instrucciones){
       this.condicion=condicion;
       this.lista_instrucciones=lista_instrucciones;
       lista_ciclos.add(TipoInstruccion.ciclo);
   }
    @Override
    public Object ejecutar(Entorno ent) {
        
        boolean ejecutado=false;
        
            if(veces==1){
            lista_ciclos.add(TipoInstruccion.ciclo);
            }
          for(Nodo instruccion:lista_instrucciones.intruccion){
              if(instruccion instanceof Instruccion){
             Object obj= ((Instruccion)instruccion).ejecutar(ent);
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
                  ((Expresion)instruccion).getValor(ent);
              }
          }
          
          if(!ejecutado){
              Expresion resultado=condicion.getValor(ent);
              if(resultado!=null && Boolean.parseBoolean(resultado.valor.toString())){
              veces++;
             ejecutar(ent);
          }
          }
       return null;
    }
    
}
