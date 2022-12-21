package com.example.c482_pa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import static com.example.c482_pa.Inventory.*;

public class HelloApplication extends Application {

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
    @FXML
    private TableView<Product> MainProductTable;
    @FXML
    private TableColumn<Product, Integer> MainProductIDColumn;
    @FXML
    private TableColumn<Product, String> MainProductNameColumn;
    @FXML
    private TableColumn<Product, Double> MainProductPriceColumn;
    @FXML
    private TableColumn<Product, Integer> MainProductInventoryColumn;
    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    public static int partModifyIndex(){
        return modifyPartIndex;
    }
    public static int productModifyIndex(){
        return modifyProductIndex;
    }
    private static int modifyProductIndex;
    @FXML private TextField MainPartsTextField;
    @FXML private TextField MainProductTextField;
    @FXML
    void EraseSearchPart(ActionEvent event) throws IOException{
        updateMainPartTable();
        MainPartsTextField.setText("");
    }
    @FXML
    void EraseSearchProduct(ActionEvent event) throws IOException{
        updateMainProductTable();
        MainProductTextField.setText("");
    }
    @FXML
    void AddPartsScreen(ActionEvent event) throws IOException {

        Parent addParts = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void AddProductScreen(ActionEvent event) throws IOException {
        Parent addProducts = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(addProducts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void ModifyPartsScreen(ActionEvent event) throws IOException{
        Parent ModifyParts = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        modifyPartIndex = getPartInventory().indexOf(modifyPart);
        Scene scene = new Scene(ModifyParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }@FXML
    void ModifyProductScreen(ActionEvent event) throws IOException{
        Parent ModifyProduct = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        modifyProductIndex = getProductInventory().indexOf(modifyProduct);
        Scene scene = new Scene(ModifyProduct);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML
    void MainScreenExit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Close Program");
        alert.setHeaderText("Click Exit To close Program");
        alert.setContentText("Goodbye");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
        System.exit(0);
        }
        else{
            System.out.println("Exit Canceled.");
        }
    }
    @FXML
    void MainSearchProductsButton(ActionEvent event) throws IOException{
        String searchProduct = MainProductTextField.getText();
        int productIndex = -1;
        if(Inventory.lookProductUp(searchProduct) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error: Search");
            alert.setHeaderText("Product not found. ");
            alert.setContentText("Search Term Returns No Value. ");
            alert.showAndWait();
        }
        else{
            productIndex = Inventory.lookProductUp(searchProduct);
            Product temporaryProduct = Inventory.getProductInventory().get(productIndex);
            ObservableList<Product> temporaryProductList = FXCollections.observableArrayList();
            temporaryProductList.add(temporaryProduct);
        }
    }
     @FXML
    void MainSearchPartsButton(ActionEvent event) throws IOException{
        String searchPart = MainPartsTextField.getText();
        int partIndex = -1;
        if(Inventory.lookProductUp(searchPart) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error: Search");
            alert.setHeaderText("Product not found. ");
            alert.setContentText("Search Term Returns No Value. ");
            alert.showAndWait();
        }
        else{
            partIndex = Inventory.lookUpAPart(searchPart);
            Part temporaryPart = Inventory.getPartInventory().get(partIndex);
            ObservableList<Part> temporaryProductList = FXCollections.observableArrayList();
            temporaryProductList.add(temporaryPart);
            MainPartsTable.setItems(temporaryProductList);
        }
    }

    @FXML
    void MainClearPart(ActionEvent event) throws IOException {
        Part part = MainPartsTable.getSelectionModel().getSelectedItem();
        if (partDeletion(part)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Error: Part Delete");
            alert.setHeaderText("Can not Delete");
            alert.setContentText("This part is already in use in a product");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Confirm");
            alert.setContentText("Do you want to delete" + part.getPartName() + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK){
                deleteAPart(part);
                updateMainPartTable();
                System.out.println("Part" + part.getPartName()+ "was deleted.");
                System.out.println("Part" + part.getPartName() + "was not deleted.");
            }
        }

    }

    @FXML
    void MainClearProduct(ActionEvent event) throws IOException{
       Product product = MainProductTable.getSelectionModel().getSelectedItem();
           Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
           alert.initModality(Modality.NONE);
           alert.setTitle("Product Delete");
           alert.setHeaderText("Confirm");
           alert.setContentText("Are you sure you want to remove" + product.getProductName()+"?");
           Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
           removeProduct(product);
           updateMainProductTable();
           System.out.println("Product" + product.getProductName() + " was deleted.");
       }
       else{
           System.out.println("Product" + product.getProductName() + " was not removed.");
       }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Ryan Wilkinson C482 Performance Assessment");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb){
        MainPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        MainPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        MainPartsInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().partInventoryProperty().asObject());
        MainPartsPriceColumn.setCellValueFactory(cellData ->cellData.getValue().partPriceProperty().asObject());
        MainProductIDColumn.setCellValueFactory(cellData ->cellData.getValue().productIDProperty().asObject());
        MainProductNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        MainProductInventoryColumn.setCellValueFactory(cellData -> cellData.getValue().productInventoryProperty().asObject());
        MainProductPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());

    }
    public void updateMainPartTable(){
        MainPartsTable.setItems(getPartInventory());
   }
    public void updateMainProductTable(){
        MainProductTable.setItems(getProductInventory());
    }
    public static void main(String[] args) {
        launch();
    }
}