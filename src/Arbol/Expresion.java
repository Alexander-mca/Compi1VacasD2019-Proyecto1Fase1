/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;


import Arbol.Entorno.Tipo;
import Arbol.Entorno.Entorno;

/**
 *
 * @author alexa
 */
public abstract class Expresion extends Nodo{
    public Tipo tipo;
    public Object valor;
    public String id;
    public abstract Expresion getValor(Entorno ent);
}
