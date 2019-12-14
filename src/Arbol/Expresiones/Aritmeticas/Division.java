/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.Aritmeticas;
import Arbol.Entorno.Entorno;
import Arbol.Expresion;
import Arbol.Expresiones.Literal;
/**
 *
 * @author alexa
 */
public class Division extends Expresion{

    String operacion;
    Expresion hijo1,hijo2;
    public Division(int linea,int columna, Expresion hijo1, Expresion hijo2){
        this.operacion="/";
        this.linea=linea;
        this.columna=columna;
        this.hijo1=hijo1;
        this.hijo2=hijo2;
    }
            
    
    
    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1=hijo1.getValor(ent);
        Expresion res2=hijo2.getValor(ent);
        
        if(res1!=null && res2!=null){
            switch(res1.tipo.tipo){
                case entero:
                    if(!res2.valor.toString().equals("0")){
                    switch(res2.tipo.tipo){
                        case entero:
                            int a=Integer.parseInt(res1.valor.toString());
                            int b=Integer.parseInt(res2.valor.toString());
                            return new Literal( res1.tipo, a/b);
                            
                        case doble:
                           
                            return new Literal( res2.tipo, Double.parseDouble(res1.valor.toString()) / Double.parseDouble(res2.valor.toString()) );
                            
                    }
                    }else{
                        System.out.println("Error Semantico: No se puede dividir entre 0. Linea: "+res1.linea +"Columna: "+res1.columna);
                    }
                    break;
                case doble:
                    if(!res2.valor.toString().equals("0")){
                     switch(res2.tipo.tipo){
                        case entero:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) / Double.parseDouble(res2.valor.toString()));
                            
                        case doble:
                            
                            return new Literal( res1.tipo, Double.parseDouble(res1.valor.toString()) / Double.parseDouble(res2.valor.toString()) );
                            
                    }
                    }else{
                        System.out.println("Error Semantico: No se puede dividir entre 0. Linea: "+res1.linea +"Columna: "+res1.columna);
                    }
                    break;
                
            }
        }
       return null;
    }
    
}
