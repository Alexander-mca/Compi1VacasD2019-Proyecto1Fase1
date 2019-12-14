/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Contexto.TipoInstruccion;
import static Interfaz.Editor.lista_ciclos;

/**
 *
 * @author alexa
 */
public class For extends Instruccion {

    int veces = 1;
    Expresion valor2;
    Nodo valor3;
    Instruccion valor1;
    Bloque bloque;

    public For(Instruccion valor1, Expresion valor2, Nodo valor3, Bloque bloque) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
        this.bloque = bloque;
        lista_ciclos.add(TipoInstruccion.ciclo);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        boolean ejecutado = false;
        if (veces == 1) {
            lista_ciclos.add(TipoInstruccion.ciclo);
//        Expresion variable=
//valor1 declara o recupera el valor de la variable a usar en el for
            valor1.ejecutar(ent);
        }
        //validacion se encarga de realizar la validacion de la expresion en el for
        Expresion validacion = valor2.getValor(ent);
        
        if (validacion != null && Boolean.parseBoolean(validacion.valor.toString())) {
            for (Nodo instruccion : bloque.intruccion) {
                if (instruccion instanceof Instruccion) {
                    Object obj = ((Instruccion) instruccion).ejecutar(ent);
                    if (obj instanceof Break) {
                        ejecutado = true;
                        break;
//                  return null;

                    } else if (obj instanceof Continue) {
                        ejecutado = false;
                        break;
//                  return null;
                    }
                } else if (instruccion instanceof Expresion) {
                    ((Expresion) instruccion).getValor(ent);
                }
            }
            //valor3 aumenta o decrementa el valor de la variable
            if(valor3 instanceof Instruccion){
                ((Instruccion)valor3).ejecutar(ent);
        }else if(valor3 instanceof Expresion){
             ((Expresion)valor3).getValor(ent);
        }
            
        
       
        
            if (!ejecutado) {
                veces++;
                ejecutar(ent);
            }
        }

        return null;
    }

}
