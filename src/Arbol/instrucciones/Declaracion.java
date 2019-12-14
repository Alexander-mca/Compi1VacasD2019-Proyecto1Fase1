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

/**
 *
 * @author alexa
 */
public class Declaracion extends Instruccion{
    Tipo tipo;
   String id;
    public Expresion valor;

    public Declaracion(Tipo tipo,String id, Expresion valor,int linea,int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.columna=columna;
                this.linea=linea;
                
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
            System.out.println("Error Semantico: El tipo de valor que se le quiere asignar a la variable "+id+" Tipo: "+tipo.tipo+" / "+resultado.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
        }else{
            switch(tipo.tipo){
                case entero:
                    ent.insertar(id,new Simbolo(tipo,0),linea,columna,"La variable");
                    break;
                case caracter:
                    ent.insertar(id,new Simbolo(tipo,'\0'),linea,columna,"La variable");
                    break;
                case booleano:
                    ent.insertar(id,new Simbolo(tipo,false),linea,columna,"La variable");
                    break;
                case doble:
                    ent.insertar(id,new Simbolo(tipo,0.0),linea,columna,"La variable");
                    break;
                case cadena:
                    ent.insertar(id,new Simbolo(tipo,""),linea,columna,"La variable");
                    break;
                    
                            }
        }
        return null;
    }
    
    
}
