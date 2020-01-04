/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.MetodosyFunciones.Llamada;
import Arbol.Nodo;
import Arbol.Objeto;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Declaracion extends Instruccion{
   public  Tipo tipo;
   public String id;
    public Expresion valor;
    LinkedList<Nodo> parametros;
    

    


    public Declaracion(Tipo tipo, String id, Expresion valor, int linea, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.columna = columna;
        this.linea = linea;

    }
    
    public Declaracion(Tipo tipo, String id,int linea,int columna){
       this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.columna=columna;
        this.linea=linea;
    }
    @Override
    public Object ejecutar(Entorno ent){
        if(valor!=null){
            Expresion resultado=valor.getValor(ent);
           
            Simbolo simbolo;
            switch(tipo.tipo){
                case entero:
                    switch(resultado.tipo.tipo){
                        case entero:
                            
                            simbolo=new Simbolo(tipo,resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                            
                            return null;
                        case caracter:
                            int ascii=(int)resultado.valor.toString().charAt(0);
                            simbolo=new Simbolo(tipo,ascii);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                            
                            return null;
                            
                    }
                    break;
                case doble:
                     switch(resultado.tipo.tipo){
                        case doble:
                            simbolo=new Simbolo(tipo,resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                           
                            return null;
                        case entero:
                           
                            simbolo=new Simbolo(tipo,(double)resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                             
                            return null;
                            
                    }
                    break;
                case caracter:
                     switch(resultado.tipo.tipo){
                        case caracter:
                            simbolo=new Simbolo(tipo,resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                          
                            return null;
                        case entero:
                            
                            simbolo=new Simbolo(tipo,(char)resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                           
                            return null;
                            
                    }
                    break;
                case booleano:
                     switch(resultado.tipo.tipo){
                        case booleano:
                            simbolo=new Simbolo(tipo,resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                             
                            return null;
                        
                            
                    }
                    break;
                case cadena:
                     switch(resultado.tipo.tipo){
                        case cadena:
                            simbolo=new Simbolo(tipo,resultado.valor);
                            ent.insertar(id, simbolo, linea, columna, "La Variable");

                            return null;
                        case caracter:
                            
                            simbolo=new Simbolo(tipo,String.valueOf(resultado.valor));
                            ent.insertar(id, simbolo, linea, columna, "La Variable");
                             
                            return null;
                            
                    }
                    break;
                
            }
            
            System.out.println("Error Semantico: El tipo de valor que se le quiere asignar a la variable "+id+" Tipo: "+tipo.tipo+" un valor Tipo: "+resultado.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            lista_errores.add(new CError("Semantico","No se le puede asignar a la variable '"+id+"' un valor Tipo:"+resultado.tipo.tipo,linea,columna));
        }else{
            if(tipo!=null){
            switch(tipo.tipo){
                case entero:
                  
                    ent.insertar(id,new Simbolo(tipo,0),linea,columna,"La variable");
                    return null;
                case caracter:
                    ent.insertar(id,new Simbolo(tipo,'\0'),linea,columna,"La variable");
                    return null;
                case booleano:
                    ent.insertar(id,new Simbolo(tipo,false),linea,columna,"La variable");
                    return null;
                case doble:
                    ent.insertar(id,new Simbolo(tipo,0.0),linea,columna,"La variable");
                    return null;
                case cadena:
                    ent.insertar(id,new Simbolo(tipo,""),linea,columna,"La variable");
                    return null;
                    
                            }
            }
        }
        
        
        return null;
    }
    
        
    


    
}