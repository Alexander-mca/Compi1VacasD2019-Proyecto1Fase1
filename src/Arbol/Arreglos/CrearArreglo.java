/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Arreglos;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.Objeto;
import Arbol.Objetos.Acceso;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class CrearArreglo extends Expresion{
public LinkedList<Nodo> valores;
public int profundidad=0;
    public CrearArreglo(LinkedList<Nodo> valores) {
        this.valores = valores;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Expresion exp2[]=new Expresion[valores.size()];
        int cont=0;
       for(Nodo ins:valores){
           if(ins instanceof Expresion){
               if(cont==0){
               Expresion exp=((Expresion)ins).getValor(ent);
               exp2[cont]=exp;
               
               }else{
                   Object exp=((Expresion)ins).getValor(ent);
                   if(exp instanceof Arreglo){
                       Arreglo arr1=(Arreglo)exp2[cont-1];
                       Arreglo arr2=(Arreglo)exp;
                       Simbolo sim1=arr1.entorno.buscar("size", arr1.linea, arr1.columna, "La Variable");
                       Simbolo sim2=arr2.entorno.buscar("size", arr2.linea, arr2.columna, "La Variable");
                       //Acceso ac1=new Acceso(arr1.id,"size", arr1.linea, arr1.columna);
//                       Expresion ant=ac1.getValor(arr1.entorno);
                       int ant1=(int)sim1.valor;
//                       Acceso ac2=new Acceso(arr2.id,"size",arr2.linea,arr2.columna);
//                       Expresion act=ac2.getValor(arr2.entorno);
                       int act2=(int)sim2.valor;
                       if(ant1==act2){
                           exp2[cont]=arr2;
                       }else{
                           //error de profundidad
                       }
                   }else if(exp instanceof Objeto){
                    String tipo1=((Objeto)exp).tipoObj;
                    String tipo2=((Objeto)exp2[cont-1]).tipoObj;
                   if(tipo1.equals(tipo2)){
                       exp2[cont]=(Expresion)exp;
                   }else{
                       //marcar error de tipos
                   }
                   }
               }
           }else if(ins instanceof CrearArreglo){
               Object obj=((CrearArreglo) ins).getValor(ent);
               
               if(cont==0){
                   exp2[cont]=(Arreglo)obj;
               }else{
                   if(((Arreglo)obj).tipo.tipo.equals(exp2[cont-1].tipo.tipo)){
                       exp2[cont]=(Arreglo)obj;
                   }else{
                       //marcar error de tipos
                   }
               }
           }
           cont++;
       }
       Arreglo arreglo=new Arreglo(new Entorno(null),this.tipo.tipo.toString());
       if(exp2!=null){
       arreglo.ArregloCrear(generarArreglo(exp2));
       arreglo.entorno.insertar("size",new Simbolo(new Tipo(Tipo.EnumTipo.entero),exp2.length), linea, columna, "La Variable ");
//           arreglo.entorno.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.objeto),arreglo), linea, columna, "El arreglo ");
       }
       return arreglo;
           
    
    }
    public LinkedList<Expresion> generarArreglo(Expresion exp[]){
        if(exp!=null){
          
           LinkedList<Expresion> expresion=new LinkedList<>();
           for(Expresion expre:exp){
               expresion.add(expre);
           }          
           return expresion;
       }
        return null;
    }
}
