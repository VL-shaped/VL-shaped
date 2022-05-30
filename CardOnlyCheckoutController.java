package StaffTraining;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class CardOnlyCheckoutController {
    @FXML
    public VBox leftVBox;
    @FXML
    public TextField runningTotalTF;
    @FXML
    public TextField productCodeTF;
    @FXML
    public Button scanItemButton;
    @FXML
    public Button removeProductButton;
    @FXML
    public TextField cardNumberTF;
    @FXML
    public TextField expiryDateTF;
    @FXML
    public TextField cvvTF;
    @FXML
    public Button processCardPaymentButton;
    @FXML
    public Button closeCheckoutBTN;
    @FXML
    private ListView<Product> cardOnlyScannedListView;
    private ProductList listOfProducts;
    private ScannedList cardOnlyScannedList;
    private MainMenuController parent;

    public void setList(ProductList listOfProducts) {
        //connect list of products to the list view
        this.listOfProducts = listOfProducts;
    }

    public void setParent(MainMenuController par) {
        parent = par;
        // reference the parent window to utilise its methods
    }

    @FXML
    public void initialize() {
        //initialise the list of products
        cardOnlyScannedList = new ScannedList();

        //connect list of products to the list view
        cardOnlyScannedListView.setItems(cardOnlyScannedList);
    }

    @FXML
    private void scannedListChoice() {
        Product chosenProduct = cardOnlyScannedListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void removeProductButtonPressed(){
        int chosenProduct = cardOnlyScannedListView.getSelectionModel().getSelectedIndex();
        if(chosenProduct == -1){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Please select a product to remove then click the remove product from scanned list button", ButtonType.CLOSE);
            alert1.showAndWait();
        } else {
            Product productToRemove = cardOnlyScannedListView.getItems().get(chosenProduct);
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + productToRemove + " from the scanned list?", ButtonType.NO, ButtonType.YES);
            alert2.showAndWait();
            if (alert2.getResult() == ButtonType.YES) {
                cardOnlyScannedList.remove(chosenProduct);
                cardOnlyScannedListView.getSelectionModel().clearSelection();
                runningTotalTF.setText(parent.formatCurrency(calculateRunningTotal()));
            } else {
                if (alert2.getResult() == ButtonType.NO) {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR, "Product Not Removed", ButtonType.OK);
                    alert3.showAndWait();
                }
            }
        }
    }

    @FXML
    private void scanItemButtonPressed(){
        String scanProductCode = productCodeTF.getText();
        if (scanProductCode.equals("")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please Type in a Product Code", ButtonType.CLOSE);
            alert1.showAndWait();
        } else {
            Product scannedProduct = listOfProducts.findProductByProductCode(productCodeTF.getText());
            if (scannedProduct != null) {
                cardOnlyScannedList.add(scannedProduct);
                cardOnlyScannedListView.getSelectionModel().select(scannedProduct);
                cardOnlyScannedListView.scrollTo(scannedProduct);
                //find details from product list and add to scanned list
                productCodeTF.setText("");
                //call the method to get the price of the scanned product(s) and recalculate total
                runningTotalTF.setText(parent.formatCurrency(calculateRunningTotal()));
            } else {
                //if product code is not found, show a warning to the user
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Sorry, Product not found. Please check the product code and try again", ButtonType.OK);
                alert2.showAndWait();
                productCodeTF.setText("");
            }
        }
    }

    @FXML
    private void processCardPaymentButtonPressed(){
        try {
            String cardNumber = cardNumberTF.getText().replaceAll(" ", "");
            String expiryDate = expiryDateTF.getText();
            String cvvNumber = cvvTF.getText();
                if(cardNumber.length() == 16 && expiryDate.length() == 4 && cvvNumber.length() == 3){
                    long cardNum = Long.parseLong(cardNumber);
                    int expiryD = Integer.parseInt(expiryDateTF.getText());
                    int cvvNum = Integer.parseInt(cvvTF.getText());
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION,"Thank You. Payment has been successfully processed", ButtonType.FINISH);
                    alert1.showAndWait();
                    cardNumberTF.setText("");
                    expiryDateTF.setText("");
                    cvvTF.setText("");
                }
                else{
                    Alert alert2 = new Alert(Alert.AlertType.ERROR, "Please check and re-enter valid card details. Card number must be 16-digits long, " +
                            "expiry date must be 4-digits, and CVV number must be 3-digits.", ButtonType.OK);
                    alert2.showAndWait();
                }
        }
            catch(Exception e){
                Alert alert3 = new Alert(Alert.AlertType.WARNING, "Please check and re-enter valid card details. Card number must be 16-digits long, " +
                        "expiry date must be 4-digits, and CVV number must be 3-digits.", ButtonType.OK);
                alert3.showAndWait();
        }
    }

    @FXML
    private void closeCheckoutButtonPressed(){
        Stage thisStage = (Stage) closeCheckoutBTN.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    private double calculateRunningTotal() {
        double sumA = 0;
        //calculate the total price of all items that have been scanned
        //loop through the product list, get the price of each product
        for (Product p : cardOnlyScannedList) {
            if (!cardOnlyScannedList.isEmpty()) {
                sumA = (p.getPrice()) + sumA;
            } else {
                runningTotalTF.setText("");
            }
        }
        return sumA;
    }


}
