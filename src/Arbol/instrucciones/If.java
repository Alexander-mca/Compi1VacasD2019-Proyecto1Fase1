/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class If extends Instruccion{
 LinkedList<Instruccion> lista_condiciones;
 Bloque bloqueelse;
  public If(LinkedList<Instruccion> lista_condiciones,Bloque bloqueelse,int linea,int columna){
      this.lista_condiciones=lista_condiciones;
  this.bloqueelse=bloqueelse;
      this.linea=linea;
      this.columna=columna;
  }
    @Override
    public Object ejecutar(Entorno ent) {
        boolean ejecutado=true;
        Entorno actual=new Entorno(ent);
        for(Instruccion condicion: lista_condiciones){
           Object obj= condicion.ejecutar(actual);
            if(Boolean.parseBoolean(obj.toString())){
                ejecutado=false;
                //Si da error alguna vez, puede ser por este break
                break;
            }else if(obj instanceof Break || obj instanceof Continue){
                return obj;
            }
        }
        if(ejecutado && bloqueelse!=null){
        Entorno nuevo=new Entorno(actual);
        bloqueelse.ejecutar(nuevo);
        }
        return null;
    }
    
}
