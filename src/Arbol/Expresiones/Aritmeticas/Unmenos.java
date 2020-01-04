/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.Aritmeticas;

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
public class Unmenos extends Expresion{
    Expresion exp;
    public Unmenos(Expresion exp,int linea,int columna){
        this.exp=exp;
        this.columna=columna;
        this.linea=linea;
    }
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion resultado=exp.getValor(ent);
        if(resultado!=null){
            switch(resultado.tipo.tipo){
                case entero:
                    return new Literal(resultado.tipo,-Integer.parseInt(resultado.valor.toString()));
                case doble:
                     return new Literal(resultado.tipo,-Double.parseDouble(resultado.valor.toString()));
                case caracter:
                    int ascii=(int)resultado.valor.toString().charAt(0);
                    return new Literal(resultado.tipo,-ascii);
            }
            System.out.println("Error Semantico: La operacion no se puede realizar.  -"+resultado.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Semantico", "Opción incorrecta:\nNo se puede realizar la operacion. -"+resultado.tipo.tipo, linea, columna);
            lista_errores.add(error);
             return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
            
        }
        return null;
    }
    
}
