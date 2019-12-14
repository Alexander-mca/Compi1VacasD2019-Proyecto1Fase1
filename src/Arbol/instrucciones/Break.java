/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Instruccion;
import Arbol.instrucciones.Contexto.TipoInstruccion;
import Interfaz.CError;
import static Interfaz.Editor.lista_ciclos;

/**
 *
 * @author alexa
 */
public class Break extends Instruccion{
    
    public Break(int linea,int columna){
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        
      if(lista_ciclos.getLast()==TipoInstruccion.ciclo){
          lista_ciclos.pop();
          return this;
      }else if(lista_ciclos.getLast()==TipoInstruccion.rswitch){
          lista_ciclos.pop();
          return this;
      }
      
      Interfaz.Editor.lista_errores.add(new CError("Semantico", "La sentencia esta fuera de un ciclo", linea, columna));
            
       return null;
    }
    
    
}
