package com.example.c482_pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.c482_pa.HelloApplication.partModifyIndex;
import static com.example.c482_pa.HelloApplication.productModifyIndex;
import static com.example.c482_pa.Inventory.productInventory;

public class ModifyProduct implements Initializable {
    private boolean Outsourced;
    int productIndex = productModifyIndex();
    private String exception = new String();
    private int productID;
    @FXML
    private TextField ModifyProductIDTextField;
    @FXML
    private TextField ModifyProductNameTextField;
    @FXML
    private TextField ModifyProductInventoryTextField;
    @FXML
    private TextField ModyifyProductPriceTextField;
    @FXML
    private TextField ModifyProductMaxTextField;
    @FXML

    private TextField ModifyProductMinTextField;
    @FXML

    private TextField ModifyProductChangingLabel;
    @FXML

    private Label ChangingLabel;
    @FXML
    private RadioButton ModifyProductInhouseRadioButton;
    @FXML
    private RadioButton ModifyProductOutsourcedRadioButton;

    @FXML
    void ModifyProductCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Confirm Deletion ");
        alert.setContentText("Cancel update for " + ModifyProductNameTextField.getText() + " ?");
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
    void ModifyProductSaveButton(ActionEvent event) throws IOException{
        String productName = ModifyProductNameTextField.getText();
        String productPrice = ModyifyProductPriceTextField.getText();
        String productInventory = ModifyProductInventoryTextField.getText();
        String productMin = ModifyProductMinTextField.getText();
        String productMax = ModifyProductMaxTextField.getText();
        String changingLabel = ModifyProductChangingLabel.getText();
        try {
            exception = Product.validProduct(productName, Integer.parseInt(productMin), Integer.parseInt(productMax), Integer.parseInt(productInventory), Double.parseDouble(productPrice), exception);

            if (exception.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Add Failed");
                alert.setHeaderText("Warning: Error");
                alert.setContentText(exception);
                alert.showAndWait();
            } else {
                if (Outsourced == false) {
                    inHouse inHouseProduct = new inHouse();

                    inHousePart.setPartID(productID);
                    inHousePart.setName(partName);
                    inHousePart.setMax(Integer.parseInt(partMax));
                    inHousePart.setMin(Integer.parseInt(partMin));
                    inHousePart.setPrice(Double.parseDouble(partPrice));
                    inHousePart.setPartMachineID(Integer.parseInt(changingLabel));
                    inHousePart.setInStock(Integer.parseInt(productInventory));
                    Inventory.updateAPart(productIndex, inHouseProduct);

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
