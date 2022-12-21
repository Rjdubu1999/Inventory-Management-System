package com.example.c482_pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Part> MainPartsTable;
    @FXML
    private TableColumn<Part, Integer> MainPartsIDColumn;
    @FXML
    private TableColumn<Part, String> MainPartsNameColumn;
    @FXML
    private TableColumn<Part, Double> MainPartsPriceColumn;
    @FXML
    private TableColumn<Part, Integer> MainPartsInventoryColumn;
    @FXML private TextField MainPartsTextField;
    @FXML private TextField MainProductTextField;



    ////

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
   // @FXML
   // public void addPartsScreen(ActionEvent event) throws IOException {
      //  Parent addParts = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
      //  Scene scene = new Scene(addParts);

     //   Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

     //   window.setScene(scene);
     //   window.show();
  //  }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}