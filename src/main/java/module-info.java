module com.example.ihm_tp_eteinslalumiere {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ihm_tp_eteinslalumiere to javafx.fxml;
    exports com.example.ihm_tp_eteinslalumiere;
    exports com.example.ihm_tp_eteinslalumiere.modele;
    opens com.example.ihm_tp_eteinslalumiere.modele to javafx.fxml;
}