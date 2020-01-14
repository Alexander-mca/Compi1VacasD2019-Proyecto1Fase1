/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Instruccion;
import Interfaz.CError;
import static Interfaz.Editor.lista_ciclos;

/**
 *
 * @author alexa
 */
public class Continue extends Instruccion{

      public Continue(int linea,int columna){
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Object ejecutar(Entorno ent) {
         if(lista_ciclos.getLast()==Contexto.TipoInstruccion.ciclo){
//          lista_ciclos.pop();
          return this;
      }
      
      Interfaz.Editor.lista_errores.add(new CError("Semantico", "La sentencia esta fuera del ciclo", linea, columna));
            
       return null;
        
    }
    
}
