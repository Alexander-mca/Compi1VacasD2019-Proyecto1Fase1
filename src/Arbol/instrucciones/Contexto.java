/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Instruccion;

/**
 *
 * @author alexa
 */
public class Contexto {
    Instruccion instruccion;
    TipoInstruccion tipo;
    public Contexto(Instruccion instruccion, TipoInstruccion tipo){
        this.instruccion=instruccion;
        this.tipo=tipo;
    }
   
    public static boolean estoyDentroDe(TipoInstruccion tipo){
        boolean resultado=false;
        switch(tipo){
            case ciclo:
                resultado=true;
                break;
            case rswitch:
                resultado=true;
                break;
        }
        return resultado;
    }
    
    public enum TipoInstruccion{
        ciclo, rswitch
    }
}
