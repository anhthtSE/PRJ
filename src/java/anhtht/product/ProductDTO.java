/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.product;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class ProductDTO implements Serializable{
    private String sku;
    private String name;
    private int quanity;
    private float price;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String sku, String name, int quanity, float price, boolean status) {
        this.sku = sku;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
        this.status = status;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quanity
     */
    public int getQuanity() {
        return quanity;
    }

    /**
     * @param quanity the quanity to set
     */
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
