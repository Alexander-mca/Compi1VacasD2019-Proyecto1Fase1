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
public class Metodo extends Instruccion{
public LinkedList<Nodo> parametros;
public Bloque instrucciones;
public String nombre;
public Tipo tipo;

    
public Metodo(LinkedList<Nodo> parametros,Bloque instrucciones, String nombre) {
        this.parametros = parametros;
        this.instrucciones=instrucciones;
        this.nombre = nombre;
        tipo=new Tipo(Tipo.EnumTipo.tvoid);
    }
    @Override
    public Object ejecutar(Entorno ent) {
       
        
        Entorno actual=new Entorno(ent);
        actual.Global=ent.Global;
        nombre+="#";
        
        if(parametros!=null){
            for (Nodo decl:parametros) {
                Declaracion simb=(Declaracion)decl;
                nombre+=simb.tipo.tipo;
                simb.ejecutar(actual);
               
                
                //marcar error en caso de que venga nulo
            }
        }
            
//        if(instrucciones!=null){
//           Object obj=instrucciones.ejecutar(actual);
//           if(obj instanceof Return){
//               
//           }
//        }
        MDatos data=new MDatos(actual,parametros,instrucciones);
        ent.Global.insertar(nombre, new Simbolo(new Tipo(Tipo.EnumTipo.metodo),data), linea, columna, "El metodo ");
      
       
        return data;
    }
    
}
