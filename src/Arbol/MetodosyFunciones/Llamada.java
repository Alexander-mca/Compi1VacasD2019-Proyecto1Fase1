/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.MetodosyFunciones;

import Arbol.Arreglos.Arreglo;
import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Arbol.Nodo;
import Arbol.instrucciones.Asignacion;
import Arbol.instrucciones.Bloque;
import Arbol.instrucciones.Declaracion;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */

public class Llamada extends Expresion{
public String nombre;
//public Expresion expresion;
public LinkedList<Nodo> parametros;
public Llamada(String nombre,LinkedList<Nodo> parametros){
    this.nombre=nombre;
    this.parametros=parametros;
    this.nombre+="#";
}
    @Override
    public Expresion getValor(Entorno ent) {
        String name=nombre;
//       if(!name.contains("#")){        
//        name+="#";
//       }
         if(parametros!=null){
            for (Nodo decl:parametros) {               
               Expresion exp=((Expresion)decl).getValor(ent);               
                name+=exp.tipo.tipo;               
            }
        }
         //se busca el metodo en el entorno global
         Entorno nuevo1=new Entorno(ent);
         nuevo1.Global=ent.Global;
        Simbolo sim=nuevo1.Global.buscar(name, linea, columna, "El metodo ");
        if(sim!=null){
        if(sim.tipo.tipo.equals(Tipo.EnumTipo.metodo) || sim.tipo.tipo.equals(Tipo.EnumTipo.constructor)){   
            MDatos metodo=(MDatos)sim.valor;
            int cont=0;
            //se asignan las expresiones a los parametros del metodo
            if(metodo.parametros!=null){
            for(Nodo ids:metodo.parametros){
                Declaracion decl=(Declaracion)ids;
                Expresion exp=((Expresion)parametros.get(cont)).getValor(ent);
                Asignacion asig=new Asignacion(decl.id,linea,columna,exp);
                asig.ejecutar(metodo.entorno);
                cont++;
            }
            }
            //se ejecutan las instrucciones del metodo
            Entorno nuevo=new Entorno(metodo.entorno);
            nuevo.Global=metodo.entorno.Global;
            
            if(metodo.instrucciones!=null){
            Object retorno=metodo.instrucciones.ejecutar(nuevo);
            
            //se verifica si el metodo posee un return
            
            if(retorno instanceof Return){
                return null;
            }else if(retorno instanceof Expresion){
                Expresion exp=(Expresion)retorno;
                lista_errores.add(new CError("Semantico","El "+sim.tipo.tipo+" "+name+" posee un return, pero no deberia tener una expresion.",exp.linea,exp.columna));
            }
            }
        }else if(sim.tipo.tipo.equals(Tipo.EnumTipo.funcion)){
            //se busca la funcion en el entorno 
//            Simbolo simf=ent.Global.buscar(nombre, linea, columna, "La Funcion ");
//            if(simf!=null){
                DataF function=(DataF)sim.valor;
                int cont=0;
                //se asignan las expresiones a los parametros de la funcion
             if(function.parametros!=null){
                for(Nodo idPar:function.parametros){
                    Declaracion decl=(Declaracion)idPar;
                    Expresion exp=((Expresion)parametros.get(cont)).getValor(ent);
                    Asignacion asig=new Asignacion(decl.id,linea,columna,exp);
                    asig.ejecutar(function.entorno);
                    cont++;
                }
             }
                //se ejecutan las instrucciones de la funcion
                function.entorno.Global=nuevo1.Global;
                Entorno nuevo=new Entorno(function.entorno);
                nuevo.Global=function.entorno.Global;
                if(function.instrucciones!=null){
                    
                Object retorno=((Expresion)function.instrucciones.ejecutar(nuevo)).valor;
                //verificar que la funcion tenga un retorno
                
                    
                    if(retorno instanceof Arreglo){
                        Arreglo arr=(Arreglo)retorno;
                       Expresion expresion=VerificarTipos(arr,function);
                       return expresion;
                        
                        
                    }else if(retorno instanceof Expresion){
                    Expresion expresion=(Expresion)retorno;
                    String tipo1=expresion.tipo.tipo.toString().toLowerCase();
                    String tipo2=function.tipoF.toLowerCase();
                    if(tipo1.equals(tipo2)){
                        return expresion;
                    }else{
                        lista_errores.add(new CError("Semantico","En La función "+name+", la expresion del return no es del mismo tipo que la funcion. Tipo Funcion: "+tipo2+" Tipo expresion de Return: "+tipo1,expresion.linea,expresion.columna));
                    }
                    }else{
                        Return ret=(Return)retorno;
                        lista_errores.add(new CError("Semantico","El return de la funcion "+name+" no posee una expresion.",ret.linea,ret.columna));
                    }
                }else{
                    lista_errores.add(new CError("Semantico","La funcion "+name+" no posee un return.",linea,columna));
                }
            
        
        }
//        else if(sim.tipo.tipo.equals(Tipo.EnumTipo.constructor)){
//            //en caso de que sea un constructor
//            
//        }
        }
       return null;
    }
    private Arreglo VerificarTipos(Arreglo arr,DataF function){
                     
                        String tipo1=arr.getTipo(arr.valor).toLowerCase();
                        if(function.tipoF!=null){
                            String tipo2=function.tipoF.toLowerCase();
                            if(tipo1.equals(tipo2)){
                                int prof1=arr.Profundidad(arr.valor, arr.cont);
                                int prof2=function.profundidad.size();
                                if(prof1==prof2){
                                    return arr;
                                }else{
                                    //marcar error de profundidad
                                    ErrorProfundidad(prof1,prof2,tipo2);
                                }
                            }else{
                                //marcar error de tipos
                                lista_errores.add(new CError("Semantico","Error de tipos en los arreglos. "+tipo1+"!="+tipo2,linea,columna));
                                
                            }
                        }
//                        else{
//                        String tipo2=function.tipoF;
//                        if(tipo2.equals(tipo1)){
//                             int prof1=arr.Profundidad(arr.valor, arr.cont);
//                                int prof2=function.profundidad.size();
//                                if(prof1==prof2){
//                                    return arr;
//                                }else{
//                                    //marcar error de profundidad
//                                    ErrorProfundidad(prof1,prof2,tipo2);
//                                }
//                        }else{
//                            //marcar error de tipos
//                              lista_errores.add(new CError("Semantico","Error de tipos en los arreglos. "+tipo1+"!="+tipo2,linea,columna));
//                        }
//                        }
                        
        return null;
    }
    private void ErrorProfundidad(int prof1, int prof2, String tipo2) {
        String p1 = "";
        String p2 = "";
        for (int i = 0; i < prof1; i++) {
            p1 += "[]";
        }
        for (int j = 0; j < prof2; j++) {
            p2 += "[]";
        }
        lista_errores.add(new CError("Semantico", "La profundidad del arreglo del return de Tipo: " + tipo2 + " no es igual a la Profundidad del tipo de la Funcion Tipo:" + tipo2 + "." + tipo2 + p1 + "!=" + tipo2 + p2, linea, columna));
    }
}
