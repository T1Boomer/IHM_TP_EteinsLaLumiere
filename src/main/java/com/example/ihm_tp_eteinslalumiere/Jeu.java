package com.example.ihm_tp_eteinslalumiere;

import com.example.ihm_tp_eteinslalumiere.modele.Lumiere;
import com.example.ihm_tp_eteinslalumiere.vue.VueGraphique;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Jeu extends Application {
    @Override
    public void start(Stage stage){
        BorderPane root = new BorderPane();

        Lumiere modele = new Lumiere();
        VueGraphique vueGraphique = new VueGraphique(modele);

        Button random = new Button("Aléatoire");
        TextField nombreClicks = new TextField();
        Button configurer = new Button("Configurer");
        Button jouer = new Button("jouer");
        Button quitter = new Button("Quitter");
        Button augmenter = new Button("+");
        Button baisser = new Button("-");
        TextField nombreAleatoire = new TextField();
        jouer.setDisable(true);
        random.setDisable(true);

        random.setPrefWidth(70);
        nombreClicks.setPrefWidth(35);

        VBox vBox = new VBox(augmenter,nombreAleatoire,baisser);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        HBox hBox = new HBox(jouer,nombreClicks,configurer,random,vBox,quitter);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);


        nombreClicks.textProperty().bind(modele.nombre_clicks.asString());
        nombreAleatoire.textProperty().bind(modele.nombreAleatoire.asString());

        root.setCenter(vueGraphique);
        root.setBottom(hBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root,Constantes.WIDTH_WINDOW,Constantes.HEIGHT_WINDOW);
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false);

        augmenter.setOnAction(event -> modele.setNombreAleatoire(1));

        baisser.setOnAction(event -> modele.setNombreAleatoire(-1));

        quitter.setOnAction(event -> {
            modele.initGrid();
            vueGraphique.initGrid();
            modele.configuration = true;
            configurer.setDisable(true);
            jouer.setDisable(false);
            random.setDisable(false);
            vueGraphique.timeLine.stop();
        });

        jouer.setOnAction(event -> {
            modele.setConfiguration();
            jouer.setDisable(true);
            configurer.setDisable(false);
            random.setDisable(true);
        });

        random.setOnAction(event -> {
            modele.setLampesRandom();
            vueGraphique.initGrid();
            vueGraphique.update();
        });

        configurer.setOnAction(event -> {
            modele.setConfiguration();
            configurer.setDisable(true);
            jouer.setDisable(false);
            random.setDisable(false);
            vueGraphique.timeLine.stop();
            vueGraphique.update();
        });
    }


}
