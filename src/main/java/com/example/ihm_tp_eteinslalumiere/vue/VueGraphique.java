package com.example.ihm_tp_eteinslalumiere.vue;

import com.example.ihm_tp_eteinslalumiere.Constantes;
import com.example.ihm_tp_eteinslalumiere.controlleur.ControlleurSouris;
import com.example.ihm_tp_eteinslalumiere.modele.Lumiere;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.IllegalFormatCodePointException;


public class VueGraphique extends GridPane {
    private Lumiere model;
    private Rectangle[][] rectangles;
    public int Fin = 0;
    public Timeline timeLine;
    private int time;

    public VueGraphique(Lumiere model) {
        this.model = model;
        super.setTranslateX((Constantes.WIDTH_WINDOW - Constantes.WIDTH)/2 - 25);
        initGrid();
        update();
    }
    public void initGrid(){
        super.setPadding(new Insets(10));
        rectangles = new Rectangle[Constantes.NOMBRE_LIGNES][Constantes.NOMBRE_COLONNES];
        Rectangle rect;
        for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
            for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                rect = new Rectangle(Constantes.WIDTH_LAMPE,Constantes.HEIGHT_LAMPE, Color.BLACK);
                rect.setFill(Color.DARKGREEN);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(2);
                rect.setArcWidth(10);
                rect.setArcHeight(10);
                super.add(rect,j,i,1,1);
                rect.setOnMouseClicked(new ControlleurSouris(model,this,j,i));
                rectangles[i][j] = rect;
            }
        }
    }


    public void update(){
        estFini();
        if(!model.estFini()) {
            for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
                for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                    if (model.getLampes()[i][j])
                        rectangles[i][j].setFill(Color.GREENYELLOW);
                    else
                        rectangles[i][j].setFill(Color.DARKGREEN);
                }
            }
        } else {
            timeLine.setCycleCount(10);
            timeLine.play();
        }
    }

    private void estFini() {
        time = 0;
        timeLine = new Timeline(new KeyFrame(Duration.millis(300),actionEvent -> {
            for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
                for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                    rectangles[i][j].setFill(Color.DARKGREEN);
                    if (time <= 2) {
                        if (j % 2 == 0 && i < 3 || j % 2 == 1 && i > 2){
                            rectangles[i][j].setFill(Color.GREENYELLOW);
                        }
                    }
                    if (time > 2 && time <= 5){
                        if (j == 2){
                            rectangles[i][j].setFill(Color.GREENYELLOW);
                        }
                    }
                    if (time > 5 && time <= 8){
                        if (j == 0 || j == 4 || j == i){
                            rectangles[i][j].setFill(Color.GREENYELLOW);
                        }
                    }
                }
            }
            time++;
        }));
    }
}
