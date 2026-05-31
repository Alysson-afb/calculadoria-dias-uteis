module ifsul.sacprojetct {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens ifsul.sacprojetct to javafx.fxml;
    opens model.classes to javafx.base;
    exports ifsul.sacprojetct;
}
