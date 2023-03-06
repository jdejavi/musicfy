/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package data;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.yesOrNo;
import java.io.File;
import java.io.Serializable;
import static java.lang.System.err;
import static java.lang.System.out;
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
public class Album implements Serializable, Comparable<Album>{
     Musicfy mu = new Musicfy();
    
    public Album modificaAlbum(Album modifi) {
        String linea1, linea2;
        try{
            modifi.año = Esdia.readInt("Introduce el nuevo año: ");
            linea1=String.join(" ",Esdia.readString("Introduce los minutos del album: "), "min");
            linea2=String.join(" ", Esdia.readString("Introduce los segundos del album: "), "seg");
            modifi.duracion=String.join(" ", linea1, linea2);
            modifi.numDeSongs=Esdia.readInt("Introduce el nuevo numero de canciones: ");
            return modifi;
        }catch(Exception e){
            err.printf("No se pudo modificar, exception %s", e);
            return null;
        }
    }
    private String titulo;
    private ArrayList<String> interpretes;
    private int año;
    private String duracion;
    private int numDeSongs;
    private String AoS;
    private ArrayList<Song> canciones;
    //Fin de declaracion de variables
    
    public Album factoryCrearAlbum(List<Song> canciones) {
        Album a = new Album();
        Song s = new Song();
        ArrayList<Song> list = new ArrayList<>();
        boolean salir = true;
        String nombreInt;
        int contador=0;
        String name;
        String duration1, duration2;
        list = (ArrayList<Song>) mu.getCanciones();
        
        try{
            a.titulo = Esdia.readString("¿Cual es el titulo del album?: ");
            do{
                nombreInt=Esdia.readString("Introduce el nombre del/los interpretes: ");
                a.interpretes.add(nombreInt);
                salir=yesOrNo("Desea introducir mas interpretes?: ");
            }while(salir);
            a.año=Esdia.readInt("Introduce el año: ");
            duration1=String.join(" ", Esdia.readString("Introduce los segundos del album: "), "seg");
            duration2=String.join(" ", Esdia.readString("Introduce los minutos del album: "),"min");
            a.duracion=String.join(" ", duration1, duration2);
            
            a.numDeSongs=Esdia.readInt("Introduce el numero de canciones del album: ");
            if(a.numDeSongs==1){
                a.AoS="sencillo";
                duration1=String.join(" ",Esdia.readString("Introduce la duracion de la cancion(minutos): "), "min");
                duration2=String.join(" ", Esdia.readString("Introduce la duracion de la cancion(segundos): "), "seg");
                String duration3 = String.join(" ", duration1, duration2);
                s = new Song(a.titulo, a.año, duration3, a.interpretes);
                a.canciones.add(s);
                canciones.add(s);
            }
            else{
                a.AoS="album";
                for (int i = 0; i < a.numDeSongs; i++) {
                    name=Esdia.readString("Introduce el nombre de la cancion: ");
                    duration1=String.join(" ",Esdia.readString("Introduce la duracion de la cancion(minutos): "), "min");
                    duration2=String.join(" ", Esdia.readString("Introduce la duracion de la cancion(segundos): "), "seg");
                    String duration3 = String.join(" ", duration1, duration2);
                    s = new Song(name, a.año, duration3, a.interpretes);
                    a.canciones.add(s);
                    canciones.add(s);
                }
            }
            return a;
        }catch(Exception e){
            err.printf("No se pudo añadir album, exception %s", e);
            return null;
        }
    }
    private static Album factoryEsp(String tituloAlbum, List<String> interprete, int año, int acumulador, int numCanciones, List<Song> cancion) {
        Album finalA = new Album();
        try{
            finalA.titulo = tituloAlbum;
            finalA.interpretes = (ArrayList<String>) interprete;
            finalA.año=año;
            finalA.duracion=String.valueOf(acumulador);
            finalA.numDeSongs=numCanciones;
            finalA.AoS="album";
            finalA.canciones=(ArrayList<Song>) cancion;
        }catch(Exception e){
            err.printf("ERROR: %s",e);
            return null;
        }
        return finalA;
    }
    private static Album factoryEspSenc(String titulo, List<String> interprete, int año, int acumulador, int numCanciones, String cancion){
        Album finalA = new Album();
        try{
            finalA.titulo = titulo;
            finalA.interpretes = (ArrayList<String>) interprete;
            finalA.año=año;
            finalA.duracion=String.valueOf(acumulador);
            finalA.numDeSongs=numCanciones;
            finalA.AoS="sencillo";
            finalA.canciones.add(new Song(cancion));
        }catch(Exception e){
            err.printf("ERROR: %s", e);
            return null;
        }
        return finalA;
    }
    static Album inicializaAleatorio() {
        List<String> songs = new ArrayList<>();
        List<String> interpretes;
        List<String> albumes;
        List<String> interpretesAl = new ArrayList<>();
        
        Random r = new Random();
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"titulosCanciones.txt";
        String ruta1 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"nombresArtistas.txt";
        String ruta2 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"nombresAlbumes.txt";
        Path path = Paths.get(ruta);
        Path path1 = Paths.get(ruta1);
        Path path2 = Paths.get(ruta2);
        
        Album albumA;
        
        ArrayList<Song> cancion = new ArrayList<>();
        Song cancionN;
        String interprete;
        String nombre;
        String tituloAlbum;
        String[] tipo = {"sencillo", "album"};
        int[] años = {1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2011,2012,2013,2014,2015,2016,2017,
            2018,2019};
        String[] duracionC = {"1min 3seg", "2min 56seg", "3min 45seg", "1min 45seg", "6min 4seg"};
        try{
            songs= readAllLines(path);
            interpretes = readAllLines(path1);
            albumes = readAllLines(path2);
            for (int i = 0; i < r.nextInt(3)+1; i++) {
                interpretesAl.add(interpretes.get(r.nextInt(interpretes.size())));
            }
            //interprete = interpretes.get(r.nextInt(interpretes.size()));
            tituloAlbum = albumes.get(r.nextInt(albumes.size()));
            
            for (int i = 0; i < r.nextInt(30); i++) {
                nombre = songs.get(r.nextInt(songs.size()));                
                cancionN = Song.factoryEsp(nombre,interpretesAl,años[r.nextInt(años.length)],duracionC[r.nextInt(duracionC.length)]);
                cancion.add(cancionN);
            }
            if(tipo[r.nextInt(tipo.length)].equalsIgnoreCase("sencillo")){
                albumA = factoryEspSenc(tituloAlbum,interpretesAl,años[r.nextInt(años.length)],r.nextInt(10)+40,1,tituloAlbum);
                return albumA;
            }
            else{
                albumA = factoryEsp(tituloAlbum,interpretesAl,años[r.nextInt(años.length)],r.nextInt(10)+40, cancion.size(),cancion);
                return albumA;
            }
        }catch(Exception e){
            err.printf("\nERROR: %s", e);
            return null;
        }    
    }

