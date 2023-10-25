module com.example.newwords {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.almasb.fxgl.all;


    opens com.example.newwords to javafx.fxml;
    exports com.example.newwords;

    opens Game to javafx.fxml;
    exports Game;
}