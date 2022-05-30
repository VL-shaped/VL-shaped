package StaffTraining;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;


public class ProductList extends ObservableListWrapper<Product> {

    public ProductList() {
        super(FXCollections.observableArrayList());
    }

    /**
     * method for adding a new product to the product list
     * @param productCode to be added to super class (observable array list)
     * @param description to be added to super class (observable array list)
     * @param price to be added to super class (observable array list)
     * @param stockLevel to be added to super class (observable array list)
     */
    public void addProduct(String productCode, String description, double price, int stockLevel){
        super.add(new Product(productCode, description, price, stockLevel));
    }

    /**
     * search the product list by product code
     * @param productCode of the searched product
     * @return index number location of searched product, if found
     */
     public Product findProductByProductCode (String productCode){
         Product p;
         int indexNumber = -1;
         for(int i=0; i<super.size(); i++){
             p = super.get(i); //get element from super class (List)
             if(p.getProductCode().equals(productCode)){ //if searched product is in the list
                 indexNumber = i;
                 break;
             }
         }
         if(indexNumber == -1){
             return null; //if product not found in the list
         }else{
             return super.get(indexNumber);
         }
     }

    /**
     * search the product list by description
     * @param description of the searched product
     * @return index number location of searched product, if found
     */
    public Product findProductByDescription (String description){
        Product p;
        int indexNumber = -1;
        for(int i=0; i<super.size(); i++){
            p = super.get(i);
            if(p.getDescription().equals(description)){
                indexNumber = i;
                break;
            }
        }
        if(indexNumber == -1){
            return null;
        }else{
            return super.get(indexNumber);
        }
    }



}
