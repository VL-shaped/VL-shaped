package StaffTraining;

import java.io.*;

public class Product implements Serializable{
    private String productCode;
    private String description;
    private double price;
    private int stockLevel;

    public Product() {
    }

    public Product(String productCode, String description, double price, int stockLevel) {
        this.productCode = productCode;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
    }

    public Product(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String toString(){ return "Code: " + productCode + ", Desc: " + description + ", Price: £" + price + ", Stock: " + stockLevel; }
}
