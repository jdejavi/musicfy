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
public class Song implements Serializable, Comparable<Song>{
    private String titulo;
    private int año;
    private String duracion;
    private List<String> interpretes;
    //Fin de declaracion de variables.

    static Song inicializaAleatorio() {
        List<String> songs = new ArrayList<>();
        List<Song> chansion = new ArrayList<>();
        List<String> interpretes;
        List<String> interpretesAl = new ArrayList<>();
        Random r = new Random();
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"titulosCanciones.txt";
        String ruta1 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"nombresArtistas.txt";
        Path path = Paths.get(ruta);
        Path path1 = Paths.get(ruta1);
        Song cancion;
        String interprete;
        String nombre;
        int[] años = {1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2011,2012,2013,2014,2015,2016,2017,
            2018,2019};
        String[] duracion = {"1min 3seg", "2min 56seg", "3min 45seg", "1min 45seg", "6min 4seg"};
        
        try{
            songs= readAllLines(path);
            interpretes = readAllLines(path1);
            nombre = songs.get(r.nextInt(songs.size()));
            //interprete = interpretes.get(r.nextInt(interpretes.size()));
            for (int i = 0; i < r.nextInt(3)+1; i++) {
                interpretesAl.add(interpretes.get(r.nextInt(interpretes.size())));
            }            
            cancion = factoryEsp(nombre,interpretesAl,años[r.nextInt(años.length)],duracion[r.nextInt(duracion.length)]);
            return cancion;
        }catch(Exception e){
            err.printf("\nERROR: %s", e);
            return null;
        }
    }

    public static Song factoryEsp(String nombre,List<String> interprete, int año, String duracion) {
        Song cancion = new Song();
        cancion.titulo=nombre;
        cancion.interpretes = interprete;
        cancion.año=año;
        cancion.duracion=duracion;
        return cancion;
    }
    public Song(){
        this.titulo="desconocido";
        this.año=0;
        this.duracion="desconocida";
        this.interpretes= new ArrayList<>();
    }
    
    public Song(String nombres) {
        this.titulo=nombres;
        this.año=0;
        this.duracion="desconocida";
        this.interpretes= new ArrayList<>();        
    }

    Song(String nombre, int añoO, String dur, ArrayList<String> interpretesS) {
        this.titulo=nombre;
        this.año=añoO;
        this.duracion=dur;
        this.interpretes=interpretesS;
    }
    
    //METODOS DE FORMATO
    //------------------
    public List<String> FormatoJunto(ArrayList<Song> canciones){
        List<String> s= new ArrayList<>();
            for (Song cancion : canciones) {
                s.add(cancion.FormatoT());
            }
            return s;        
    }

    public String FormatoT() {
        return String.format("%s",this.titulo);
    }
    
    public String FormatoPaTodo(){
        String bonito = String.join(", ",this.interpretes);
        return String.format("|%-15s|%-15d|%-15s|%-15s|", this.titulo,this.año,this.duracion,bonito);
    }
    
    //GETTERS Y SETTERS
    //-----------------

    public String getTitulo() {
        return titulo;
    }

    public int getAño() {
        return año;
    }

    public String getDuracion() {
        return duracion;
    }

    public List<String> getInterpretes() {
        return interpretes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setInterpretes(List<String> interpretes) {
        this.interpretes = interpretes;
    }

    @Override
    public int compareTo(Song o) {
        return this.titulo.compareToIgnoreCase(o.getTitulo());
    }
    @Override
    public String toString(){
        return this.titulo;
    }
    
}
