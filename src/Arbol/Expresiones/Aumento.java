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
    String nombre;
    public Aumento(String nombre){
        this.nombre=nombre;
    }
            

   
  @Override
    public Expresion getValor(Entorno ent) {
//       Simbolo resultado=ent.buscar(nombre, linea, columna, "La variable");
        Id id2=new Id(nombre,linea,columna);
        Expresion resultado=id2.getValor(ent);
        
        
    
           
               switch(resultado.tipo.tipo){
                   case entero:
                       int a=Integer.parseInt(resultado.valor.toString());
                       Expresion exp=resultado.getValor(ent);
                       a++;
                     
                       resultado.valor=a;
                       
                       Asignacion asig=new Asignacion(resultado.id, linea, columna, resultado);
                       asig.ejecutar(ent);
                       return exp;
                   case doble:
                        double b=Double.parseDouble(resultado.valor.toString());
                       Expresion res=resultado.getValor(ent);
                       b++;
                     
                       resultado.valor=b;
                       Asignacion asig2=new Asignacion(resultado.id, linea, columna, resultado);
                       asig2.ejecutar(ent);
                       return res;
                   case caracter:
                       int c=(int)resultado.valor.toString().charAt(0);
                      Expresion res2=resultado.getValor(ent);
                       c++;
                       
                       resultado.valor=(char)c;
                       Asignacion asig3=new Asignacion(resultado.id, linea, columna, resultado);
                       asig3.ejecutar(ent);
                       return res2;
                       
               }
                
           
                 System.out.println("Error Semantico: No se puede aumentar un id de Tipo: "+resultado.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nSolo se puede usar el incremento con Id's de Tipo: "+resultado.tipo.tipo, linea, columna);
            lista_errores.add(error);

        
            
        return null;
    }
  
    
}
