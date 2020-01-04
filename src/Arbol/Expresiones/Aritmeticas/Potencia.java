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
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Integer.parseInt(res1.valor.toString())^Integer.parseInt(res2.valor.toString()));
                        case doble:
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Math.pow(Double.parseDouble(res1.valor.toString()), Double.parseDouble(res2.valor.toString())));
                        case caracter:
                            int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Math.pow(Double.parseDouble(res1.valor.toString()), ascii));
                    }
                case doble:
                     switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(res1.tipo,Math.pow(Double.parseDouble(res1.valor.toString()),Double.parseDouble(res2.valor.toString())));
                        case doble:
                            return new Literal(res1.tipo,Math.pow(Double.parseDouble(res1.valor.toString()), Double.parseDouble(res2.valor.toString())));
                    case caracter:
                         int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Math.pow(Double.parseDouble(res1.valor.toString()), ascii));
                     }    
                    break;
                case caracter:
                int ascii=(int)res1.valor.toString().charAt(0);
                switch(res2.tipo.tipo){
                    case entero:
                        return new Literal(new Tipo(Tipo.EnumTipo.entero),Math.pow(ascii, Integer.parseInt(res2.valor.toString())));
                    case doble:
                        return new Literal(res2.tipo,Math.pow(ascii,Double.parseDouble(res2.valor.toString())));
                    case caracter:
                        int asc=(int)res2.valor.toString().charAt(0);
                        return new Literal(new Tipo(Tipo.EnumTipo.doble),Math.pow(ascii, asc));
                }
                    break;
            }
             System.out.println("Error Semantico: El tipo de valores que se quieren operar con la potencia no son compatibles. Tipo: "+res1.tipo.tipo+" ** "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Semantico", "Opción incorrecta:\nNo se puede Operar una potenciación con\n una base Tipo:"+res1.tipo.tipo+" y un exponente Tipo: "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
             return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
            
        }
         return null;   
    }
    
}
