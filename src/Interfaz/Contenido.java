/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.File;
import javax.swing.JTextArea;

/**
 *
 * @author alexa
 */
public class Contenido {
   private File Archivo;
   private String nombre;

    public Contenido(File Archivo, String nombre) {
        this.Archivo = Archivo;
        this.nombre = nombre;
    }

    public File getArchivo() {
        return Archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setArchivo(File Archivo) {
        this.Archivo = Archivo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
