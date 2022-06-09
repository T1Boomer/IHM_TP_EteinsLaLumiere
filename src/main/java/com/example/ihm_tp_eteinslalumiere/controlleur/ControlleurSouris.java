package com.example.ihm_tp_eteinslalumiere.controlleur;

import com.example.ihm_tp_eteinslalumiere.Constantes;
import com.example.ihm_tp_eteinslalumiere.modele.Lumiere;
import com.example.ihm_tp_eteinslalumiere.vue.VueGraphique;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlleurSouris implements EventHandler<MouseEvent> {

    private final Lumiere modele;
    private final VueGraphique vueGraphique;
    private final int mouseX;
    private final int mouseY;

    public ControlleurSouris(Lumiere modele, VueGraphique vueGraphique,int x, int y) {
        this.modele = modele;
        this.vueGraphique = vueGraphique;
        this.mouseX = x;
        this.mouseY = y;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (!modele.estFini()) {
            if (modele.configuration)
                modele.basculerLampe(mouseX, mouseY);
            else {
                modele.changerLampe(mouseX, mouseY);
                modele.nombre_clicks.set(modele.nombre_clicks.get() + 1);
            }
            vueGraphique.update();
            if (modele.estFini()){
                if(modele.nombre_clicks.get() != 1)
                    System.out.println("Vous avez gagné en " + modele.nombre_clicks.get() + " coups.\n");
                else
                    System.out.println("Vous avez gagné en " + modele.nombre_clicks.get() + " coup.\n");
            }
        }
    }

}
