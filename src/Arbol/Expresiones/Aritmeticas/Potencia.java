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
/**
 *
 * @author alexa
 */
public class Potencia extends Expresion {

    String Operacion;
    Expresion hijo1,hijo2;

    public Potencia(int linea,int columna,Expresion hijo1, Expresion hijo2) {
        this.Operacion = "**";
        this.hijo1 = hijo1;
        this.hijo2 = hijo2;
        this.linea=linea;
        this.columna=columna;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1=hijo1.getValor(ent);
        Expresion res2=hijo2.getValor(ent);
        if(res1!=null && res2!=null){
            switch(res1.tipo.tipo){
                case entero:
                    switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(res1.tipo,Integer.parseInt(res1.valor.toString())^Integer.parseInt(res2.valor.toString()));
                        case doble:
                            return new Literal(res2.tipo,Math.pow(Double.parseDouble(res1.valor.toString()), Double.parseDouble(res2.valor.toString())));
//                        case caracter:
//                            return new Literal(res1.tipo.tipo.doble,Math.pow(Double.parseDouble(res1.valor.toString()), Double.parseDouble(res2.valor.toString())))
                    }
                case doble:
                     switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(res2.tipo,Math.pow(Double.parseDouble(res1.valor.toString()),Double.parseDouble(res2.valor.toString())));
                        case doble:
                            return new Literal(res2.tipo,Math.pow(Double.parseDouble(res1.valor.toString()), Double.parseDouble(res2.valor.toString())));
                    }
                    break;
                case caracter:
                    break;
            }
            
        }
         return null;   
    }
    
}
