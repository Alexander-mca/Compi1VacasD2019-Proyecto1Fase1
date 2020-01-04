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
public class Funcion extends Instruccion{
//public Tipo tipo;
String tipoF;
LinkedList<String> profundidad;
public String id;
public LinkedList<Nodo> parametros;
public Bloque instrucciones;
    public Funcion(String tipoF, LinkedList<String> profundidad, String id, LinkedList<Nodo> parametros, Bloque instrucciones) {
        this.tipoF = tipoF;
        this.profundidad = profundidad;
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }



    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno actual=new Entorno(ent);
        actual.Global=ent.Global;
        id+="#";
       
        if(parametros!=null){
            for (Nodo decl:parametros) {
                Declaracion simb=(Declaracion)decl;                             
                id+=simb.tipo.tipo;
                simb.ejecutar(actual);
                
            }
        } 
        DataF funtion=new DataF(actual,tipoF,profundidad,parametros,instrucciones);
//        if(profundidad!=null){
//            if(tipoF!=null){
//                funtion=new DataF(actual,tipoF,profundidad,parametros,instrucciones);
//            }else{
//                funtion=new DataF(actual,tipo);
//            }
//        }else if(tipoF!=null){
//                funtion=new DataF(actual,parametros,instrucciones,tipoF);
//        }else{
//            funtion=new DataF(actual,parametros,instrucciones,tipo);
//        }
        ent.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.funcion),funtion), linea, columna, "La Funcion ");
       return null;
    }
    
}
