/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.product;

import anhtht.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class ProductDAO implements Serializable{
    private List<ProductDTO> productItems;

    public List<ProductDTO> getProductItems() {
        return productItems;
    }
    
    public void searchProduct(String searchValue) 
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        try{
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select name, quantity ,price "
                        + "From Product "
                        + "Where name = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                
                result = stm.executeQuery();
                while (result.next()) {                    
                    String name = result.getString("name");
                    int quantity = result.getInt("quantity");
                    float price = result.getFloat("price");
                    
                    ProductDTO dto = new ProductDTO(null, name, quantity, price, false);
                }
                
                if (this.productItems == null) {
                    this.productItems = new ArrayList<>();
                }//end rs had got many row
            }//end connection is avalible
            
        } finally {
            if (result != null) {
                result.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
