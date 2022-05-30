package StaffTraining;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;

import java.util.List;

public class ShoppingList extends ObservableListWrapper<Product> {

    public ShoppingList() {
        super(FXCollections.observableArrayList());
    }


}
