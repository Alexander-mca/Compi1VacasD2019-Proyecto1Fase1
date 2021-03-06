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
public class And extends Expresion{

    String operacion;
    Expresion izq,der;
    public And(int linea,int columna,Expresion izq,Expresion der){
        this.operacion="&&";
        this.izq=izq;
        this.der=der;
        this.linea=linea;
        this.columna=columna;
    }
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1 = izq.getValor(ent);
        Expresion res2 = null;
        if (res1.valor != null) {
            switch (res1.tipo.tipo) {
                case booleano:
                    if (Boolean.parseBoolean(res1.valor.toString())) {
                        res2 = der.getValor(ent);
                        if (Boolean.parseBoolean(res2.valor.toString())) {
                            return new Literal(res2.tipo, true);
                        } else {
                            return new Literal(res2.tipo, false);
                        }
                    } else {
                        return new Literal(res1.tipo, false);

                    }

            }
            System.out.println("Error Semantico: No es posible comparar los valores  Tipo: " + res1.tipo.tipo + " && " + res2.tipo.tipo + " Linea: " + linea + " Columna: " + columna);
            CError error = new CError("Semantico", "Opción incorrecta:\nNo se puede comparar. " + res1.tipo.tipo + " && " + res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
            return new Literal(new Tipo(Tipo.EnumTipo.error), "@Error@");
        }
        return null;
    }
    
}
