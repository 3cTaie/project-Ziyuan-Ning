module com.teamsolid.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires junit;

    opens com.teamsolid.javaproject to javafx.fxml;
    exports com.teamsolid.javaproject;
}