/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Arreglos;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Id;
import Arbol.Nodo;
import Arbol.Objeto;
import Arbol.Objetos.Acceso;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class AccesoArreglo extends Expresion{
    public LinkedList<Nodo> prof;
    public String idArreglo;
    public Expresion acceso;
    public AccesoArreglo(LinkedList<Nodo> prof, String idArreglo,Expresion acceso) {
        this.prof = prof;
        this.idArreglo = idArreglo;
        this.acceso=acceso;
    }
    
    @Override
    public Expresion getValor(Entorno ent) {
       Id exp=new Id(idArreglo,linea,columna);
       Object obj=exp.getValor(ent).valor;
      
       if(obj instanceof Arreglo){
           Arreglo arr=(Arreglo)obj;
           Object obj1=BuscarEnArreglo(arr);
           if(obj1 instanceof Objeto){
               Objeto ob1=(Objeto)obj1;
               if(acceso!=null){
                   Expresion ob2=acceso.getValor(ob1.entorno);
                   return ob2;
               }else{
                   
               }
           }else if(obj1 instanceof Expresion){
               return (Expresion)obj1;
           }
       }
       return null;
    }
    private Object BuscarEnArreglo(Arreglo arr){
        Object obj=null;
        for (int i = 0; i <this.prof.size(); i++) {
           Expresion ins=(Expresion)this.prof.get(i);
           if(ins.tipo.tipo.equals(Tipo.EnumTipo.entero)){
            int x=Integer.parseInt(((Expresion)ins).valor.toString());
            if(x==i){
                Object obj1=((LinkedList<Nodo>)arr.valor).get(x);
            if(obj1 instanceof Arreglo){
               obj=BuscarEnArreglo((Arreglo)obj1);
               break;
            }else{
                return obj1;
                
            }
            }
           }else{
               //marcar error de que solo pueden venir enteros
           }
        }
        return obj;
    }
}
