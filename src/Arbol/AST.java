/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Arbol.Entorno.Entorno;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class AST {
   public LinkedList<Nodo> lista_instrucciones;
   public Entorno tablaGlobal;
    public AST(LinkedList<Nodo> lista_instrucciones){
        this.lista_instrucciones=lista_instrucciones;
        tablaGlobal=new Entorno(null);
    }
    public void Ejecutar(){
        for (Nodo it:lista_instrucciones) {
            if(it instanceof Instruccion){
            Instruccion instruccion = (Instruccion) it;
            instruccion.ejecutar(tablaGlobal);
            }else if(it instanceof Expresion){
               ((Expresion) it).getValor(tablaGlobal);
            }
        }
    }
    
}
