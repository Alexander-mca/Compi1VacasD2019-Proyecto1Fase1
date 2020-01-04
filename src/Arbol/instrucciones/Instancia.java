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
import Arbol.Objetos.CrearObjeto;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class Instancia extends Instruccion{
public String id,tipo;

    public Instancia(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
public Expresion valor;


    public Instancia(String id, String tipo, Expresion valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
     
    }
    @Override
    public Object ejecutar(Entorno ent) {        
        String tipoC=((CrearObjeto)valor).tipoC;
        Objeto exp=(Objeto)valor.getValor(ent);
        if(valor!=null){
           
            if(tipo.equals(tipoC)){             
            
               
            ent.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.objeto),exp), linea, columna,"El objeto ");
            
            }
        }else{
            Objeto obj=null;
            
            ent.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.objeto),obj), linea, columna, "El objeto ");
        }
       return null;
    }
    
}
