package com.example.user.inventory_management_system.Model;

/**
 * Created by user on 27-Feb-19.
 */

public class Sales {
    private String productID;
    private String productName;
    private String productCategory;
    private String productQuantity;

    public Sales(){

    }
    public Sales( String productID, String productName, String productCategory, String productQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
}
