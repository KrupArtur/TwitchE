module com.twitche.twitche {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;
    requires imgscalr.lib;
    requires java.logging;

    opens com.twitche.twitche to javafx.fxml;
    exports com.twitche.twitche;
    exports com.twitche.twitche.Controller;
    opens com.twitche.twitche.Controller to javafx.fxml;
    exports com.twitche.twitche.Config;
    opens com.twitche.twitche.Config to javafx.fxml;
    exports com.twitche.twitche.Model;
    opens com.twitche.twitche.Model to javafx.fxml;
    exports com.twitche.twitche.Model.Controller;
    opens com.twitche.twitche.Model.Controller to javafx.fxml;
    exports com.twitche.twitche.Implementation;
    opens com.twitche.twitche.Implementation to javafx.fxml;
}