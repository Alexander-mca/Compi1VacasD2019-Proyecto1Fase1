/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.instrucciones;

import Arbol.Entorno.Entorno;
import Arbol.Entorno.Simbolo;

import Arbol.Instruccion;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author alexa
 */
public class GraficarEntorno extends Instruccion {

    @Override
    public Object ejecutar(Entorno ent) {
        String entorno = "", contenido = "digraph{",relaciones="";
        Entorno auxiliar = ent;
        int n = 0;
        while (auxiliar != null) {
            entorno += "node"+n+"[shape=plaintext label=<<table><tr><td>Tipo</td><td>Nombre</td><td>Valor</td></tr>";
            Map map = auxiliar.tabla;
            Iterator entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String id = entry.getKey().toString();
                Simbolo sim = (Simbolo) entry.getValue();
                entorno += "<tr>"
                        + "<td>" +sim.tipo.tipo+"</td>"
                        + "<td>" +id+"</td>"
                        + "<td>" +sim.valor+"</td>"
                        + "</tr>\n";
            }
            if(auxiliar.anterior!=null){
                int cont=n+1;
                relaciones+="node"+n+"->node"+cont+";";
            }
            auxiliar=auxiliar.anterior;
            n++;
            entorno+="</table>>];\n";
        }
        contenido+=entorno+relaciones+"}";
        File file=new File("entornos.txt");
         try {
            
            
 
            

            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
         String name=file.getName();
         String nombre[]=name.split("\\.");
         
        
        ejecutarCMD("dot -Tpng "+name+" -o "+nombre[0]+".png");
        try{
            
            File archivo=new File(nombre[0]+".png");
            if(archivo.exists()){
            Desktop.getDesktop().open(archivo);
            }
            //archivo.delete();
            
        }catch(Exception ex){
            
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
