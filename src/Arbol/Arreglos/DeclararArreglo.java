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
import Arbol.Expresiones.Literal;
import Arbol.Instruccion;
import Arbol.MetodosyFunciones.Llamada;
import Arbol.Nodo;
import java.util.LinkedList;
//import Arbol.Nodo;

/**
 *
 * @author alexa
 */
public class DeclararArreglo extends Instruccion{
LinkedList<String> profundidad;
LinkedList<Nodo> prof;
String idArreglo;
Expresion valores;
String tipoArreglo,Tipo2;


    public DeclararArreglo(LinkedList<String> profundidad, String id, String tipo, Expresion valores,int linea,int columna) {
        this.profundidad = profundidad;
     this.linea=linea;
     this.columna=columna;
        this.idArreglo = id;
        this.tipoArreglo = tipo;
        this.valores = valores;
    }

    public DeclararArreglo(LinkedList<String> profundidad,LinkedList<Nodo> prof, String id, String tipo,String tipo2,int linea,int columna) {
        this.profundidad = profundidad;
        this.idArreglo = id;
        this.tipoArreglo = tipo;
        this.prof=prof;
        this.Tipo2=tipo2;
        this.linea=linea;
        this.columna=columna;
    }

    @Override
    public Object ejecutar(Entorno ent) {
//       Expresion exp[]=(Expresion[]) ((CrearArreglo)valores).ejecutar(ent);
//       if(exp!=null){
//           Arreglo arreglo=new Arreglo(new Entorno(null));
//           LinkedList<Expresion> expresion=new LinkedList<>();
//           for(Expresion expre:exp){
//               expresion.add(expre);
//           }
//           arreglo.ArregloCrear(expresion);
//           arreglo.entorno.insertar("size",new Simbolo(new Tipo(Tipo.EnumTipo.entero),exp.length), linea, columna, id);
//           ent.insertar(id, new Simbolo(new Tipo(Tipo.EnumTipo.objeto),arreglo), linea, columna, "El arreglo ");
//           
//       }
        if(valores instanceof CrearArreglo){
            //declaracion obteniendo el dato de una funcion
            Arreglo resultado=(Arreglo) valores.getValor(ent);
            if(resultado!=null){
                String tipo2=resultado.getTipo((LinkedList)resultado.valor);
                if(tipoArreglo.toLowerCase().equals(tipo2)){
                int pr1=resultado.Profundidad((LinkedList)resultado.valor, resultado.cont);
                int pr2=profundidad.size();
                if(pr1==pr2){
                    ent.insertar(idArreglo,new Simbolo(new Tipo(Tipo.EnumTipo.objeto),resultado),linea,columna,"El arreglo");
                }else{
                    //marcar error de profundidad
                }
                }else{
                    //marcar error de tipo de dato
                }
            }
        }else{
       if (valores != null) {
            Object exp =  valores.getValor(ent);
            Arreglo arreglo = (Arreglo) exp;
            int prof1=arreglo.Profundidad(arreglo.valor, arreglo.cont);
            int prof2=profundidad.size();
            String tip=arreglo.getTipo(arreglo.valor);
            String tip2=tipoArreglo.toLowerCase();
            if(tip2.equals(tip)){
            if(prof1==prof2 ){
            ent.insertar(idArreglo, new Simbolo(new Tipo(Tipo.EnumTipo.objeto), arreglo), linea, columna, "El Arreglo ");
            }else{
                //marcar error de profundidad de arreglos
            }
            }else{
              //marcar error por tipo de dato
            }
        }else{
           //otro tipo de declaracion, esta vez con new
           if(Tipo2!=null){
           if(tipoArreglo.equals(Tipo2)){
               if(profundidad.size()==prof.size()){
                   Arreglo arreglo=CreateArray(prof.size(),0);
                   ent.insertar(idArreglo,new Simbolo(new Tipo(Tipo.EnumTipo.objeto),arreglo), linea, columna, "El Arreglo");
               }else{
                   //marcar error de profundidad
               }
           }else{
               //marcar error de tipo de dato
           }
           }
       }
    }
       return null;
    }
    private Arreglo CreateArray(int profundidad,int cont) {
        Arreglo arr = null;
        if (profundidad==1) {
            arr = new Arreglo(new Entorno(null),this.tipoArreglo);
            String data=(((Expresion) this.prof.get(cont)).valor.toString());
              int  cont1 = Integer.parseInt(data);
            LinkedList<Expresion> list = new LinkedList<>();
            for (int x = 0; x < cont1; x++) {
                list.add(null);
            }
            arr.ArregloCrear(list);
            arr.entorno.insertar("size", new Simbolo(new Tipo(Tipo.EnumTipo.entero),list.size()), linea, columna, "La Variable ");
            return arr;
        } else if(profundidad>1) {
//            cont=0;
//            for (int x = 0; x < profundidad; x++) {
                arr = new Arreglo(new Entorno(null),this.tipoArreglo);
                String data=(((Expresion) this.prof.get(cont)).valor.toString());
                int cont1 = Integer.parseInt(data);
                LinkedList<Expresion> list = new LinkedList<>();
                cont++;
                for (int y = 0; y < cont1; y++) {
                   
                    
                    Arreglo arreglo=CreateArray(profundidad-1,cont);    
                    list.add(arreglo);
                }
                arr.ArregloCrear(list);
                arr.entorno.insertar("size", new Simbolo(new Tipo(Tipo.EnumTipo.entero),list.size()), linea, columna, "La Variable ");
//            }
        }
        return arr;
    }

}
