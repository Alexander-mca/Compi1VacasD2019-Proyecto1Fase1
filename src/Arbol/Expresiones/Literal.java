/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones;


import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;

/**
 *
 * @author alexa
 */
public class Literal extends Expresion{
  
    public Literal(Tipo tipo,Object valor){
        this.tipo=tipo;
        this.valor= valor;
    }
    
    @Override
    public Expresion getValor(Entorno ent){
        return new Literal(tipo,valor);
    }
            
}
