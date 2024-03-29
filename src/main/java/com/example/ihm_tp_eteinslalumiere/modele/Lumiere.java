package com.example.ihm_tp_eteinslalumiere.modele;

import com.example.ihm_tp_eteinslalumiere.Constantes;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;

public class Lumiere {

    private final boolean [][] lampes;
    public boolean configuration;
    public SimpleIntegerProperty nombre_clicks;
    public SimpleIntegerProperty nombreAleatoire;

    public Lumiere() {
        this.lampes = new boolean[Constantes.NOMBRE_LIGNES][Constantes.NOMBRE_LIGNES];
        nombre_clicks = new SimpleIntegerProperty();
        configuration = false;
        nombreAleatoire = new SimpleIntegerProperty(8);
        initGrid();
        setLampesRandom();
    }

    public void initGrid(){
        nombre_clicks.set(0);
        for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
            for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                setLampesFalse(j,i);
            }
        }
    }

    public void changerLampe(int x, int y){
        if (DansLaGrille(x,y)){
            basculerLampe(x, y);
        }
        if (DansLaGrille(x,y - 1)){
            basculerLampe(x,y - 1);
        }
        if (DansLaGrille(x - 1,y)){
            basculerLampe(x - 1,y);
        }
        if (DansLaGrille(x + 1,y)){
            basculerLampe(x + 1,y);
        }
        if (DansLaGrille(x,y + 1)){
            basculerLampe(x,y + 1);
        }
    }

    public void basculerLampe(int x, int y) {
        lampes[y][x] = !lampes[y][x];
    }

    public boolean DansLaGrille(int x,int y){
        return x >= 0 && x < Constantes.NOMBRE_COLONNES && y >= 0 && y < Constantes.NOMBRE_LIGNES;
    }

    public void setLampesRandom(){
        initGrid();
        int x = new Random().nextInt(Constantes.NOMBRE_COLONNES);
        int y = new Random().nextInt(Constantes.NOMBRE_LIGNES);
        for (int i = 0; i < nombreAleatoire.get(); i++) {
            while(lampes[y][x]){
                x = new Random().nextInt(Constantes.NOMBRE_COLONNES);
                y = new Random().nextInt(Constantes.NOMBRE_LIGNES);
            }
            setLampesTrue(x,y);
        }
    }

    public void setLampesTrue(int x, int y){
        lampes[y][x] = true;
    }

    public void setLampesFalse(int x,int y){
        lampes[y][x] = false;
    }

    public boolean[][] getLampes() {
        return lampes;
    }

    public void setConfiguration() {
        if (configuration)
            configuration = false;
        else {
            configuration = true;
            nombre_clicks.set(0);
        }
    }

    public boolean estFini(){
        if(!configuration && nombre_clicks.get() > 0) {
            for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
                for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                    if (lampes[i][j])
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    public void setNombreAleatoire(int i){
        if (nombreAleatoire.get() + i <= 25 && nombreAleatoire.get() + i >= 0)
        nombreAleatoire.set(nombreAleatoire.get() + i);
    }

}