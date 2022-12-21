module com.example.c482_pa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.c482_pa to javafx.fxml;
    exports com.example.c482_pa;
}