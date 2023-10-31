module com.example.newwords {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.almasb.fxgl.all;
    requires org.apache.httpcomponents.httpclient;
    requires json.simple;
    requires org.apache.httpcomponents.httpcore;


    opens com.example.newwords to javafx.fxml;
    exports com.example.newwords;

    opens Game to javafx.fxml;
    exports Game;
}