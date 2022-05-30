package StaffTraining;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

public class ScannedList extends ObservableListWrapper<Product> {

    public ScannedList() {
        super(FXCollections.observableArrayList());
    }

    /*public void addProduct(String productCode, String description, double price, int stockLevel){
        super.add(new Product(productCode, description, price, stockLevel));
    }

    public void deleteProduct(String productCode){
        Product productToDelete = listOfProducts.findProductByProductCode(productCode);
        super.remove(productToDelete);
    }**/
}
