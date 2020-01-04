/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Expresion;
import Arbol.Entorno.Tipo;

/**
 *
 * @author alexa
 */
public class Id extends Expresion {
   
     public Id(String id, int linea,int columna){
         this.id=id;
         this.linea=linea;
         this.columna=columna;
     }
     
     @Override
     public Expresion getValor(Entorno ent){
 
         Simbolo sim=ent.buscar(id,linea,columna,"La variable ");
         if(sim!=null){
             Literal retorno=new Literal(sim.tipo,sim.valor);
              retorno.id=id;
             return retorno;
         }else{
         return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
         }
     }
    
}
