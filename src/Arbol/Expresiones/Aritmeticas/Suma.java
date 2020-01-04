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
                    int varia=Integer.parseInt(res1.valor.toString());
                    switch(res2.tipo.tipo){
                        
                        case entero:
                            
                            int b=Integer.parseInt(res2.valor.toString());
                            return new Literal( res1.tipo, varia+b);
                        case caracter:
                            int ascii=(int)res2.valor.toString().charAt(0);
                            return new Literal(res1.tipo,Integer.parseInt(res1.valor.toString())+ascii);
                        case doble:  
                            double var1=Double.parseDouble(res2.valor.toString());
                            double resul=varia+var1;
                            return new Literal( res2.tipo, resul );
                        case cadena:
                            String var2=res2.valor.toString();
                            String result=varia+var2;
                            return new Literal( res2.tipo, result );
                    }
                    break;
                case doble:
                    double var=Double.parseDouble(res1.valor.toString());
                     switch(res2.tipo.tipo){
                        case entero:        
                            int res3=Integer.parseInt(res2.valor.toString());
                            double resulta=var+res3;
                            return new Literal( res1.tipo, resulta);
                        case doble:  
                            double res=Double.parseDouble(res2.valor.toString());
                            double result=var+res;
                            return new Literal( res1.tipo, result );
                        case caracter:
                            int ascii=(int)res2.valor.toString().charAt(0);
                           double resultado=ascii+var;
                            return new Literal(res1.tipo,resultado);
                        case cadena:
                            String var1=var+res2.valor.toString();
                            return new Literal(res2.tipo,var1);
                    }
                    break;
                case caracter:
                    int ascii=(int)res1.valor.toString().charAt(0);
                    switch(res2.tipo.tipo){
                        case entero:
                            return new Literal(res2.tipo,ascii+Integer.parseInt(res2.valor.toString()));
                        case doble:
                            double var2=Double.parseDouble(res2.valor.toString());
                            double resultado=ascii+var2;
                            return new Literal(res2.tipo,resultado);
                        case caracter:
                            int asc=(int)res2.valor.toString().charAt(0);
                            return new Literal(new Tipo(Tipo.EnumTipo.entero),ascii+asc);
                        case cadena:
                            return new Literal(res2.tipo,(char)ascii+res2.valor.toString());
                    }
                    break;
                case cadena:
                    switch(res2.tipo.tipo){
                        case caracter:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                        case entero:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                        case doble:
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                        case cadena:                          
                            return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                        case booleano:
                           return new Literal(res1.tipo,res1.valor.toString()+res2.valor.toString());
                    }
                    break;
                case booleano:
                    switch(res2.tipo.tipo){
                        case cadena:
                            return new Literal(res2.tipo,res1.valor.toString()+res2.valor.toString());
                    }
                    break;
            }
            System.out.println("Error Semantico: El tipo de valores que se quieren sumar no son iguales  Tipo: "+res1.tipo.tipo+" + "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError("Semantico", "Opción incorrecta:\nNo se puede sumar un "+res1.tipo.tipo+" con un "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
            return new Literal(new Tipo(Tipo.EnumTipo.error),"@Error@");
        }
       return null;
    }
//    private void Error(Expresion e,Expresion f){
//        if(e.tipo.tipo.equals(Tipo.EnumTipo.error)){
//            if(f.tipo.tipo.equals(Tipo.EnumTipo.error)){
//                System.out.println("Error semantico: Las variables que se quieren sumar no existen. Tipo:"+Tipo.EnumTipo.error+" Linea: "+linea+" Columna: "+columna);
//                lista_errores.add(new CError(Tipo.EnumTipo.error.toString(),"Las variables '"+e.id+"' y '"+f.id+"' no existen",linea,columna));
//            }else{
//                     System.out.println("Error semantico: No es posible sumar las variables. Tipo:"+Tipo.EnumTipo.error+"/ Tipo:"+f.tipo+" Linea: "+linea+" Columna: "+columna);
//                lista_errores.add(new CError(Tipo.EnumTipo.error.toString(),"Las variables no se pueden sumar. Tipo: "+Tipo.EnumTipo.error+"/ Tipo: "+f.tipo+".",linea,columna));
//            }
//        }else{
//            if(e.tipo.tipo.equals(Tipo.EnumTipo.error)){
//                System.out.println("Error semantico: No es posible sumar las variables. Tipo:"+e.tipo+"/ Tipo:"+Tipo.EnumTipo.error+" Linea: "+linea+" Columna: "+columna);
//                lista_errores.add(new CError(Tipo.EnumTipo.error.toString(),"Las variables no se pueden sumar. Tipo: "+e.tipo+"/ Tipo: "+Tipo.EnumTipo.error+".",linea,columna));
//            }
//        }
//    }
     
}
