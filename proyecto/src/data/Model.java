/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package data;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.yesOrNo;
import static data.Album.factoryAlb;
import static data.Artist.factoryArt;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author javier
 */
public class Model {

    private Musicfy mu = new Musicfy();
    //Fin de la declaracion de variables
    
    public void importarTodasLasCosas() throws ClassNotFoundException {
        String nombreCarpeta = "musicfy";
        
        String ruta1 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+nombreCarpeta+File.separator+"binarios"+
                File.separator+"musicfy.bin";
        String ruta2 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+nombreCarpeta+File.separator+"datos"+
                File.separator+"albumes.txt";
        String ruta3 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+nombreCarpeta+File.separator+"datos"+
                File.separator+"artistas.txt";
        
        Path p = Paths.get(ruta1);
        Path p1 = Paths.get(ruta2);
        Path p2 = Paths.get(ruta3);
        
        List<String> auxiliar01 = new ArrayList<>();
        List<String> auxiliar02 = new ArrayList<>();
        int contador=0;
        Album a = new Album();
        Artist art;
        //Song cancion = new Song();
        Random r = new Random();
        ArrayList<Artist> artistasAux = new ArrayList<>();
        ArrayList<Album> albumesAux = new ArrayList<>();
        List<Song> cancionesAux = new ArrayList<>();
        String[] duraciones = {"2min 34seg", "1min 1seg", "4min 4seg", "6min 2seg"};
                
        if(p.toFile().exists()){
            try{
                FileInputStream fis = new FileInputStream(p.toFile());
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
                mu =  (Musicfy) ois.readObject();//donde lo meto de la lista) 
                ois.close();
                out.printf("\nSe ha importado el .bin...\n");             
            }catch(IOException ex){
                err.printf("\nNo fue posible leer el archivo...\n");
                err.printf(ex.toString());
            }
        }
        else{
            try{
                auxiliar01= readAllLines(p1);
                for(String aux : auxiliar01){
                    a = factoryAlb(aux);
                    albumesAux.add(a);
                }
                mu.setAlbumes(albumesAux);             

               /* for(Song c : mu.getCanciones()){
                    out.printf("%s\n", c.FormatoPaTodo());
                }*/
                
            }catch(IOException e){
                err.printf("\nERROR al leer albumes: %s", e);
            }
//End of try catch() de albumes
            
            try{
                auxiliar02=readAllLines(p2);
                for(String aux2 : auxiliar02){
                    art = factoryArt(aux2);
                    artistasAux.add(art);
                }
                mu.setArtistas(artistasAux);
            }catch(IOException e){
                err.printf("\nERROR al leer artistas: %s", e);
            }//End of try catch() de artistas
            for (int i = 0; i < albumesAux.size(); i++) {
                for (int j = 0; j < albumesAux.get(i).getCanciones().size(); j++) {
                    Song cancion = new Song();
                    cancion.setTitulo(albumesAux.get(i).getCanciones().get(j).getTitulo());
                    cancion.setDuracion(duraciones[r.nextInt(duraciones.length)]);
                    cancion.setAño(albumesAux.get(i).getAño());
                    cancion.setInterpretes(albumesAux.get(i).getInterpretes());
                    cancionesAux.add(cancion); 
                }
            }
            mu.setCanciones((ArrayList<Song>) cancionesAux);
            out.printf("Se cargaron correctamente los archivos...\n");
        }//End of else        
    }

    public void guardaTodoBin() throws FileNotFoundException {
        FileOutputStream fo;
        BufferedOutputStream bo;
        ObjectOutputStream oo = null;
        DataOutputStream doo = null;
        String nombreCarpeta = "musicfy";

        String ruta1 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+nombreCarpeta+File.separator+"binarios"+
                File.separator+"musicfy.bin";
        Path p1 = Paths.get(ruta1);
        if(!p1.toFile().exists()){
            fo= new FileOutputStream(p1.toFile());
            doo= new DataOutputStream(fo);
        }
        try{
            fo= new FileOutputStream(p1.toFile());
            bo= new BufferedOutputStream(fo);
            oo= new ObjectOutputStream(bo);
            oo.writeObject(mu);
            oo.close();
            out.printf("\n\nSe ha guardado todo en musicfy.bin...\n");
        }catch(IOException ioe){
            err.printf("No fue posible guardar el binario");
            err.printf(ioe.toString());
        } finally{
            if(oo!=null){
                try{
                    oo.close();
                }catch(IOException ioe){
                    err.printf("ERROR");
                }
            }
        }
    }

