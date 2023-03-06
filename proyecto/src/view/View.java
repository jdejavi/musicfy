/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package view;
import static com.coti.tools.DiaUtil.clear;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.yesOrNo;
import com.coti.tools.Rutas;
import controller.Controller;
import data.Album;
import data.Artist;
import data.Musicfy;
import data.Song;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.System.err;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author javier
 */
public class View {
    Controller c = new Controller();
    Musicfy mu = new Musicfy();
    private final String nameOfHtml = "albumes.html";
    private final String nombreCarpeta="musicfy";
    public void runMenuMain(String menu) throws InterruptedException {
        /*Metodo que muestra las opciones disponibles 
        MenuPrincipal
        */
        String opcion = null;
        String[] posiblesOpciones = {"1", "2", "3", "4", "5", "6", "h", "q"};
        boolean salir = false;
        do{
            opcion=readString(menu, posiblesOpciones).toLowerCase();
            switch(opcion){
                case "1": this.inicializaTodoAleatorio();       break;
                
                case "2": this.runMenuExportar("\n1.- Exportar artistas"
                                +"\n2.- Exportar albumes"
                                +"\nh.- Menu de ayuda"
                                +"\nq.- Salir");                break;
                                
                case "3": this.runMenuAlbum("\n1.- Altas"
                                +"\n2.- Bajas"
                                +"\n3.- Modificaciones"
                                +"\n4.- Consulta"
                                +"\nh.- Menu de ayuda"
                                +"\nq.- Salir");                break;
                
                case "4": this.runMenuArtista("\n1.- Altas"
                                +"\n2.- Bajas"
                                +"\n3.- Modificaciones"
                                +"\n4.- Albumes"
                                +"\nh.- Menu de ayuda"
                                +"\nq.- Salir");                break;
                
                case "5": this.runMenuPlayList("\n1.- Altas"
                                +"\n2.- Eliminar cancion"
                                +"\n3.- Añadir cancion"
                                +"\nh.- Menu de ayuda"
                                +"\nq.- Salir");                break;
                
                case "6": this.listadoCanciones();              break;
                
                case "h": this.displayAyuda();                  break;
                
                case "q": salir=yesOrNo("Desea salir de la app?: ");      break;
                
                default:  out.printf("\nOpcion escogida no valida\n"); break;
            }
        }while(!salir);
    }//End of runMenuMain()
    
    //===============================//
    // Metodos del runMenu principal //
    //===============================//
    
    private void inicializaTodoAleatorio() {
        c.inicializaTodoAleatorio();
    }//End of inicializaTodoAleatorio()

    private void runMenuExportar(String menu) throws InterruptedException {
        /*Metodo que enseña el menu disponible para la opcion
        Exportar
        */
        out.printf("\nHas entrado en el menu de la opcion Exportar\n");
        String opcion = null;
        String[] opciones = {"1","2","h","q"};
        boolean salir = false;
        do{
            opcion = readString(menu,opciones).toLowerCase();
            switch(opcion){
                case "1": this.exportarArtistas();      break;
                case "2": this.exportarAlbumes();       break;
                case "h": this.displayAyuda();          break;
                case "q": salir=yesOrNo("Desea salir del menu Exportar?: "); break;
                default: out.println("\nOpcion elegida no valida entre"
                                    +" las disponibles\n\n"); break;
            }
            
        }while(!salir);
    }//End of runMenuExportar()

    private void runMenuAlbum(String menu) throws InterruptedException {
        /*Metodo que muestra el menu disponible para la opcion
        Album
        */
        out.printf("\nHas entrado en el menu de la opcion Album\n");        
        String opcion = null;
        String[] opciones = {"1","2","3","4","h","q"};
        boolean salir = false;
        do{
            opcion = readString(menu,opciones).toLowerCase();
            switch(opcion){
                case "1": this.añadirAlbum();       break;
                case "2": this.borrarAlbum();       break;
                case "3": this.modificarAtributosAlbum();   break;
                case "4": this.consultaDelAlbum();  break;
                case "h": this.displayAyuda();      break;
                case "q": salir=yesOrNo("Desea salir del menu Album?: ");  break;
                default: out.printf("\nOpcion incorrecta...\n");    break;
            }
        }while(!salir);
    }//End of runMenuAlbum()

    private void runMenuArtista(String menu) throws InterruptedException {
        /*Metodo que muestra el menu disponible para la opcion
        Artista
        */
        out.printf("\nHas entrado en el menu de la opcion Artista\n");        
        String opcion = null;
        String[] opciones = {"1","2","3","4","h","q"};
        boolean salir = false;
        do{
            opcion = readString(menu,opciones).toLowerCase();
            switch(opcion){
                case "1": this.añadirArtista();       break;
                case "2": this.borrarArtista();       break;
                case "3": this.modificarAtributosArtista();   break;
                case "4": this.consultaDeAlbumesDelArtista();  break;
                case "h": this.displayAyuda();        break;
                case "q": salir=yesOrNo("Desea salir del menu Artista?: ");  break;
                default: out.printf("\nOpcion incorrecta...\n");    break;
            }
        }while(!salir);        
    }//End of runMenuArtista()

