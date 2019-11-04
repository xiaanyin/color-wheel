module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires toml4j;
    requires gson;
    requires java.sql;

    opens app to javafx.fxml;
    exports app;
}