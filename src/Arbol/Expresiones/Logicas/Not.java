/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.Logicas;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Literal;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Not extends Expresion{
    String operacion;
    Expresion val;
    public Not(int linea,int columna,Expresion val){
        this.operacion="!";
        this.val=val;
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion resultado=val.getValor(ent);
        if(resultado!=null){
            switch(resultado.tipo.tipo){
                case booleano:
            if(Boolean.parseBoolean(resultado.valor.toString())){
                return new Literal(new Tipo(Tipo.EnumTipo.booleano),false);
            }else{
                return new Literal(new Tipo(Tipo.EnumTipo.booleano),true);
            
            }
           
            
        }
             System.out.println("Error Semantico: No es posible realizar la operación. "+resultado.tipo.tr+" Tipo: "+resultado.tipo+". Solo se puede con valores booleanos. Linea:"+linea+" Columna:"+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nNo se puede realizar la operacion "+resultado.valor+" no es booleano ", linea, columna);
            lista_errores.add(error);
        }
        return null;
    }
    
}