    public void exportarArtistas() {
        List<Artist> artistas = mu.getArtistas();
        File file;
        PrintStream p;
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"salida";        
        File directorio = new File(ruta);
        if(!directorio.exists()){
            directorio.mkdir();
        }        
        String ruta2 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"salida"+
                File.separator+"artistas.col";
        try{
            file= new File(ruta2);            
            if(file.exists()){
                file.delete();
                file=new File(ruta2);
            }
            p= new PrintStream(file);
            String blanco = " ";
            //p.printf("|%-48s Nombre|%-48s Biografia|%-48s Instagram|%-48s Twitter|%-48s Facebook|%-48s Wikipedia|%-48s Albumes\n\n", blanco,blanco,blanco,blanco,
                    //blanco,blanco,blanco);            
            for(int i = 0; i < artistas.size(); i++){
                String a = String.join(" ,", artistas.get(i).getAlbumes());
                p.printf("|%-50s|-%50s|%-50s|-%50s|%-50s|-%50s|%-50s|\n",
                        artistas.get(i).getNombre(),artistas.get(i).getBio(),artistas.get(i).getInsta(),
                        artistas.get(i).getTwitter(),artistas.get(i).getFace(),artistas.get(i).getWiki(),a);
            }
        }catch(IOException e){
            err.printf("Exception: %s",e);
        }
        out.printf("\nSe exportaron los archivos a %s\n", ruta2);
    }
    public void inicializaTodoAlM() {
        Random r = new Random(System.currentTimeMillis());
        ArrayList<Song> canciones = new ArrayList<>();
        Song auxS = new Song();
        int aux = Esdia.readInt("Introduce el num de canciones a generar aleatorias: ");
        for (int i = 0; i < aux; i++) {
            auxS = Song.inicializaAleatorio();
            //out.printf("%s\n", auxS.FormatoPaTodo());
            canciones.add(auxS);
        }
        mu.setCanciones(canciones);
        
        ArrayList<Album> albumesS = new ArrayList<>();
        Album auxA = new Album();
        aux = Esdia.readInt("Introduce el num de albumes a generar aleatorias: ");
        for (int i = 0; i < aux; i++) {
            auxA = Album.inicializaAleatorio();
            //out.printf("%s\n",auxA.FormatosImprimir());
            albumesS.add(auxA);
        }
        mu.setAlbumes(albumesS);
        
        ArrayList<Artist> artistas = new ArrayList<>();
        Artist auxArt = new Artist();
        aux = Esdia.readInt("Introduce el num de artistas a generar aleatorias: ");        
        for (int i = 0; i < aux ; i++) {
            auxArt = Artist.inicializaAleatorio();
            //out.printf("%s\n", auxArt.FormatoImprimir());
            artistas.add(auxArt);
        }
        mu.setArtistas(artistas);
        
        ArrayList<PlayList> playlist = new ArrayList<>();
        PlayList auxPl = new PlayList();
        aux = Esdia.readInt("Introduce el num de playlist a generar aleatorias: ");      
        for (int i = 0; i < aux; i++) {
            auxPl = PlayList.inicializaAleatorio();
            //out.printf("%s: %s\n",auxPl.nombre,auxPl.FormatoJunto(auxPl.canciones));
            playlist.add(auxPl);
        }
        mu.setPlayList(playlist);
        
        out.printf("\nSe ha inicializado todos los artistas correctamente...\n");
    }
    public void exportarAlbumes() {
        List<Album> albumes = mu.getAlbumes();
        
        String ruta = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"salida";        
        File directorio = new File(ruta);
        File file;
        if(!directorio.exists()){
            directorio.mkdir();
        }        
        String ruta2 = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"musicfy"+File.separator+"salida"+
                File.separator+"albumes.html";
        
        try {
            file= new File(ruta2);            
            if(file.exists()){
                file.delete();
                file=new File(ruta2);
            }            
            PrintWriter pw = new PrintWriter(file);
            pw.printf("<!DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset=\"UTF-8\">%n"
                    + "<H1>Listado de albumes</H1>%n"
                    + "</HEAD>%n"
                    + "<BODY>");
            pw.printf("<TABLE BORDER=1>%n");
            pw.printf("<TR>\n" +
                "<TH> TITULO </TH>\n" +
                "<TH> INTERPRETES </TH>\n" +
                "<TH> AÑO </TH>\n" +
                "<TH> DURACION </TH>\n" +
                "<TH> Nº CANCIONES </TH>\n" +
                "<TH> TIPO </TH>\n" +
                "</TR>");
            for (Album a : albumes) {
                pw.printf("%s%n", a.asHTMLTableRow());
            }
            pw.printf("</TABLE>%n</BODY>%n</HTML>%n");
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.printf("ERROR: no se ha creado %s%n%n", ruta2);
            return;
        }
        out.printf("%n%nSe ha creado una tabla con %d tuplas.%n%n",
                albumes.size());

        out.println("Ruta del archivo: " + file.toString());
    }

