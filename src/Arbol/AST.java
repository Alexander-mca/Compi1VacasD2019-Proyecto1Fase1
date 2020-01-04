/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;
import Arbol.MetodosyFunciones.*;
import Arbol.instrucciones.Bloque;
import Arbol.instrucciones.Clase;
import Arbol.instrucciones.Declaracion;
import Arbol.instrucciones.Importar;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author alexa
 */
public class AST {

    public LinkedList<Nodo> lista_instrucciones;
    public Entorno tablaGlobal;
//   public Entorno Global;

    public AST(LinkedList<Nodo> lista_instrucciones) {
        this.lista_instrucciones = lista_instrucciones;
        tablaGlobal = new Entorno(null);

    }

    public void Ejecutar() {
        for (Nodo it : lista_instrucciones) {
            if (it instanceof Importar) {
                Importar imp = (Importar) it;
                imp.ejecutar(tablaGlobal);
            }
        }
        for (Nodo it : lista_instrucciones) {
            if (it instanceof Clase) {
                Clase cl = (Clase) it;
                if(!HasMain(cl)){
                cl.ejecutar(tablaGlobal);
                
                }else{
                    Entorno clas=new Entorno(tablaGlobal);
                clas.Global=clas;
                    LinkedList<Nodo> instrucciones = (LinkedList<Nodo>) cl.instrucciones;
                for(Nodo nd:instrucciones){
                    if(nd instanceof Metodo){
                        Metodo met=(Metodo)nd;
                        String nom=met.nombre.toLowerCase();
                        if(nom.equals("main") && met.parametros==null){
                            Entorno nuevo=new Entorno(clas);
                            nuevo.Global=clas;
                            met.instrucciones.ejecutar(nuevo);
                            break;
                        }
                    }
                }
                }
                
                }
        }
    }
    private boolean HasMain(Clase clase){
        
        Clase clas=clase;
        LinkedList<Nodo> instrucciones=clas.instrucciones;
         for(Nodo nd:instrucciones){
                    if(nd instanceof Metodo){
                        Metodo met=(Metodo)nd;
                        String nom=met.nombre.toLowerCase();
                        if(nom.equals("main") && met.parametros==null){
                            return true;
                        }
                    }
                }
        return false;
    }

}
