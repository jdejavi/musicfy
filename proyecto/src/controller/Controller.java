/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package controller;

import data.Album;
import data.Artist;
import data.Model;
import data.Musicfy;
import data.Song;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javier
 */
public class Controller {
    Model m = new Model();
    Musicfy mu = new Musicfy();
    
    public void importarTodasLasCosas() throws ClassNotFoundException {
        m.importarTodasLasCosas();
    }

    public void guardaTodoC() throws FileNotFoundException {
        m.guardaTodoBin();
    }

    public void inicializaTodoAleatorio() {
        m.inicializaTodoAlM();
    }

    public void exportarArtistas() {
        m.exportarArtistas();
    }

    public void exportarAlbumes() {
        m.exportarAlbumes();
    }

    public void añadirAlbum() {
        m.añadirAlbum();
    }

    public void borrarAlbum() {
        m.borrarAlbum();
    }

    public void modificaAtributosAlbum() {
        m.modificaAtributosAlbum();
    }

    public void mostrarAlbum() {
        m.mostrarAlbum();
    }

    public List<Song> getCanciones() {
        return m.getCanciones();
    }

    public void añadirArtista() {
        m.añadirArtista();
    }

    public void borrarArtista() {
        m.borrarArtista();
    }

    public void modificarArtista() {
        m.modificarAtributosArtista();
    }

    public void consultaDeAlbum() {
        m.consultaDeAlbum();
    }

    public void crearPlayList() {
        m.crearPlayList();
    }

    public void borrarPlayList() {
        m.borrarPlayList();
    }

    public void añadirCancionAPlayList() {
        m.añadirCancionAPlayList();
    }


}