    public void añadirAlbum() {
        Album a = new Album();
        List<Song> canciones = mu.getCanciones();
        List<Album> albumes = mu.getAlbumes();
        a = a.factoryCrearAlbum(canciones);
        albumes.add(a);
        mu.setAlbumes((ArrayList<Album>) albumes);
        mu.setCanciones((ArrayList<Song>) canciones);
        out.printf("\n\nSe ha añadido el album correctamente...\n\n");
    }

    public void borrarAlbum() {
        List<Album> albumes = mu.getAlbumes();
        List<Artist> artistas = mu.getArtistas();
        List<Song> canciones = mu.getCanciones();
        List<Song> cancionesAlb;
        Artist a = new Artist();
        int borraAlbum;
        int contador;
        boolean si = false;
        do{
            do{
                borraAlbum = Esdia.readInt("Que album desea borrar?: ");
            }while(borraAlbum<0 || borraAlbum>albumes.size());
            out.printf("%s\n", albumes.get(borraAlbum).FormatosImprimir());
            si = yesOrNo("Desea borrar este album?: ");
        }while(!si);
        
        //Borra el album de artista
        
        String nombreAlbum = albumes.get(borraAlbum).getTitulo();
        for (int i = 0; i < artistas.size(); i++) {
            contador=0;
            a = artistas.get(i);
            for(String album : a.getAlbumes()){
                if(album.equalsIgnoreCase(nombreAlbum)){
                    artistas.get(i).getAlbumes().remove(contador);
                }
                contador++;
            }
        }
        mu.setArtistas((ArrayList<Artist>) artistas);
        
        //Borra las canciones
        
        cancionesAlb = albumes.get(borraAlbum).getCanciones();
        for(contador = 0; contador<cancionesAlb.size(); contador++){
            for (int i = 0; i < canciones.size(); i++) {
                if(cancionesAlb.get(contador).getTitulo().equalsIgnoreCase(canciones.get(i).getTitulo())){
                    canciones.remove(i);
                }
            }
        }    
        mu.setCanciones((ArrayList<Song>) canciones);
        out.printf("\nEl album %s se borro correctamente...\n", albumes.get(borraAlbum).getTitulo());        
        albumes.remove(borraAlbum);
        mu.setAlbumes((ArrayList<Album>) albumes);
       
    }