    private void runMenuPlayList(String menu) throws InterruptedException {
        /*Metodo que muestra el menu disponible para la opcion
        PlayList
        */
        out.printf("\nHas entrado en el menu de la opcion PlayList\n");        
        String opcion = null;
        String[] opciones = {"1","2","3","h","q"};
        boolean salir = false;
        do{
            opcion = readString(menu,opciones).toLowerCase();
            switch(opcion){
                case "1": this.crearPlayList();       break;
                case "2": this.borrarCancionDePlayList();       break;
                case "3": this.añadirCancionAPlayList();   break;
                case "h": this.displayAyuda();        break;
                case "q": salir=yesOrNo("Desea salir del menu PlayList?: ");  break;
                default: out.printf("\nOpcion incorrecta...\n");    break;
            }
        }while(!salir);
    }//End of runMenuPlayList()

    private void displayAyuda() throws InterruptedException {
        out.printf("\n\n");
        clear();
        out.println("===================================================");
        out.println("\n|     Has entrado en el menu de ayuda al user     |\n");
        out.println("\n|     en unos pocos segundos se desplegara un     |\n");
        out.println("\n|      texto con lo que hace cada opcion          |\n");
        out.println("\n|            disponible arriba :)                 |\n");
        out.println("===================================================");
        Thread.sleep(3000);
        out.println("\nLa opcion de GENERACION ALEATORIA inicializa de forma"
                + " completamente aleatoria todas las colecciones.\n\n");
        out.println("\nLa opcion ARCHIVOS te da dos opciones: \nLa primera de ellas"
                + " consiste en exportar los artistas a un fichero llamado "
                + "artistas.col"
                + "\nLa segunda de ellas te da la opcion de exportar los albumes.\n\n");
        out.println("\nLa opcion ALBUM te da cuatro opciones a elegir: \nLa primera"
                + " de ellas añade un album a la coleccion de albumes."
                + "\nLa segunda de ellas te da la opcion de borrar un album"
                + " de la lista de albumes.\nLa tercera de ellas te da la opcion"
                + " modificar cualquier atributo del album.\nLa cuarta te da la"
                + " opcion de consultar los datos de un album en especial.\n\n");
        out.println("\nLa opcion ARTISTA te da cuatro opciones a elegir: \nLa primera"
                + " de ellas añade un artista a la coleccion de artistas."
                + "\nLa segunda de ellas te da la opcion de borrar un artista de "
                + "la coleccion de artistas.\nLa tercera de ellas te da la opcion"
                + " de modificar los datos de un artista en concreto.\nLa cuarta"
                + " de ellas te da la opcion de consultar los albumes del artista.\n\n");
        out.println("\nLa opcion PLAYLIST te da tres opciones: \nLa primera de ellas"
                + " te da la opcion de añadir una playlist a la coleccion de"
                + " playlists.\nLa segunda de ellas te da la opcion de eliminar"
                + " una cancion de una playlist determinada.\nLa cuarta de ellas"
                + " te da la opcion de añadir una cancion a una playlist.\n\n");
        out.println("\nLa opcion CANCIONES te saca por pantalla un listado general"
                + " de todas las canciones ordenadas por fecha y titulo.\n\n");
        out.println("\nLa opcion h despliega este menu.\n\n");
        out.println("\nLa opcion s te saca de la app.\n\n");
        
    }//End of displayAyuda()
    
    //=================================//
    // Metodos del runMenu de Exportar //
    //=================================//

    private void exportarArtistas() {
        c.exportarArtistas();
    }//End of exportarArtistas()

    private void exportarAlbumes() {
        c.exportarAlbumes();
    }

    
    
    //==============================//
    // Metodos del runMenu de Album //
    //==============================//

    private void añadirAlbum() {
        c.añadirAlbum();
    }

    private void borrarAlbum() {
        c.borrarAlbum();
    }

    private void modificarAtributosAlbum() {
        c.modificaAtributosAlbum();
    }

    private void consultaDelAlbum() {
        c.mostrarAlbum();
    }
    
    //================================//
    // Metodos del runMenu de Artista //
    //================================//

    private void añadirArtista() {
        c.añadirArtista();
    }

    private void borrarArtista() {
        c.borrarArtista();
    }

    private void modificarAtributosArtista() {
        c.modificarArtista();
    }

    private void consultaDeAlbumesDelArtista() {
        c.consultaDeAlbum();//ordenar por año
    }
    
    //=================================//
    // Metodos del runMenu de PlayList //
    //=================================//    

    private void crearPlayList() {
        c.crearPlayList();
    }

    private void borrarCancionDePlayList() {
        c.borrarPlayList();
    }

    private void añadirCancionAPlayList() {
        c.añadirCancionAPlayList();
    }
    //================================//
    //================================//
    private void listadoCanciones() {//Ordenado por año y titulo resp
        List<Song> cancion = c.getCanciones();
        List<String> ordenado = new ArrayList<>();


        Comparator<Song> comparator1 = Comparator.comparing(Song::getAño);
        Comparator<Song> comparator2 = Comparator.comparing(Song::getTitulo);
        cancion.sort(comparator1.thenComparing(comparator2));

        for(Song c : cancion){
            out.printf("%s\n",c.FormatoPaTodo());
        }
    }//End of listadoCanciones()    
    
    //=================================//
    //=================================//
    public void importarTodasLasCosas() throws ClassNotFoundException {
        c.importarTodasLasCosas();
    }

    public void guardaTodo() throws FileNotFoundException {
        c.guardaTodoC();
    }
   /* 
    Comparator<Song> comparator = new Comparator<Song>(){
        
        public int compare(Song s1, Song s2){
            int resultado = Integer.compare(s1.getAño(), s2.getAño());
            if(resultado!=0){
                return resultado;
            }
            resultado= comparaNombres(s1.getTitulo(), s2.getTitulo());
        }

        private int comparaNombres(String titulo, String titulo0) {
            return 
        }
    }*/

}
