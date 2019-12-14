/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.AST;
import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Instruccion;
import Interfaz.CError;
import Interfaz.Editor;
import static Interfaz.Editor.lista_errores;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class Importar extends Instruccion{
    Expresion exp;
    public Importar(Expresion exp){
        this.exp=exp;
        
    }
            

    @Override
    public Object ejecutar(Entorno ent) {
        //enlazar los archivos, generar un AST  [archivo]-->[archivo]-->null
        Expresion resultado=exp.getValor(ent);
        if(resultado!=null){
            try{
            File file=new File(exp.valor.toString());
           
            String ruta=file.getAbsolutePath();
            
            String texto="";
                String aux="";
                if(ruta.endsWith("NM")){
                   FileReader archivos=new FileReader(file);
                   BufferedReader lee=new BufferedReader(archivos);
                   while((aux=lee.readLine())!=null)
                   {
                      texto+= aux+ "\n";
                   }
                    lee.close();
                   AST ant= Analizar(texto);
                    if(ant!=null){
                       
                          // ent.anterior=new Entorno(ant.tablaGlobal);
                          Entorno auxiliar=ent;
                          while(auxiliar!=null){
                              if(auxiliar.anterior==null){
                                  Entorno nuevo=new Entorno(ant.tablaGlobal);
                                  Entorno extra=nuevo.anterior;
                                  auxiliar.anterior=new Entorno(null);
                                  auxiliar.anterior.tabla=extra.tabla;
                                  break;
                              }
                              auxiliar=auxiliar.anterior;
                          }
                          
                       
                           
                        
                    }
                }
            
            }catch(IOException e){
                System.out.println("Error Semantico:No se encontro el archivo de la sentencia import.  Linea: "+linea +" Columna: "+columna);
            CError error=new CError(Tipo.EnumTipo.error.toString(), "Opción incorrecta:\n, No se encontro el archivo de la sentencia import. Ruta proporcionada: "+resultado.valor, linea, columna);
            lista_errores.add(error);
            }
        }
       return null;
    }
    
    private AST Analizar(String cmd){
         Analizadores.parser sintactico;
    AST arbol = null;
     try{
        
        sintactico=new Analizadores.parser(new Analizadores.Lexico(new BufferedReader(new StringReader(cmd))));
        sintactico.parse();
        arbol=sintactico.AST;
        if(arbol!=null){
            arbol.Ejecutar();
            
        }else{
            System.out.println("-------------------------------------Existe un error en el analisis");
        }
        
    }catch(IOException e){
        System.out.println(e);
    } catch (Exception ex) {
        Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
    }
     return arbol;
    }
    
}
