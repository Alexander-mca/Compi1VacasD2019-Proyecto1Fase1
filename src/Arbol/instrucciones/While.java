/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Contexto.TipoInstruccion;
import Interfaz.CError;
import static Interfaz.Editor.lista_ciclos;
import static Interfaz.Editor.lista_errores;
import java.math.BigInteger;
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
//        Entorno actual;
//        if (veces == 1) {
//            lista_ciclos.add(TipoInstruccion.ciclo);
//            actual = new Entorno(ent);
//        } else {
//            actual = ent;
//        }
//        Expresion resultado=condicion.getValor(actual);
//        boolean ejecutado=false;
//        if(resultado!=null && Boolean.parseBoolean(resultado.valor.toString())){
//            
//            
//            if(lista_instrucciones!=null){
//          for(Nodo instruccion:lista_instrucciones.intruccion){
//             
//              if(instruccion instanceof Instruccion){
//               Object obj= ((Instruccion)instruccion).ejecutar(actual); 
//               if(obj instanceof Break){
//                  ejecutado=true;
//                  break;
////                  return null;
//              
//              }else if(obj instanceof Continue){
//                  ejecutado=false;
//                  break;
////                  return null;
//              }
//              }else if(instruccion instanceof Expresion){
//                  ((Expresion)instruccion).getValor(actual);
//              }
//             
//          }
//            }else{
//               lista_errores.add(new CError("Ejecucion","Bloque While vacío. Bucle infinito.",linea,columna));
//               return null;
//            }
//          
//          if(!ejecutado){
//              veces++;
//              try{
//             ejecutar(actual);
//              }catch(StackOverflowError e){
//                  
//              }
//          }
//        }
         Object obj=EjecutarWhile(ent);
       return null;
    }
    public Object EjecutarWhile(Entorno ent) {
        Entorno actual = new Entorno(ent);
        boolean ejecutado;
      
//        lista_ciclos.add(TipoInstruccion.ciclo);
//       LinkedList<TipoInstruccion> lista=lista_ciclos;
        Expresion resultado = condicion.getValor(actual);
        if(resultado.tipo.tipo.equals(Tipo.EnumTipo.booleano)){
        while (true) {          
                 if (Boolean.parseBoolean(resultado.valor.toString())) {
                  ejecutado = EjecutarInstrucciones(actual);
                  if(ejecutado){  
                      lista_ciclos.pop();
                      break;
                  }
                }   
                 
        }
        }else{
            lista_errores.add(new CError("Semantico","Error de tipos en la condición del While. No puede venir una expresion de Tipo: "+resultado.tipo.tipo+".",resultado.linea,resultado.columna));
        }
        return null;
    }
    private boolean EjecutarInstrucciones(Entorno actual){
        boolean ejecutado=false;
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
              
            }
        return ejecutado;
    }
}
