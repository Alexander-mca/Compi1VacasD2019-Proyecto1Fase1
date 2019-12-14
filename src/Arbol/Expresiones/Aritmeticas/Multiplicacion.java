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
public class Multiplicacion extends Expresion{
String operacion;
    Expresion hijo1,hijo2;
    public Multiplicacion(int linea,int columna, Expresion hijo1, Expresion hijo2){
        this.operacion="*";
        this.linea=linea;
        this.columna=columna;
        this.hijo1=hijo1;
        this.hijo2=hijo2;
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
                            int a=Integer.parseInt(res1.valor.toString());
                            int b=Integer.parseInt(res2.valor.toString());
                            return new Literal( res1.tipo, a*b);
                            
                        case doble:
                           
                            return new Literal( res2.tipo, Double.parseDouble(res1.valor.toString()) * Double.parseDouble(res2.valor.toString()) );
                        case caracter:
                            int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(res1.tipo,Integer.parseInt(res1.valor.toString())*ascii);
                    }
                    break;
                case doble:
                     switch(res2.tipo.tipo){
                        case entero:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) * Double.parseDouble(res2.valor.toString()));
                            
                        case doble:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) * Double.parseDouble(res2.valor.toString()) );
                        case caracter:
                             int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(res1.tipo,Double.parseDouble(res1.valor.toString())*ascii);
                    }
                    break;
                case caracter:
                    int ascii=(int)res1.valor.toString().charAt(0);
                    switch(res2.tipo.tipo){
                        case entero:                            
                            return new Literal(res2.tipo,ascii*Integer.parseInt(res2.valor.toString()));
                        case doble:
                            return new Literal(res1.tipo,ascii*Double.parseDouble(res2.valor.toString()));
                        case caracter:
                            int asc2=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.entero),ascii*asc2);
                            
                    }
                    break;

            }
        
        System.out.println("Error Semantico: El tipo de valores que se quieren multiplicar no son iguales "+res1.tipo.tipo+" Tipo: "+tipo.tipo+" / "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nNo se puede multiplicar un "+res1.tipo.tipo+" con un "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
        }
       return null;
    }
    
}
