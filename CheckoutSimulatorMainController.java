package StaffTraining;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class CheckoutSimulatorMainController {
    @FXML
    public Button cashOnlyButton;
    @FXML
    public Button cardOnlyButton;
    @FXML
    public Button tenItemsButton;
    @FXML
    public Button viewProductsButton;
    @FXML
    public TextField runningTotalTF;
    @FXML
    public Button exitButton;
    @FXML
    public VBox topVBox;
    @FXML
    public AnchorPane topAnchorPane;
    @FXML
    public AnchorPane bottomAnchorPane;
    @FXML
    public VBox bottomVBox;
    @FXML
    public SplitPane splitPane;
    @FXML
    public TextField showTotalItemsTF;


    @FXML
    public void viewProductsButtonClicked(ActionEvent actionEvent) throws IOException {
        //create new FXMLLoader, then new scene, new controller, set controller parent, set stage, scene, and show
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductList.fxml"));
        Scene productScene = new Scene(loader.load());
        ProductListController productsController = loader.getController();
        productsController.setParent(CheckoutSimulatorMainController.this);

        Stage productList = new Stage();
        productList.setScene(productScene);
        productList.initModality(Modality.APPLICATION_MODAL);
        productList.show();
    }

    @FXML
    public void cashOnlyButtonClicked(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CashOnlyCheckout.fxml"));
        Scene cashOnlyScene = new Scene(loader2.load());
        CashOnlyCheckoutController cashScannedController = loader2.getController();
        cashScannedController.setParent(CheckoutSimulatorMainController.this);

        Stage cashOnlyStage = new Stage();
        cashOnlyStage.setScene(cashOnlyScene);
        cashOnlyStage.initModality(Modality.NONE);
        cashOnlyStage.show();
    }


    @FXML
    private void exitProgram(){System.exit(0);}


    public void updateSum(int sumA) {
        showTotalItemsTF.setText(String.valueOf(sumA));
    }
}
