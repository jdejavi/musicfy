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
public class Artist implements Serializable{

    private String nombre;
    private String bio;
    private String insta;
    private String twitter;
    private String face;
    private String wiki;
    private List<String> albumes;
    //Fin de la declaracion de variables

    private static Artist factoryEspArt(String[] primerSplit) {
        Artist auxiliar = new Artist();
        String[] albumes = primerSplit[6].split(";");
        try{
            auxiliar.nombre=primerSplit[0];
            auxiliar.bio=primerSplit[1];
            auxiliar.insta=primerSplit[2];
            auxiliar.twitter=primerSplit[3];
            auxiliar.face=primerSplit[4];
            auxiliar.wiki=primerSplit[5];
            for (int i = 0; i < albumes.length; i++) {
                auxiliar.albumes.add(albumes[i]);
            }
            return auxiliar;
        }catch(Exception e){
            err.printf("\nERROR: %s", e);
            return null;
        }
    }
    
    static Artist inicializaAleatorio() {
        List<String> albumes;
        List<String> todo;
        Random r = new Random();
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"aleatorios"+
                File.separator+"aleatorioArt.txt";
        Path path = Paths.get(ruta);
        Artist artista = new Artist();
        
        try{
            todo= readAllLines(path);
            String[] primerSplit = todo.get(r.nextInt(todo.size())).split("#");
            //
            artista= factoryEspArt(primerSplit);
            //
            return artista;
        }catch(Exception e){
            err.printf("\nERROR: %s", e);
            return null;
        }    

    }    
    public Artist(){
        this.nombre="desconocido";
        this.bio="desconocida";
        this.insta="@desconocido";
        this.twitter="@desconocido";
        this.face="desconocido";
        this.wiki="No encontrada";
        this.albumes= new ArrayList<>();
    }
    public static Artist factoryArt(String aux){
        String[] split = aux.split("#");
        String[] split2 = split[6].split(";");
        Artist devolver = new Artist();
        
        if(split.length!=7){
            err.printf("\nError al hacer el split, elementos obtenidos"
                       +" distintos a los esperados...");
            return null;            
        }
        else{
            try{
                devolver.nombre=split[0];
                devolver.bio=split[1];
                devolver.insta=split[2];
                devolver.twitter=split[3];
                devolver.face=split[4];
                devolver.wiki=split[5];
                for (String tupla : split2) {
                    devolver.albumes.add(tupla);
                }
                return devolver;
            }catch(Exception e){
                err.printf("ERROR EN EL TRY CATCH(artist): %s\n",e);
                return null;
            }
        }
    }//End of factoryArt method

    public Artist(String name) {
        this.nombre=name;
        this.bio="desconocida";
        this.insta="@desconocido";
        this.twitter="@desconocido";
        this.face="desconocido";
        this.wiki="No encontrada";
        this.albumes= new ArrayList<>();        
    }//End of constructor Artist cuando solo me dan el nombre
    
    //GETTERS Y SETTERS
    //-----------------
    public String getNombre(){
        return this.nombre;
    }

    public String getBio() {
        return bio;
    }

    public String getInsta() {
        return insta;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFace() {
        return face;
    }

    public String getWiki() {
        return wiki;
    }

    public List<String> getAlbumes() {
        return albumes;
    }
    
    //METODOS DE FORMATO
    //------------------
    
    public String FormatoImprimir(){
        String bonito = String.format(", ", this.albumes);
        return String.format("|%-3s|%-3s|%-3s|%-3s|%-3s|%-3s|%-3s|", this.nombre,this.bio,this.insta,this.twitter,this.face,
                this.wiki,bonito);
    }

    Artist factoryAñadeArtista() {
        Artist artNew = new Artist();
        boolean añade = false;
        artNew.nombre= Esdia.readString("Introduce el nombre del artista nuevo: ");
        artNew.bio=Esdia.readString("Introduce la biografia del artista: ");
        artNew.insta=Esdia.readString("Introduce el instagram del artista: ");
        artNew.twitter=Esdia.readString("Introduce el twitter del artista: ");
        artNew.face=Esdia.readString("Introduce el facebook del artista: ");
        artNew.wiki=Esdia.readString("Introduce la wikipedia del artista: ");
        do{
            String nA = Esdia.readString("Introduce el album del artista: ");
            artNew.albumes.add(nA);
            añade=yesOrNo("Desea introducir mas albumes?: ");
        }while(añade);
        return artNew;
    }

    Artist modificaArtista(Artist a) {
        a.bio= Esdia.readString("Introduce la nueva biografia: ");
        a.insta=Esdia.readString("Introduce el nuevo instagram: ");
        a.twitter=Esdia.readString("Introduce el nuevo twitter: ");
        a.face=Esdia.readString("Introduce el nuevo facebook: ");
        a.wiki=Esdia.readString("Introduce el nuevo enlace a wikipedia: ");
        return a;
    }
}
