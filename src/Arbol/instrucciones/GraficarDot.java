/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Tipo;
import Arbol.Expresion;
import Arbol.Expresiones.Logicas.And;
import Arbol.Instruccion;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author alexa
 */
public class GraficarDot extends Instruccion{
    public Expresion ruta,contenido;

    public GraficarDot(Expresion ruta, Expresion contenido) {
        this.ruta = ruta;
        this.contenido = contenido;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        Expresion Resruta=ruta.getValor(ent);
        Expresion ResContenido=contenido.getValor(ent);
        if(Resruta!=null && ResContenido!=null){
            
           if(Resruta.tipo.tipo==Tipo.EnumTipo.cadena && ResContenido.tipo.tipo==Tipo.EnumTipo.cadena){
               String ruta1=Resruta.valor.toString();
               String []nombre=ruta1.split("\\.");
          
            File file=new File(nombre[0]+".txt");
       
         try {
            
            
 
            

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ResContenido.valor.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        ejecutarCMD("dot -T"+nombre[1]+" "+file.getName()+" -o "+ruta1);
        try{
            
            File archivo=new File(ruta1);
            if(archivo.exists()){
            Desktop.getDesktop().open(archivo);
            }
            //archivo.delete();
            
        }catch(Exception ex){
            
        }
           }
        }
            
       return null;
    }
     private void ejecutarCMD(String cmd){
    Process p;
    try {
      p = Runtime.getRuntime().exec(cmd);
      p.waitFor();
      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line = "";
      while ((line = reader.readLine())!= null) {
        System.out.println(line);
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
