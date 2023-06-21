/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class CartObj implements Serializable{
    //SKU
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String sku){
        if(sku == null){
            return;
        }
        
        if (sku.trim().isEmpty()) {
            return;
        }

        //1. Check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }//items have not existed
        //2. Check extsted item
        int quantity = 1;
        if (this.items.containsKey(sku)) {
            quantity = this.items.get(sku) + 1;
        }//end item has existed in items
        //3. Update items
        this.items.put(sku, quantity);
    }
    
    public void removeItemFromCart(String sku){
        
        //1. Check existed items
        if (this.items == null) {
            return;
        }
        //2. Check existed item
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            //khi remove về value = 0 thì gia
            //Ko bao giờ để item exist khi value =0
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }//end item has existed in items
    }
}
