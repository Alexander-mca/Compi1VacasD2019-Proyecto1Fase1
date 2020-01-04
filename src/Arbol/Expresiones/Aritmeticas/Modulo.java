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
public class Modulo extends Expresion{
    String operacion;
    Expresion hijo1,hijo2;
    public Modulo(int linea,int columna, Expresion hijo1, Expresion hijo2){
        this.operacion="%";
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
            try{
            switch(res1.tipo.tipo){
                   case entero:
                    switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Integer.parseInt(res1.valor.toString())%Integer.parseInt(res2.valor.toString()));
                        case doble:
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Double.parseDouble(res1.valor.toString())% Double.parseDouble(res2.valor.toString()));
                        case caracter:
                            int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Double.parseDouble(res1.valor.toString())%ascii);
                    }
                case doble:
                     switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(res1.tipo,Double.parseDouble(res1.valor.toString())%Double.parseDouble(res2.valor.toString()));
                        case doble:
                            return new Literal(res1.tipo,Double.parseDouble(res1.valor.toString())%Double.parseDouble(res2.valor.toString()));
                    case caracter:
                         int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.doble),Double.parseDouble(res1.valor.toString())%ascii);
                     }    
                    break;
                case caracter:
                int ascii=(int)res1.valor.toString().charAt(0);
                switch(res2.tipo.tipo){
                    case entero:
                        return new Literal(new Tipo(Tipo.EnumTipo.entero),ascii%Integer.parseInt(res2.valor.toString()));
                    case doble:
                        return new Literal(res2.tipo,ascii%Double.parseDouble(res2.valor.toString()));
                    case caracter:
                        int asc=(int)res2.valor.toString().charAt(0);
                        return new Literal(new Tipo(Tipo.EnumTipo.doble),ascii%asc);
                }
                    break;
                
            }
             System.out.println("Error Semantico: El tipo de valores que se les quiere aplicar modulo no son iguales. Tipo: "+res1.tipo.tipo+" % "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Semantico", "Opción incorrecta:\nNo se puede aplicar modulo. Tipos no compatibles, Tipo: "+res1.tipo.tipo+" % Tipo: "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
             return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
            }catch(ArithmeticException e){
                 System.out.println("Error de Ejecucion: No se puede dividir por 0. Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Ejecucion", "Opción incorrecta:\nNo se puede dividir por 0.", linea, columna);
            lista_errores.add(error);
             return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
            }
        }
       return null;
    }
}
