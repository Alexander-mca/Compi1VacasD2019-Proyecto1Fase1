/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Asignacion;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Aumento extends Expresion{

//String nombre;
//
//
//
//    public Aumento(String nombre) {
//        this.nombre=nombre;
//    }
    Expresion nombre;
    public Aumento(Expresion nombre){
        this.nombre=nombre;
    }
            

   
  @Override
    public Expresion getValor(Entorno ent) {
//       Simbolo resultado=ent.buscar(nombre, linea, columna, "La variable");
        Expresion resultado=nombre.getValor(ent);
        
        if(resultado!=null){
           
               switch(resultado.tipo.tipo){
                   case entero:
                       int a=Integer.parseInt(resultado.valor.toString());
                       Expresion exp=nombre.getValor(ent);
                       a++;
                     
                       resultado.valor=a;
                       
                       Asignacion asig=new Asignacion(resultado.id, linea, columna, resultado);
                       asig.ejecutar(ent);
                       return exp;
                   case doble:
                        double b=Double.parseDouble(resultado.valor.toString());
                       Expresion res=nombre.getValor(ent);
                       b++;
                     
                       resultado.valor=b;
                       Asignacion asig2=new Asignacion(resultado.id, linea, columna, resultado);
                       asig2.ejecutar(ent);
                       return res;
                   case caracter:
                       int c=(int)resultado.valor.toString().charAt(0);
                      Expresion res2=nombre.getValor(ent);
                       c++;
                       
                       resultado.valor=(char)c;
                       Asignacion asig3=new Asignacion(resultado.id, linea, columna, resultado);
                       asig3.ejecutar(ent);
                       return res2;
                       
               }
                
            }else{
                 System.out.println("Error Semantico: El incremento solo se puede usar en id's.  Valor "+nombre.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nSolo se puede usar el incremento con Id's. Valor "+nombre.tipo, linea, columna);
            lista_errores.add(error);
            }
        
            
        return null;
    }
  
    
}
