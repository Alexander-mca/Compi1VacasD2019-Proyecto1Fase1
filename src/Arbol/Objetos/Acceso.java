/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Objetos;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Id;
import Arbol.Instruccion;
import Arbol.MetodosyFunciones.Llamada;
import Arbol.Objeto;

/**
 *
 * @author alexa
 */
public class Acceso extends Expresion{
public String idObj;
public Expresion expresion;

//    public Acceso(String idObj, String idVar,int linea,int columna) {
//        this.idObj = idObj;
//        this.linea=linea;
//        this.columna=columna;
//        this.idVar = idVar;
//    }

    public Acceso(String idObj, Expresion expresion,int linea,int columna) {
        this.idObj = idObj;
        this.expresion = expresion;
        this.linea=linea;
        this.columna=columna;
    }
     
    @Override
    public Expresion getValor(Entorno ent) {
       String rthis=idObj;
       Simbolo sim;
       if(rthis.toLowerCase().equals("this")){
           idObj=rthis.toLowerCase();
           sim=ent.Global.buscar(idObj, linea, columna, "El objeto ");
       }else{
          sim=ent.Global.buscar(idObj, linea, columna, "El objeto ");
          if(sim==null){
       sim = ent.buscar(idObj, linea, columna, "El objeto ");
          }
       }
     
       if (sim.tipo.tipo.equals(Tipo.EnumTipo.objeto)) {
            Objeto obj = (Objeto) sim.valor;
            Entorno entObj = obj.entorno;
            Expresion exp=expresion.getValor(entObj);
//            if (expresion instanceof Id) {                 
//                
//                 exp = expresion.getValor(entObj);                
//                
//            }else if ( expresion!= null) {               
//                exp=expresion.getValor(entObj);
//            }
            return exp;
        } //marcar error si la variable no es un objeto
        return null;
    }
    
}
