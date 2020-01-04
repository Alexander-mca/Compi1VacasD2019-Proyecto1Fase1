/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.MetodosyFunciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Bloque;
import Arbol.instrucciones.Declaracion;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Constructor extends Instruccion{
public String nombre;
public LinkedList<Nodo> parametros;
public Bloque instrucciones;

    public Constructor(String nombre, LinkedList<Nodo> parametros, Bloque instrucciones) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }


    @Override
    public Object ejecutar(Entorno ent) {
        Entorno actual=new Entorno(ent);
        actual.Global=ent.Global;
        nombre+="@#";
        MDatos constructor;
        if(parametros!=null){
        for(Nodo ins:parametros){
            Declaracion decl=(Declaracion)ins;
            nombre+=decl.tipo.tipo;
            decl.ejecutar(actual);
        }
        constructor=new MDatos(actual,parametros,instrucciones);
        }else{
            constructor=new MDatos(actual,instrucciones);
        }
    
        ent.Global.insertar(nombre, new Simbolo(new Tipo(Tipo.EnumTipo.constructor),constructor), linea, columna, "El Constructor ");
       return null;
    }
    
}
