package StaffTraining;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.NumberFormat;


public class ProductListController {
    @FXML
    public Button clearTextButton;
    @FXML
    public Button returnToMenuButton;
    @FXML
    public javafx.scene.layout.Pane Pane;
    @FXML
    public TextField totalItemsTF;
    @FXML
    private ListView<Product> productListView;
    @FXML
    public VBox leftVBox;
    @FXML
    public VBox rightVBox;
    @FXML
    public TextField productCodeTF;
    @FXML
    public TextField descriptionTF;
    @FXML
    public TextField priceTF;
    @FXML
    public TextField stockLevelTF;
    @FXML
    public Button searchButton;
    @FXML
    public Button addItemButton;
    @FXML
    public Label topHBox;

    private MainMenuController parent;
    private ProductList listOfProducts;


    public void setParent(MainMenuController p){
        parent = p;
        // reference the parent window so we can use its methods
    }
    public void setList(ProductList listOfProducts){
        //connect list of products to the screen list view
        this.listOfProducts = listOfProducts;
        productListView.setItems(listOfProducts);
    }

    @FXML
    private void productListChoice(){
        Product chosenProduct = productListView.getSelectionModel().getSelectedItem();
        if(chosenProduct !=null){
            setTextFields(chosenProduct);
            //if the chosen product is not null, display the product attributes in the test fields
        }
    }
    @FXML
    private void addItemButtonPressed(){
        if(productCodeTF.getText().equals("") || descriptionTF.getText().equals("") || priceTF.getText().equals("") || stockLevelTF.getText().equals("")) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Please complete ALL fields", ButtonType.CLOSE);
            alert1.showAndWait();
            //if text fields are empty show an alert informing the user
       }else {
            try{
                String newProductCode = productCodeTF.getText().toLowerCase();
                if(newProductCode.length() == 5){
                    String newDescription = descriptionTF.getText();
                    double newPrice = Double.parseDouble(priceTF.getText());
                    NumberFormat nf= NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);
                    double p = Double.parseDouble(nf.format(newPrice));
                    int newStockLevel = Integer.parseInt(stockLevelTF.getText());
                    listOfProducts.addProduct(newProductCode, newDescription, p, newStockLevel);
                    //add the new product to the list of products
                    Product newProduct = listOfProducts.findProductByProductCode(newProductCode);
                    productListView.getSelectionModel().select(newProduct);
                    //update the list view to include the newly added product
                    productListView.scrollTo(newProduct);
                    setTextFields(newProduct);
                }
                else{
                    Alert alert2 = new Alert(Alert.AlertType.WARNING, "Please ensure the product code is 5 digits long", ButtonType.CLOSE);
                    alert2.showAndWait();
                }
            }
            catch(Exception e){
                    Alert alert3 = new Alert(Alert.AlertType.WARNING, "Please check and re-enter valid product details", ButtonType.CLOSE);
                    alert3.showAndWait();

            }
        }
    }

    @FXML
    private void clearTextButtonPressed(){
        //if the user clicks the clear text button, call the clear all text method
        clearAllText();
        productListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchButtonPressed (){
        //search for the product either by product code or description
        Product searchedProduct;
        String findProductCode = productCodeTF.getText();
        String findDescription = descriptionTF.getText().toLowerCase();
        if(findProductCode.equals("") && findDescription.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.WARNING, "Please enter a Product Code or Description to search for", ButtonType.OK);
            alert1.showAndWait();
            //if the search text fields are empty, show a warning to the user
        } else {
            if(!findProductCode.equals("")){
                searchedProduct = listOfProducts.findProductByProductCode(findProductCode);
                //if the product code text field is not empty, call the find by product code method
            }else{
                searchedProduct = listOfProducts.findProductByDescription(findDescription);
                //if the description text field is empty, call the find by description method
            }
            if(searchedProduct == null){
                Alert alert2 = new Alert(Alert.AlertType.WARNING, "Sorry, Product not found, please try again", ButtonType.OK);
                //if the product is not in the list, show a warning and reset the text field to empty
                alert2.showAndWait();
                productCodeTF.setText("");
            } else {
                productListView.getSelectionModel().select(searchedProduct);
                //if the product is found, display the attributes in the relevant text fields
                productListView.scrollTo(searchedProduct);
                setTextFields(searchedProduct);
            }
        }
    }

    //the method below sets all text fields on the stock control page to an empty string
    private void clearAllText(){
        productCodeTF.setText("");
        descriptionTF.setText("");
        priceTF.setText("");
        stockLevelTF.setText("");
    }

    //the method below fills the text fields with the relevant product details
    private void setTextFields(Product p){
        productCodeTF.setText(p.getProductCode());
        descriptionTF.setText(p.getDescription());
        priceTF.setText(String.valueOf(p.getPrice()));
        stockLevelTF.setText(String.valueOf(p.getStockLevel()));
    }

    //the method below iterates through the list of products and sums up the stock levels to produce a total stock figure
    //this can be viewed on the product page and the main menu
    @FXML
    private void totalItemsInStock(){
        int sumA = 0;
        for (Product p : parent.listOfProducts) {
            sumA =  (p.getStockLevel() + sumA);
            totalItemsTF.setText(String.valueOf(sumA));
        }
    }

    @FXML
    private void returnToMenuButtonPressed(){
        Stage thisStage = (Stage) returnToMenuButton.getScene().getWindow();
        thisStage.close();
    }
}
