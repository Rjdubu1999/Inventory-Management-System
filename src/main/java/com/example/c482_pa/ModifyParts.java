package com.example.c482_pa;

import javafx.event.ActionEvent;
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
import java.time.OffsetTime;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.c482_pa.HelloApplication.partModifyIndex;
import static com.example.c482_pa.Inventory.getPartInventory;


public class ModifyParts implements Initializable {
    private boolean Outsourced;
    int partIndex = partModifyIndex();
    private String exception = new String();
    private int partID;
    @FXML
    private TextField ModifyPartIDTextField;
    @FXML
    private TextField ModifyPartNameTextField;
    @FXML
    private TextField ModifyPartInventoryTextField;
    @FXML
    private TextField ModyifyPartPriceTextField;
    @FXML
    private TextField ModifyPartMaxTextField;
    @FXML

    private TextField ModifyPartMinTextField;
    @FXML

    private TextField ModifyPartChangingLabel;
    @FXML

    private Label ChangingLabel;
    @FXML
    private RadioButton ModifyInhouseRadioButton;
    @FXML
    private RadioButton ModifyOutsourcedRadioButton;

    @FXML
    void ModifyPartsCancelButton(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Confirm Deletion ");
        alert.setContentText("Cancel update for " + ModifyPartNameTextField.getText() + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            System.out.println("Part add canceled");
            Parent cancelPart = FXMLLoader.load(getClass().getResource("HelloApplication.fxml"));
            Scene scene = new Scene(cancelPart);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }
        else{
            System.out.println("Canceled. Please complete form. ");
        }
    }

    @FXML
    void ModifyPartSaveButton(ActionEvent event) throws IOException{
        String partName = ModifyPartNameTextField.getText();
        String partPrice = ModyifyPartPriceTextField.getText();
        String partInventory = ModifyPartInventoryTextField.getText();
        String partMin = ModifyPartMinTextField.getText();
        String partMax = ModifyPartMaxTextField.getText();
        String changingLabel = ModifyPartChangingLabel.getText();
        try {
            exception = Part.validPart(partName, Integer.parseInt(partMin), Integer.parseInt(partMax), Integer.parseInt(partInventory), Double.parseDouble(partPrice), exception);

            if (exception.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Part Add Failed");
                alert.setHeaderText("Warning: Error");
                alert.setContentText(exception);
                alert.showAndWait();
            } else {
                if (Outsourced == false) {
                    inHouse inHousePart = new inHouse();

                    inHousePart.setPartID(partID);
                    inHousePart.setName(partName);
                    inHousePart.setMax(Integer.parseInt(partMax));
                    inHousePart.setMin(Integer.parseInt(partMin));
                    inHousePart.setPrice(Double.parseDouble(partPrice));
                    inHousePart.setPartMachineID(Integer.parseInt(changingLabel));
                    inHousePart.setInStock(Integer.parseInt(partInventory));
                    Inventory.updateAPart(partIndex, inHousePart);

                } else {
                    Outsourced outSourcedPart = new Outsourced();
                    outSourcedPart.setPartID(partID);
                    outSourcedPart.setName(partName);
                    outSourcedPart.setPrice(Double.parseDouble(partPrice));
                    outSourcedPart.setMin(Integer.parseInt(partMin));
                    outSourcedPart.setMax(Integer.parseInt(partMax));
                    outSourcedPart.setCompanyName(changingLabel);
                    outSourcedPart.setInStock(Integer.parseInt(partInventory));
                }

                Parent modifyProductCancelButton = FXMLLoader.load(getClass().getResource("HelloApplication.fxml"));
                Scene scene = new Scene(modifyProductCancelButton);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Part Add Error ");
            alert.setHeaderText("Error ");
            alert.setContentText("Remove Blank Text ");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Part part = getPartInventory().get(partIndex);
    partID = getPartInventory().get(partIndex).getPartID();
    ModifyPartNameTextField.setText(part.getPartName());
    ModifyPartIDTextField.setText("Part ID set automatically to: " + partID);
    ModyifyPartPriceTextField.setText((Double.toString(part.getPrice())));
    ModifyPartMaxTextField.setText(Integer.toString(part.getMax()));
    ModifyPartMinTextField.setText(Integer.toString(part.getMin()));
    ModifyPartInventoryTextField.setText(Integer.toString(part.getInStock()));
    if(part instanceof inHouse){
        ModifyPartChangingLabel.setText(Integer.toString(((inHouse)getPartInventory().get(partIndex)).getPartMachineId()));
        ChangingLabel.setText("Machine ID");
        ModifyInhouseRadioButton.setSelected(true);
    }
    else {
        ModifyPartChangingLabel.setText(((com.example.c482_pa.Outsourced)getPartInventory().get(partIndex)).getCompanyName());
        ChangingLabel.setText("Company Name");
        ModifyOutsourcedRadioButton.setSelected(true);
    }
    }
}





