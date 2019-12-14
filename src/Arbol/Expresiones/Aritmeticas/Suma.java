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
public class Suma extends Expresion{
    
    String operacion;
    Expresion hijo1,hijo2;
    public Suma(int linea,int columna, Expresion hijo1, Expresion hijo2){
        this.operacion="+";
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
                            return new Literal( res1.tipo, a+b);
                            
                        case doble:
                           
                            return new Literal( res2.tipo, Double.parseDouble(res1.valor.toString()) + Double.parseDouble(res2.valor.toString()) );
                        case cadena:
                            return new Literal( res2.tipo, Double.parseDouble(res1.valor.toString()) + res2.valor.toString() );
                    }
                    break;
                case doble:
                     switch(res2.tipo.tipo){
                        case entero:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) + Double.parseDouble(res2.valor.toString()));
                            
                        case doble:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) + Double.parseDouble(res2.valor.toString()) );
                            
                    }
                    break;
                case cadena:
                    switch(res2.tipo.tipo){
                        case caracter:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor);
                        case entero:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor);
                        case doble:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor);
                        case cadena:                          
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                    }
                    break;
            }
            System.out.println("Error Semantico: El tipo de valores que se quieren sumar no son iguales "+res1.tipo.tipo+" Tipo: "+tipo.tipo+" / "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nNo se puede sumar un "+res1.tipo.tipo+" con un "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
        }
       return null;
    }
     
}
