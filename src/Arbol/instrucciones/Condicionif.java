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
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Condicionif extends Instruccion{

    Expresion exp;
    Bloque bloque;
    public Condicionif(Expresion exp,Bloque bloque,int linea,int columna){
        this.exp=exp;
     this.bloque=bloque;
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno actual=new Entorno(ent);
        if(ent.Global!=null){
            actual.Global=ent.Global;
        }
        Expresion resultado=exp.getValor(actual);
        Boolean ejecutado=false;
        if(resultado.tipo.tipo.equals(Tipo.EnumTipo.booleano)){
        
        if(Boolean.parseBoolean(resultado.valor.toString())){
            ejecutado=true;
            if(bloque!=null){
         Object obj= bloque.ejecutar(actual);
            if(obj!=null){
                //a++; 
                return obj;
            }
            }
//            else if(obj instanceof Expresion){
//                
//            }
        }
        }else{
             lista_errores.add(new CError("Semantico","Error de tipos en la condición del if. No puede venir una expresion de Tipo: "+resultado.tipo.tipo+".",resultado.linea,resultado.columna));
             
        }
        return ejecutado;
    }
    
}
