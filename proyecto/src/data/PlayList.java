/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package data;

import java.io.File;
import java.io.Serializable;
import static java.lang.System.err;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author javier
 */
public class PlayList implements Serializable{

    String nombre;
    ArrayList<Song> canciones;
    
    public PlayList(){
        nombre="desconocido";
        canciones = new ArrayList<>();
    }
    static PlayList inicializaAleatorio() {
        List<Song> canciones = new ArrayList<>();
        List<String> nombresCanciones = new ArrayList<>();
        List<String> todo = new ArrayList<>();
        PlayList auxPlaylist= new PlayList();
        Random r = new Random();
        
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"nombresPlaylists.txt";
        String ruta1 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"titulosCanciones.txt";
        Path path1 = Paths.get(ruta);
        Path path2 = Paths.get(ruta1);
        
        try{
            todo=readAllLines(path1);
            nombresCanciones = readAllLines(path2);
            for(String nombre : nombresCanciones){
                Song auxCancion = new Song(nombre);
                canciones.add(auxCancion);
            }
            auxPlaylist= factoryEspPlayL(todo.get(r.nextInt(todo.size())),canciones);
            return auxPlaylist;
        }catch(Exception e){
            err.printf("ERROR: %s", e);
            return null;
        }
    }
    private static PlayList factoryEspPlayL(String nombrePl, List<Song> cancionesS) {
        PlayList p = new PlayList();
        Random r = new Random();
        try{
            p.nombre=nombrePl;
            for (int i = 0; i < r.nextInt(cancionesS.size()); i++) {
                p.canciones.add(cancionesS.get(r.nextInt(cancionesS.size())));
            }
            return p;
        }catch(Exception e){
            err.printf("\nERROR: %s", e);
            return null;
        }
    }

    //METODOS DE FORMATO
    //------------------
    public String FormatoImprimir(){
        Song s = new Song();
        String bonito = String.join(", ", s.FormatoJunto(canciones));
        return String.format("|%-15s|%-15s|", this.nombre, bonito);
    }
    
    public List<String> FormatoJunto(ArrayList<Song> canciones){
        List<String> s= new ArrayList<>();
        for (Song cancion : canciones) {
            s.add(cancion.FormatoT());
        }
        return s;
    }  
}
