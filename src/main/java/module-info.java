module org.example.cineboot {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive org.json;


    opens org.example.cineboot to javafx.fxml;
    exports org.example.cineboot;
    exports org.example.cineboot.gui;
    opens org.example.cineboot.gui to javafx.fxml;
    exports org.example.cineboot.negocio;
    opens org.example.cineboot.negocio to javafx.fxml;
}