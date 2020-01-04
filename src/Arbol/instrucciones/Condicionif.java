/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Condicionif extends Instruccion{

    Expresion exp;
    Bloque bloque;
    public Condicionif(Expresion exp,Bloque bloque,int linea,int columna){
        this.exp=exp;
     this.bloque=bloque;
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno actual=new Entorno(ent);
        Expresion val=exp.getValor(actual);
       
        Boolean ejecutado=false;
        if(Boolean.parseBoolean(val.valor.toString())){
            ejecutado=true;
         Object obj= bloque.ejecutar(actual);
            if(obj instanceof Break || obj instanceof Continue){
                //a++; 
                return obj;
            }else if(obj instanceof Expresion){
                
            }
        }else{
//             System.out.println("Error Semantico: La condicion no es de tipo Booleano.  Tipo: "+val.tipo+" Linea: "+linea +" Columna: "+columna);
//            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nLa condicion no es de tipo Booleano.  Tipo:"+val.tipo, linea, columna);
//            lista_errores.add(error);
        }
        return ejecutado;
    }
    
}
