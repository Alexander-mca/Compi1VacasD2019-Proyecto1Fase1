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
         Entorno actual=new Entorno(ent);
            if(veces==1){
               
//            lista_ciclos.add(TipoInstruccion.ciclo);
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
              }else if(obj!=null){
                      return obj;
              }
              
                    
              }else if(instruccion instanceof Expresion){
                  ((Expresion)instruccion).getValor(actual);
              }
          }
            }else{
                lista_errores.add(new CError("Ejecucion","Bloque DoWhile vacío. Bucle infinito.",linea,columna));
                return null;
            }
          
          if(!ejecutado){
              Expresion resultado=condicion.getValor(actual);
              if(resultado.tipo.tipo.equals(Tipo.EnumTipo.booleano)){
              if(Boolean.parseBoolean(resultado.valor.toString())){
              veces++;
             ejecutar(actual);
               }
              }else{
                   lista_errores.add(new CError("Semantico","Error de tipos en la condición del DoWhile. No puede venir una expresion de Tipo: "+resultado.tipo.tipo+".",resultado.linea,resultado.columna));
              }
          }else{
              lista_ciclos.pop();
          }
       return null;
    }
    
}