    public void modificaAtributosAlbum() {
        List<Album> albumes = mu.getAlbumes();
        Album modifi = new Album();
        int modificaAlbum;
        do{
            modificaAlbum = Esdia.readInt("Que album desea modificar?: ");
        }while(modificaAlbum<0 || modificaAlbum>albumes.size());
        modifi=albumes.get(modificaAlbum);
        modifi = modifi.modificaAlbum(modifi);
        albumes.remove(modificaAlbum);
        albumes.add(modifi);
        mu.setAlbumes((ArrayList<Album>) albumes);
        out.printf("%s\n",modifi.FormatosImprimir());
        out.printf("\nEl album fue modificado correctamente...\n");
        
    }

    public void mostrarAlbum() {
        List<Album> albumes = mu.getAlbumes();
        Album a = new Album();
        String nombreAlbum = Esdia.readString("Introduce el nombre del album: ");
        int contador;
        for (contador = 0; contador < albumes.size(); contador++) {
            a = albumes.get(contador);
            if(a.getTitulo().equalsIgnoreCase(nombreAlbum)){
                out.printf("%s\n",a.FormatosImprimir());
            }
        }
        if(contador==albumes.size()-1){
            out.printf("\nEl album %s no se encuentra en la lista\n", nombreAlbum);
        }
    }

    public List<Song> getCanciones() {
        return mu.getCanciones();
    }

    public void añadirArtista() {
        List<Artist> artistas = mu.getArtistas();
        Artist aNew = new Artist();
        aNew = aNew.factoryAñadeArtista();
        artistas.add(aNew);
        mu.setArtistas((ArrayList<Artist>) artistas);
    }

    public void borrarArtista() {
        List<Artist> artistas = mu.getArtistas();
        List<Album> albumes = mu.getAlbumes();
        List<Artist> aBorr = new ArrayList<>();
        boolean encontrado = false;
        boolean encontradoN = false;
        String nombreBorrar = Esdia.readString("Que artista desea borrar?: ");
        
        for(Artist a : artistas){
            if(a.getNombre().equalsIgnoreCase(nombreBorrar)){
                encontradoN = true;
                for (int i = 0; i < a.getAlbumes().size(); i++) {
                    for (int j = 0; j < albumes.size(); j++) {
                        if(a.getAlbumes().get(i).equalsIgnoreCase(albumes.get(j).getTitulo())){
                            encontrado = true;
                        }  
                    }
                }
                if(!encontrado){
                    aBorr.add(a);
                }
            }
        }
        if(!encontradoN){
            out.printf("Artista no encontrado...\n");
        }
        else{
            if(!encontrado){
                out.printf("Albumes del artista no dados de alta, se procede a borrar...\n");
                artistas.removeAll(aBorr);
                mu.setArtistas((ArrayList<Artist>) artistas);
            }
            else{
                out.printf("No se puede borrar, albumes dados de alta...\n");
            }
        }
    }

    public void modificarAtributosArtista() {
        List<Artist> artistas = mu.getArtistas();
        Artist artModifi = new Artist();
        Artist borr = new Artist();
        boolean encontrado = false;
        String nombreModificar = Esdia.readString("Introduce el nombre del artista" 
                +" a modificar: ");
        for(Artist a : artistas){
            if(a.getNombre().equalsIgnoreCase(nombreModificar)){
                artModifi = a.modificaArtista(a);
                encontrado = true;
                borr=a;
            }
        }
        if(encontrado){
            out.printf("Artista modificado...\n");
            artistas.add(artModifi);
            artistas.remove(borr);
            mu.setArtistas((ArrayList<Artist>) artistas);
        }
    }

    public void consultaDeAlbum() {
        List<Artist> artistas = mu.getArtistas();
        List<Album> albumes = mu.getAlbumes();
        List<Album> albVac = new ArrayList<>();
        boolean encontrado = false;
        String nombre = Esdia.readString("Introduce el nombre del artista"
            + " del que se va a consultar los albumes: ");
        for(Album alb : albumes){
            for(String name : alb.getInterpretes()){
                String[] split = name.split(",");
                for (int i = 0; i < split.length; i++) {
                    if(nombre.equalsIgnoreCase(split[i])){
                        encontrado = true;
                        albVac.add(alb);
                    }
                }
            }
        }
        if(encontrado){
            out.printf("Artista encontrado...\n");
            Collections.sort(albVac);
            for(Album a : albVac){
                out.printf("%s\n", a.FormatosImprimirSinCanciones());
            }
        }
    }

