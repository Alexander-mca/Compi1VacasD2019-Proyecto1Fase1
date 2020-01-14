/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Objetos;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Id;
import Arbol.Instruccion;
import Arbol.Objeto;
import static Interfaz.Editor.lista_errores;
import Interfaz.CError;
import Arbol.instrucciones.Asignacion;

/**
 *
 * @author alexa
 */
public class AsignarAcceso extends Instruccion{
public String idObj,idVar;
public Expresion valor;

    public AsignarAcceso(String idObj, String idVar, Expresion valor,int linea,int columna) {
        this.idObj = idObj;
        this.idVar = idVar;
        this.valor = valor;
        this.linea=linea;
        this.columna=columna;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        String rthis=idObj;
       Simbolo sim;
       if(rthis.toLowerCase().equals("this")){
           idObj=rthis.toLowerCase();
           sim=ent.Global.buscar(idObj, linea, columna, "El objeto ");
       }else{
          sim=ent.Global.buscar(idObj, linea, columna, "El objeto ");
          if(sim==null){
       sim = ent.buscar(idObj, linea, columna, "El objeto ");
          }
       }
     
       if (sim.tipo.tipo.equals(Tipo.EnumTipo.objeto)) {
            Objeto obj = (Objeto) sim.valor;
            Entorno entObj = obj.entorno;
            AsignaAcceso(entObj.Global,ent);
//            Expresion exp=null;
                            
//                Id idVariable = new Id(idVar, linea, columna);
//                 exp = idVariable.getValor(entObj);                
//                 Asignacion asig=new Asignacion(idVar, linea, columna, valor);
//                 asig.ejecutar(ent);
        
        } else{
           lista_errores.add(new CError("Semantico","La variable '"+idObj+"' no es un objeto. No se le puede asignar valores primitivos.",linea,columna));
       } 
//marcar error si la variable no es un objeto
       return null;
    }
    private void AsignaAcceso(Entorno objeto,Entorno exp){
         Simbolo sim=objeto.buscar(idVar,linea,columna,"La variable");
        if(sim!=null){
            Expresion resultado=valor.getValor(exp);
            switch(sim.tipo.tipo){
                case entero:
                    switch(resultado.tipo.tipo){
                        case entero:
                            sim.valor=resultado.valor;
                          
                           return;
                        case caracter:
                            int ascii=(int)resultado.valor.toString().charAt(0);
                            sim.valor=ascii;
                           
                            return;
                    }
                    break;
                case doble:
                    switch(resultado.tipo.tipo){
                        case entero:
                            sim.valor=(double)resultado.valor;
                        
                            return;
                        case doble:
                            
                            sim.valor=resultado.valor;
                         
                            return;
                    }
                    
                    break;
                case caracter:
                    switch(resultado.tipo.tipo){
                        case caracter:
                            sim.valor=resultado.valor;
                       
                   
                            return;
                        case entero:
                            
                            sim.valor=(char)resultado.valor;
                            
                            return;
                    }
                    break;
                case booleano:
                     switch(resultado.tipo.tipo){
                        case booleano:
                            sim.valor=resultado.valor;                  
                   
                            return;
                     }
                    break;
                case cadena:
                     switch(resultado.tipo.tipo){
                        case caracter:
                            sim.valor=String.valueOf(resultado.valor);               
                   
                            return;
                        case cadena:                    
                      
                            sim.valor=resultado.valor;
                            
                           return;
                    }
                    break;
            }
            System.out.println("El tipo de dato que se le quiere asignar a la variable "+idVar+" es incorrecto"+" Tipo:"+sim.tipo.tipo+" !=" +resultado.tipo);
              lista_errores.add(new CError("Semantico","El tipo de dato que quiere asignar ala variable "+idVar+" es incorrecto"+" Tipo:"+sim.tipo.tipo+" !=" +resultado.tipo,linea,columna));          
        }
    }
    
}
