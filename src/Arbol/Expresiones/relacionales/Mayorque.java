/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Expresiones.relacionales;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Literal;
import Interfaz.CError;
import static Interfaz.Editor.lista_errores;

/**
 *
 * @author alexa
 */
public class Mayorque extends Expresion {

    String operacion;
    Expresion hijo1, hijo2;

    public Mayorque(int linea, int columna, Expresion hijo1, Expresion hijo2) {
        this.operacion = ">";
        this.hijo1 = hijo1;
        this.hijo2 = hijo2;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public Expresion getValor(Entorno ent) {
        Expresion res1 = hijo1.getValor(ent);
        Expresion res2 = hijo2.getValor(ent);
        if (res1 != null && res2 != null) {
            switch (res1.tipo.tipo) {
                case entero:
                    int a = Integer.parseInt(res1.valor.toString());
                    switch (res2.tipo.tipo) {
                        case entero:

                            int b = Integer.parseInt(res2.valor.toString());
                            return Validar(a, b);
                        case doble:
                            double be = Double.parseDouble(res2.valor.toString());
                            return Validar(a, be);
                        case caracter:
                            int ascii = (int) res2.valor.toString().charAt(0);
                            return Validar(a, ascii);

                    }
                    break;
                case doble:
                   double var1=Double.parseDouble(res1.valor.toString());
                   switch(res2.tipo.tipo){
                       case doble:
                           double var2=Double.parseDouble(res2.valor.toString());
                           return Validar(var1,var2);
                       case  entero:
                           int va1=Integer.parseInt(res2.valor.toString());
                           return Validar(var1,va1);
                       case caracter:
                           int ascii=(int)res2.valor.toString().charAt(0);
                           return Validar(var1,ascii);
                           
                   }
                    break;
                case caracter:
                    int x=(int)res1.valor.toString().charAt(0);
                    switch(res2.tipo.tipo){
                        case caracter:
                            int y=(int)res2.valor.toString().charAt(0);
                            return Validar(x,y);
                        case entero:
                            int b=Integer.parseInt(res2.valor.toString());
                            return Validar(x,b);
                        case doble:
                            double r=Double.parseDouble(res2.valor.toString());
                            return Validar(x,r);
                    }
                    break;
            }
          System.out.println("Error Semantico: No es posible comparar los valores "+res1.tipo.tipo+" Tipo: "+tipo.tipo+" / "+res2.tipo.tipo+" Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\nNo se puede comparar un "+res1.tipo.tipo+" con un "+res2.tipo.tipo, linea, columna);
            lista_errores.add(error);
        }
        return null;
    }

    private Literal Validar(double a, double b) {
        if (a > b) {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), true);
        } else {
            return new Literal(new Tipo(Tipo.EnumTipo.booleano), false);
        }
    }

}