    public void crearPlayList() {
        PlayList playListNueva = new PlayList();
        List<PlayList> lista = new ArrayList<>();
        List<Song> songs = mu.getCanciones();
        Random r = new Random();
        playListNueva.nombre=Esdia.readString("Introduce el nombre de la playlist: ");
        int numCanc = Esdia.readInt("Introduce el numero de canciones que tendra la playlist: ");
        for (int i = 0; i < numCanc; i++) {
            playListNueva.canciones.add(songs.get(r.nextInt(songs.size())));
        }
        lista.add(playListNueva);        
        for(PlayList pl : lista){
            out.printf("%s\n", pl.FormatoImprimir());
        }
        
        mu.setPlayList((ArrayList<PlayList>) lista);
    }

    public void borrarPlayList() {
        List<PlayList> playlist = mu.getPlayList();
        String nombre = Esdia.readString("Introduce el nombre de la playlist"
                +" a borrar: ");
        boolean plencontrada = false;
        boolean cancionEncontrada = false;
        String nombrecancion;
        boolean salir = false;
        Song aBorr = new Song();
        for(PlayList pl : playlist){
            if(pl.nombre.equalsIgnoreCase(nombre)){
                plencontrada = true;
                do{
                    out.printf("Canciones de la playlist\n");
                    for(Song s : pl.canciones){
                        out.printf("%s\n", s.FormatoPaTodo());
                    }
                    nombrecancion = Esdia.readString("¿Que cancion desea borrar?: ");
                    for(Song so : pl.canciones){
                        if(so.getTitulo().equalsIgnoreCase(nombrecancion)){
                            cancionEncontrada = true;
                            aBorr = so;
                        }
                    }
                    pl.canciones.remove(aBorr);
                    salir=yesOrNo("Desea borrar otra cancion?: ");
                }while(salir);
                out.printf("\nTu playlist ha quedado asi: \n\n");
                for(Song s : pl.canciones){
                    out.printf("%s\n", s.FormatoPaTodo());
                }
            }
        }
        if(!plencontrada){
            out.printf("\nPlayList con nombre %s no encontrada...\n", nombre);
        }
        mu.setPlayList((ArrayList<PlayList>) playlist);
    }

    public void añadirCancionAPlayList() {
        List<PlayList> playlist = mu.getPlayList();
        Song s = new Song();
        String nombrepl = Esdia.readString("Introduce el nombre de la playlist"
                +" donde se añadira la cancion: ");
        boolean encontradapl = false;
        boolean añade = false;
        boolean añadeI = false;
        String linea1, linea2;
        List<String> interpretes = new ArrayList<>();
        
        for(PlayList pl : playlist){
            if(pl.nombre.equalsIgnoreCase(nombrepl)){
                encontradapl = true;
                do{
                    String nombre = Esdia.readString("Introduce el nombre de la cancion: ");
                    int año = Esdia.readInt("Introduce el año de la cancion: ");
                    linea1=String.join(" ",Esdia.readString("Introduce los minutos de la cancion: "), "min");
                    linea2=String.join(" ", Esdia.readString("Introduce los segundos de la cancion: "), "seg");
                    String duracion = String.join(" ", linea1, linea2);
                    do{
                        String interprete=Esdia.readString("Introduce el/los interpretes: ");
                        interpretes.add(interprete);
                        añadeI=yesOrNo("Desea añadir otro interprete?: ");
                    }while(añadeI);
                    s = new Song(nombre, año, duracion, (ArrayList<String>) interpretes);
                    pl.canciones.add(s);
                    añade=yesOrNo("Desea añadir otra cancion?: ");
                }while(añade);
            }
        }
        if(!encontradapl){
            out.printf("\nLa playlist con nombre %s no se encontro...\n", nombrepl);
        }
        mu.setPlayList((ArrayList<PlayList>) playlist);
    }

}    

