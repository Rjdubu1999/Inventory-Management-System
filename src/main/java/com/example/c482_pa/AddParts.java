package com.example.c482_pa;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddParts implements Initializable {
    @FXML
    private Label changingLabel;
    @FXML
    private TextField AddPartIDTextField;
    @FXML
    private TextField AddPartNameTextField;
    @FXML
    private TextField AddPartInventoryTextField;
    @FXML
    private TextField AddPartPriceTextField;
    @FXML
    private TextField AddPartMaxTextField;
    @FXML
    private TextField AddPartMinTextField;
    @FXML
    private TextField AddPartMachineIDTextField;

    private boolean Outsourced;
    private String exception = new String();
    private int partID;

    @FXML
    void inHouseRadioButton(ActionEvent event) {
        Outsourced = false;
        changingLabel.setText("Machine ID");
    }

    @FXML
    void addPartsOutsourcedRadioButton(ActionEvent event) {
        Outsourced = true;
        changingLabel.setText("Company Name");
    }

    @FXML
    void addPartsSaveButton(ActionEvent event) throws IOException {
        String partName = AddPartNameTextField.getText();
        String partID = AddPartIDTextField.getText();
        String partPrice = AddPartPriceTextField.getText();
        String partInventory = AddPartInventoryTextField.getText();
        String partMax = AddPartMaxTextField.getText();
        String partMin = AddPartMinTextField.getText();
        String partMachineID = AddPartMachineIDTextField.getText();

        try {
            exception = Part.validPart(partName, Integer.parseInt(partMin), Integer.parseInt(partMax), Integer.parseInt(partInventory), Double.parseDouble(partPrice), exception);

            if (exception.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Part Add Failed");
                alert.setHeaderText("Warning: Error");
                alert.setContentText(exception);
                alert.showAndWait();
                exception = "";
            } else {
                if (Outsourced == false) {
                    inHouse inHousePart = new inHouse();

                    inHousePart.setPartID(Integer.parseInt(partID));
                    inHousePart.setName(partName);
                    inHousePart.setMax(Integer.parseInt(partMax));
                    inHousePart.setMin(Integer.parseInt(partMin));
                    inHousePart.setPrice(Double.parseDouble(partPrice));
                    inHousePart.setPartMachineID(Integer.parseInt(partMachineID));
                    //create inventory class

                } else {
                    Outsourced outSourcedPart = new Outsourced();
                    outSourcedPart.setPartID(Integer.parseInt(partID));
                    outSourcedPart.setName(partName);
                    outSourcedPart.setPrice(Double.parseDouble(partPrice));
                    outSourcedPart.setMin(Integer.parseInt(partMin));
                    outSourcedPart.setMax(Integer.parseInt(partMax));
                    outSourcedPart.setCompanyName(partMachineID);
                    //add to inventory class
                }

                Parent partsSaveButton = FXMLLoader.load(getClass().getResource("HelloApplication.fxml"));
                Scene scene = new Scene(partsSaveButton);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }
        }catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Part Add Error.");
                    alert.setHeaderText("Error.");
                    alert.setContentText("Remove Blank Text");
                    alert.showAndWait();
                }
            }
            @FXML
    void addPartsCancelButton(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Confirm Delete");
        alert.setContentText("Do you want to delete part" + AddPartNameTextField.getText() + "?");
                Optional<ButtonType> result = alert.showAndWait();
        }



        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
