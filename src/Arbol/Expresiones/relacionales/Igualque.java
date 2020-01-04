/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.relacionales;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Literal;
import Arbol.Objeto;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Igualque extends Expresion {

    String operacion;
    Expresion hijo1, hijo2;

    public Igualque(int linea, int columna, Expresion hijo1, Expresion hijo2) {
        this.operacion = "==";
        this.hijo1 = hijo1;
        this.hijo2 = hijo2;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1 = hijo1.getValor(ent);
        Expresion res2 = hijo2.getValor(ent);
        if (res1 != null && res2 != null) {
            switch (res1.tipo.tipo) {
                case entero:
//                    int a = Integer.parseInt(res1.valor.toString());
                    switch (res2.tipo.tipo) {
                        case entero:

//                            int b = Integer.parseInt(res2.valor.toString());
                            return Validar(res1.valor.toString(), res2.valor.toString());
                        case doble:
//                            double be = Double.parseDouble(res2.valor.toString());
                            return Validar(res1.valor.toString(), res2.valor.toString());
                        case caracter:
                            int ascii = (int) res2.valor.toString().charAt(0);
                            return Validar(res1.valor.toString(), String.valueOf(ascii));

                    }
                    break;
                case doble:
//                   double var1=Double.parseDouble(res1.valor.toString());
                   switch(res2.tipo.tipo){
                       case doble:
//                           double var2=Double.parseDouble(res2.valor.toString());
                           return Validar(res1.valor.toString(), res2.valor.toString());
                       case  entero:
//                           int va1=Integer.parseInt(res2.valor.toString());
                          return Validar(res1.valor.toString(), res2.valor.toString());
                       case caracter:
                           int ascii=(int)res2.valor.toString().charAt(0);
                            return Validar(res1.valor.toString(), String.valueOf(ascii));
                           
                   }
                    break;
                case caracter:
                    int x=(int)res1.valor.toString().charAt(0);
                    switch(res2.tipo.tipo){
                        case caracter:
                            int y=(int)res2.valor.toString().charAt(0);
                            return Validar(String.valueOf(x),String.valueOf(y));
                        case entero:
//                            int b=Integer.parseInt(res2.valor.toString());
                            return Validar(String.valueOf(x),res2.valor.toString());
                        case doble:
//                            double r=Double.parseDouble(res2.valor.toString());
                            return Validar(String.valueOf(x),res2.valor.toString());
                    }
                    break;
                case cadena:
                    switch(res2.tipo.tipo){
                        case cadena:
                           return Validar(res1.valor.toString(),res2.valor.toString());
                           
                    }
                    break;
                case booleano:
                    switch(res2.tipo.tipo){
                        case booleano:
                            return Validar(res1.valor.toString(),res2.valor.toString());
                    }
                    break;
                    //agregar tipos objeto en Fase2
                case objeto:
                    Objeto obj1=(Objeto)res1.valor;
                    switch(res2.tipo.tipo){
                        case objeto:
                            Objeto obj2=(Objeto)res2.valor;
                            return ValidarObjeto(obj1,obj2);
                        case nulo:
                            return ValidarObjeto(obj1,null);
                     }
                        
                    break;
                case nulo:
                    switch(res2.tipo.tipo){
                        case nulo:
                            return ValidarObjeto(null,null);
                        case objeto:
                            Objeto obj=(Objeto)res2.valor;
                            return ValidarObjeto(null,obj);
                    }
                    break;
                   
            }
          System.out.println("Error Semantico: No es posible comparar los valores. "+res1.tipo.tipo+" == "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Semantico", "Opción incorrecta:\nNo se puede comparar. Tipo: "+res1.tipo.tipo+" == Tipo: "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
             return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
        }
        return null;
    }

    private Literal Validar(String a, String b) {
        
        if (a.equals(b)) {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), true);
        } else {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), false);
        }
    }
    private Literal ValidarObjeto(Objeto a,Objeto b){
         if (a.equals(b)) {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), true);
        } else {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), false);
        }
        
    }

}