    public Album() {
        this.titulo = "desconocido";
        this.interpretes = new ArrayList<>();
        this.año = 0;
        this.duracion = "desconocido";
        this.numDeSongs = 0;
        this.AoS = "desconocido";
        this.canciones = new ArrayList<>();
    }

    public static Album factoryAlb(String aux) {
        String[] spliteada = aux.split("\t");
        String[] splitArt = spliteada[1].split(";");
        String[] splitSeg = spliteada[spliteada.length-1].split(";");
        Album auxiliar = new Album();
        Song canciones = new Song();
        
        if (spliteada.length > 7) {
            err.printf("\nError al hacer el split, elementos obtenidos"
                    + " distintos a los esperados...");
            return null;
        }
        else {
            try {
                auxiliar.titulo = spliteada[0];
                
                for (int i = 0; i < splitArt.length; i++) {
                    auxiliar.interpretes.add(splitArt[i]);
                }
                
                auxiliar.año = Integer.parseInt(spliteada[2]);
                auxiliar.duracion = spliteada[3];
                auxiliar.numDeSongs = Integer.parseInt(spliteada[4]);
                auxiliar.AoS = spliteada[5];
                
                if(auxiliar.AoS.equalsIgnoreCase("sencillo")){
                    canciones=new Song(auxiliar.titulo);
                    auxiliar.canciones.add(canciones);
                    return auxiliar;
                }
                else{
                    for (String tupla : splitSeg){
                        canciones = new Song(tupla);
                        auxiliar.canciones.add(canciones);
                    }
                    return auxiliar;
                }
            }catch (Exception e) {
                err.printf("ERROR EN EL TRY CATCH(album): %s\n", e);
                return null;
            }
            
    }
}//End of method factory(String aux)


    //GETTERS Y SETTERS
    //-----------------

    public String getTitulo() {
        return titulo;
    }

    public List<String> getInterpretes() {
        return interpretes;
    }

    public int getAño() {
        return año;
    }

    public String getDuracion() {
        return duracion;
    }

    public int getNumDeSongs() {
        return numDeSongs;
    }

    public String getAoS() {
        return AoS;
    }

    public List<Song> getCanciones() {
        return canciones;
    }
    
    //METODOS DE FORMATO
    //------------------    
    public String FormatosImprimir(){
        Song s = new Song();
        String bonito = String.join(", ", s.FormatoJunto(canciones));
        String bonito2 = String.join(", ", this.interpretes);
            return String.format("|%-15s|%-15s|%-15d|%-15s|%-15d|%-15s|%-15s|", this.titulo,bonito2,this.año,
                    this.duracion,this.numDeSongs,this.AoS,bonito);
    }
    public String asHTMLTableRow() {
        String resultado;
        Song s = new Song();
        //String bonito = String.join(", ", s.FormatoJunto(this.canciones));
        String bonito2 = String.join(", ", this.interpretes);        
        resultado = String.format("<TR>"
                + "<TD>%s</TD>" // Titulo
                + "<TD>%s</TD>" // Interpretes
                + "<TD>%d</TD>" // Año
                + "<TD>%s</TD>" // Duracion
                + "<TD>%d</TD>" // NumDeCanciones
                + "<TD>%s</TD>" // Tipo
                + "</TR>",
                this.titulo,
                bonito2,
                this.año,
                this.duracion,
                this.numDeSongs,
                this.AoS);
        return resultado;
    }    

    public String FormatosImprimirSinCanciones() {
        Song s = new Song();
        String bonito2 = String.join(", ", this.interpretes);
            return String.format("|%-25s|%-25s|%-25d|%-25s|%-25d|%-25s|", this.titulo,bonito2,this.año,
                    this.duracion,this.numDeSongs,this.AoS);    
    }

    @Override
    public int compareTo(Album o) {
        if(this.año>o.getAño()){
            return 1;
        }
        else if(this.año== o.getAño()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
