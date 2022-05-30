package StaffTraining;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            primaryStage.setTitle("Main Menu - Checkout Simulator");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();
        }

        public static void main(String[] args) { launch(args); }


    }
