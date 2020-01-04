/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.MetodosyFunciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Instruccion;

/**
 *
 * @author alexa
 */
public class Return extends Instruccion{
    public Expresion retorno;
    public Return(Expresion expresion){
        this.retorno=expresion;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        if(retorno!=null){
        Expresion exp=retorno.getValor(ent);
        if(exp!=null){
            return exp;
        }
        }else{
            return this;
        }
        return null;
    }
    
}
