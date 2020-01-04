/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Arreglos;
import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Nodo;
import Arbol.Objeto;
import java.util.LinkedList;
/**
 *
 * @author alexa
 */
public class Arreglo extends Objeto{
    
//  public LinkedList<Expresion> elementos;
    public int cont=1;
    public Arreglo(Entorno entorno,String tipoObj) {
        super(entorno,tipoObj);
        
    }
    public void ArregloCrear(LinkedList<Expresion> elementos){
        this.valor=elementos;
    }
    public int Profundidad(Object valor,int cont1){
      
        LinkedList<Nodo> list = (LinkedList) valor;
        for (int a = 0; a < list.size(); a++) {
            Nodo ins = list.get(a);
            if (a == 0) {
                if (ins instanceof Arreglo) {

                    Profundidad((((Arreglo) ins).valor), cont1++);

                }else{
                    break;
                }

            }
//            else {
//                break;
//            }
        }
        return cont1;
    }
    
    public String getTipo(Object valor){
      
        LinkedList<Nodo> list = (LinkedList) valor;
        String tip=null;
        for (int a = 0; a < list.size(); a++) {
            Nodo ins = list.get(a);
            if (a == 0) {
                if (ins instanceof Arreglo) {

                    tip=getTipo((((Arreglo) ins).valor));
                  

                }else{
                    Expresion exp=(Expresion)ins;
                    tip=exp.tipo.tipo.toString();
                    return tip;
                }
                break;
            } 
//            else {
//                Expresion exp=(Expresion)ins;
//                return exp.tipo.tipo.toString();
//            }
        }
        if(tip!=null){
        return tip;
        }
        return null;
    }
}
