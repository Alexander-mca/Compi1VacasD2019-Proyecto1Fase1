/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import static Interfaz.Editor.*;
/**
 *
 * @author alexa
 */
public class Imprimir extends Instruccion{
    Expresion valor;
    boolean salto;//true = println, false = print
    
    public Imprimir(Expresion valor,boolean salto){
     this.valor=valor;
     this.salto=salto;
    }
    
    @Override
    public Object ejecutar(Entorno e){
       
        
           Expresion resultado=valor.getValor(e); 
        
        
        
        String auxiliar="";
        if(salto){
            auxiliar="\n";
        }    
        
        
        
                if(resultado!=null && resultado.tipo.tipo!=Tipo.EnumTipo.error){
                    String val=resultado.valor.toString().replace("\\n", "\n");
                    String val1=val.replace("\\r", "\r");
                    String val2=val1.replace("\\t", "\t");
                    String salida=consola.getText();
                           salida +=val2+auxiliar;
                          consola.setText(salida);
//                          entrada++;
                    
                    //imprimir en consola de interfaz           
                }
        

                return null;
    }
}
