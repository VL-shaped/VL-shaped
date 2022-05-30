package StaffTraining;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class CashOnlyCheckoutController {
    @FXML
    public Pane cashOnlyPane;
    @FXML
    public HBox topHBox;
    @FXML
    public VBox leftVBox;
    @FXML
    public VBox rightVBox1;
    @FXML
    public TextField enterProductCodeTF;
    @FXML
    public Button removeProductButton;
    @FXML
    public VBox rightVBox2;
    @FXML
    public TextField cashReceivedTF;
    @FXML
    public Button calculateCashChangeButton;
    @FXML
    public TextField cashChangeTF;
    @FXML
    public Button scanItemButton;
    @FXML
    public Button closeCheckoutBTN;
    @FXML
    private ListView<Product> cashOnlyScannedListView;
    @FXML
    public TextField runningTotalTF;
    private ProductList listOfProducts;
    private ScannedList cashOnlyScannedList;
    private MainMenuController parent;

    public void setParent(MainMenuController pa) {
        parent = pa;
        // reference the parent window to utilise its methods
    }

    public void setList(ProductList listOfProducts) {
        //connect list of products to the list view
        this.listOfProducts = listOfProducts;
    }

    @FXML
    public void initialize() {
        //initialise the list of products
        cashOnlyScannedList = new ScannedList();

        //connect list of products to the screen list view
        cashOnlyScannedListView.setItems(cashOnlyScannedList);
    }

    @FXML
    private void scannedListChoice() {
        Product chosenProduct = cashOnlyScannedListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void calculateChangeButtonPressed() {
        try{
            double cashReceived = Double.parseDouble(String.format(cashReceivedTF.getText(), "£ %.2f"));
            double runningTotal = Double.parseDouble(runningTotalTF.getText().replaceFirst("£", ""));
            double change = cashReceived - runningTotal;
            cashChangeTF.setText(parent.formatCurrency(change));}
        catch(Exception e){
            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Try again, Please enter a valid currency amount", ButtonType.OK);
            alert1.showAndWait();
            cashReceivedTF.setText("");
        }
    }

    @FXML
    private void removeProductButtonPressed(){
        int chosenProduct = cashOnlyScannedListView.getSelectionModel().getSelectedIndex();
        if(chosenProduct == -1){
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please select a product to remove then click the remove product from scanned list button", ButtonType.CLOSE);
            alert1.showAndWait();
        } else {
            Product productToRemove = cashOnlyScannedListView.getItems().get(chosenProduct);
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + productToRemove + " from the scanned list?", ButtonType.NO, ButtonType.YES);
            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.YES) {
                cashOnlyScannedList.remove(chosenProduct);
                cashOnlyScannedListView.getSelectionModel().clearSelection();
                runningTotalTF.setText(parent.formatCurrency(runningTotalCalculation()));
            } else {
                if (alert2.getResult() == ButtonType.NO) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Product Not Removed", ButtonType.OK);
                    alert3.showAndWait();
                }
            }
        }
    }

    @FXML
    private void scanItemButtonPressed() {

        String findProductCode = enterProductCodeTF.getText();
        if (findProductCode.equals("")) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Please Type in a Product Code", ButtonType.CLOSE);
            alert1.showAndWait();
        } else {
            Product searchedProduct = listOfProducts.findProductByProductCode(enterProductCodeTF.getText());
            if (searchedProduct != null) {
                cashOnlyScannedList.add(searchedProduct);
                cashOnlyScannedListView.getSelectionModel().select(searchedProduct);
                cashOnlyScannedListView.scrollTo(searchedProduct);
                //find details from product list and add to scannedList
                enterProductCodeTF.setText("");
                runningTotalTF.setText(parent.formatCurrency(runningTotalCalculation()));
            } else {
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Sorry, Product not found. Please check the product code and try again", ButtonType.OK);
                alert2.showAndWait();
                enterProductCodeTF.setText("");
            }
        }
    }

    @FXML
    private void closeCheckoutButtonPressed(){
        Stage thisStage = (Stage) closeCheckoutBTN.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private double runningTotalCalculation() {
        double sumA = 0;
        //calculate the total price of all items that have been scanned
        for (Product p : cashOnlyScannedList) {
            sumA = (p.getPrice()) + sumA;
        }
        return sumA;
    }
}