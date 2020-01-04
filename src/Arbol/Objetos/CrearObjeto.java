/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Objetos;

import Arbol.*;
import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.MetodosyFunciones.Constructor;
import Arbol.MetodosyFunciones.Funcion;
import Arbol.MetodosyFunciones.Llamada;
import Arbol.MetodosyFunciones.Metodo;
import Arbol.instrucciones.Clase;
import Arbol.instrucciones.Declaracion;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class CrearObjeto extends Expresion{
public String tipoC;

   public LinkedList<Nodo> parametros;

    public CrearObjeto( String tipoC, LinkedList<Nodo> parametros) {
        
        this.tipoC = tipoC;
        this.parametros = parametros;
    }

    public CrearObjeto(String tipoC) {
        this.tipoC = tipoC;
    }

 
    @Override
    public Expresion getValor(Entorno ent) {
        Entorno nuevo=new Entorno(null);
        nuevo.Global=nuevo;
        tipoC+="@";
        Simbolo sim=ent.buscar(tipoC, linea, columna,"La clase");
        
        LinkedList<Nodo> instrucciones=(LinkedList)sim.valor;
        for(Nodo ins:instrucciones){
            if(ins instanceof Metodo || ins instanceof Funcion){
                
            
                ((Instruccion)ins).ejecutar(nuevo);
            }
        }
        for(Nodo ins:instrucciones){
            if(ins instanceof Declaracion){
                ((Instruccion)ins).ejecutar(nuevo);
            }
        }
        Entorno entthis=new Entorno(null);
        entthis.Global=nuevo.Global;
        for (Nodo ins:instrucciones) {
            if(ins instanceof Constructor){
                ((Instruccion)ins).ejecutar(nuevo);
            }
                    
        }
//        nuevo.insertar("this", new Simbolo(new Tipo(Tipo.EnumTipo.objeto),), linea, columna, "El objeto");
        Objeto varthis=new Objeto(entthis,tipoC);
        nuevo.insertar("this", new Simbolo(new Tipo(Tipo.EnumTipo.objeto),varthis), linea, columna, "El objeto ");
        Llamada llama=new Llamada(tipoC,parametros);
        Entorno new1=new Entorno(nuevo);
        new1.Global=nuevo;
        llama.getValor(new1);
        nuevo.tabla.remove("this");
        Objeto obj=new Objeto(nuevo,tipoC);
        
        
        return obj;
    }
  
    
}
