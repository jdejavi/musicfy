/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package data;

import java.io.Serializable;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author javier
 */
public class Musicfy implements Serializable{
    private List<Song> canciones = new ArrayList<>();
    private List<Album> albumes = new ArrayList<>();
    private List<Artist> artistas = new ArrayList<>();
    private List<PlayList> playList = new ArrayList<>();
    
    //GETTERS Y SETTERS
    //-----------------

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public void setArtistas(ArrayList<Artist> artistas) {
        this.artistas = artistas;
    }

    public void setPlayList(ArrayList<PlayList> playList) {
        this.playList = playList;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public List<Artist> getArtistas() {
        return artistas;
    }

    public List<PlayList> getPlayList() {
        return playList;
    }

    Musicfy devuelveMusicfyFinal() {
        Musicfy mu = new Musicfy();
        mu.albumes=mu.getAlbumes();
        mu.artistas=mu.getArtistas();
        mu.canciones=mu.getCanciones();
        mu.playList=mu.getPlayList();
        return mu;
    }
}

    
    //--------------------------------------
    //--------------------------------------
