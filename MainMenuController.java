package StaffTraining;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;


public class MainMenuController {
    @FXML
    public Button cashOnlyButton;
    @FXML
    public Button cardOnlyButton;
    @FXML
    public Button viewProductsButton;
    @FXML
    public Button exitButton;
    @FXML
    public VBox topVBox;
    @FXML
    public AnchorPane topAnchorPane;
    @FXML
    public AnchorPane bottomAnchorPane;
    @FXML
    public SplitPane splitPane;
    @FXML
    public TextField showTotalItemsTF;
    public ProductList listOfProducts;
    @FXML
    public Button itemsInStockBTN;


    @FXML
    private void viewProductsButtonClicked(ActionEvent actionEvent) throws IOException {
        //create new FXMLLoader, then new scene, new controller, set controller parent, set stage, scene, and show
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductList.fxml"));
        Scene productScene = new Scene(loader.load());
        ProductListController productsController = loader.getController();
        productsController.setParent(MainMenuController.this);
        productsController.setList(listOfProducts);

        Stage productList = new Stage();
        productList.setTitle("Product Page - Stock Control");
        productList.setScene(productScene);
        productList.initModality(Modality.APPLICATION_MODAL);
        productList.show();
    }

    @FXML
    private void cashOnlyButtonClicked(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CashOnlyCheckout.fxml"));
        Scene cashOnlyScene = new Scene(loader2.load());
        CashOnlyCheckoutController cashScannedController = loader2.getController();
        cashScannedController.setList(listOfProducts);
        cashScannedController.setParent(MainMenuController.this);

        Stage cashOnlyStage = new Stage();
        cashOnlyStage.setTitle("Cash Only Checkout");
        cashOnlyStage.setScene(cashOnlyScene);
        cashOnlyStage.initModality(Modality.NONE);
        cashOnlyStage.show();
    }

    @FXML
    private void cardOnlyButtonPressed(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("CardOnlyCheckout.fxml"));
        Scene cardOnlyScene = new Scene(loader3.load());
        CardOnlyCheckoutController cardScannedController = loader3.getController();
        cardScannedController.setList(listOfProducts);
        cardScannedController.setParent(MainMenuController.this);

        Stage cardOnlyStage = new Stage();
        cardOnlyStage.setTitle("Card Only Checkout");
        cardOnlyStage.setScene(cardOnlyScene);
        cardOnlyStage.initModality(Modality.NONE);
        cardOnlyStage.show();
    }

    @FXML
    private void exitProgram(){System.exit(0);}

    @FXML
    private void totalItemsSum() {
        int sum = 0;
        for (Product p : listOfProducts) {
            sum = (p.getStockLevel() + sum);
            showTotalItemsTF.setText(String.valueOf(sum));
        }
    }

    @FXML
    public void initialize(){
        //initialise the global list of products
        listOfProducts = new ProductList();

        listOfProducts.addProduct("00001", "beans", 00.44, 100);
        listOfProducts.addProduct("00002", "tomatoes", 00.89, 110);
        listOfProducts.addProduct("00003", "mushrooms", 00.99, 121);
        listOfProducts.addProduct("00004", "eggs", 00.89, 320);
        listOfProducts.addProduct("00005", "spam", 01.89, 230);
        listOfProducts.addProduct("00006", "bacon", 02.39, 198);
        listOfProducts.addProduct("00007", "bread", 00.99, 78);
        listOfProducts.addProduct("00008", "black pudding", 01.79, 99);
        listOfProducts.addProduct("00009", "hash browns", 00.99, 33);
        listOfProducts.addProduct("00010", "sausages", 03.09, 86);
        listOfProducts.addProduct("00011", "tuna", 00.84, 100);
        listOfProducts.addProduct("00012", "mayonnaise", 00.89, 86);
        listOfProducts.addProduct("00013", "ham", 02.99, 299);
        listOfProducts.addProduct("00014", "apple", 00.39, 10);
        listOfProducts.addProduct("00015", "banana", 00.89, 8);
        listOfProducts.addProduct("00016", "melon", 01.39, 49);
        listOfProducts.addProduct("00017", "spaghetti", 00.99, 77);
        listOfProducts.addProduct("00018", "carrot cake", 01.79, 99);
        listOfProducts.addProduct("00019", "madeira cake", 00.99, 100);
        listOfProducts.addProduct("00020", "turkey slices", 03.09, 100);
    }

    //format the cost of a product(s) to the UK currency british pounds
    public String formatCurrency(double cost){
        NumberFormat ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        return ukCurrency.format(cost);
    }
}
