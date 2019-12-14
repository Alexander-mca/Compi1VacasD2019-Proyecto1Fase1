/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.Logicas;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Literal;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Or extends Expresion{

    String operacion;
    Expresion izq,der;
    public Or(int linea,int columna,Expresion izq,Expresion der){
        this.operacion="||";
        this.izq=izq;
        this.der=der;
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1=izq.getValor(ent);
        Expresion res2=der.getValor(ent);
        if(res1!=null && res2!=null){
            switch(res1.tipo.tipo){
                case booleano:
                    switch(res2.tipo.tipo){
                        case booleano:
                            if(Boolean.parseBoolean(res1.valor.toString())){
                                return new Literal(res2.tipo,true);
                            }else{
                                if(Boolean.parseBoolean(res2.valor.toString())){
                                 return new Literal(res2.tipo,true);
                                }else{
                                     return new Literal(res2.tipo,false);
                                }
                            }
                    }
                    break;
            }
            System.out.println("Error Semantico: No es posible comparar los valores "+res1.tipo.tipo+" Tipo: "+tipo.tipo+" / "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nNo se puede comparar un "+res1.tipo.tipo+" con un "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
        }
        return null;
    }
    
}
