/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;
import Arbol.Entorno.Entorno;
/**
 *
 * @author alexa
 */
public abstract class Instruccion extends Nodo{
    public abstract Object ejecutar(Entorno ent);
    
}
