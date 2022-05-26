package com.example.ihm_tp_eteinslalumiere.vue;

import com.example.ihm_tp_eteinslalumiere.Constantes;
import com.example.ihm_tp_eteinslalumiere.controlleur.ControlleurSouris;
import com.example.ihm_tp_eteinslalumiere.modele.Lumiere;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VueGraphique extends GridPane {
    private Lumiere model;
    private Rectangle[][] rectangles;

    public VueGraphique(Lumiere model) {
        super();
        this.model = model;
        super.setTranslateX((Constantes.WIDTH_WINDOW - Constantes.WIDTH)/2 - 25);
//        super.setVgap(1);
//        super.setHgap(1);
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
        for (int i = 0; i < Constantes.NOMBRE_LIGNES; i++) {
            for (int j = 0; j < Constantes.NOMBRE_COLONNES; j++) {
                if(model.getLampes()[i][j])
                    rectangles[i][j].setFill(Color.GREENYELLOW);

                else
                    rectangles[i][j].setFill(Color.DARKGREEN);
            }
        }
    }
}
