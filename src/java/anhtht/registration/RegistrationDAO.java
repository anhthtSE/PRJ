/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.registration;

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
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
        throws SQLException, /*ClassNotFoundException*/NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select Username "
                        + "From Registration "
                        + "Where Username = ? "
                        + "And Password = ?";
                //3. Create statement Obj (ĐK: check connection, SQL String)
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    result = true;
                }//end rs had got only one row
            }//end connection is available
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> listAccounts;
    
    public List<RegistrationDTO> getListAccounts(){
        return listAccounts;
    }
    
    public void searchLastName(String searchValue)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            //1.1 Check connection
            if (con != null) {
                //2. Create SQL String
                String url = "Select Username, Password, Lastname, isAdmin "
                        + "From Registration "
                        + "Where Lastname like ?";
                //3. Create statement Obj (ĐK: check connection, SQL String)
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                while (rs.next()) {                    
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String lastname = rs.getString("Lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    RegistrationDTO dto = 
                            new RegistrationDTO(username, password, lastname, role);
                    
                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<RegistrationDTO>();
                    }
                    
                    this.listAccounts.add(dto);
                }//end rs had got many row
            }//end connection is available
        } finally{
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deteleAccount(String username)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Make connect
            con = DBHelper.makeConnection();
            //1.1 Check connection
            if (con!= null) {
                //2. Create SQL String
                String url = "Delete "
                        + "From Registration "
                        + "Where username = ? ";
                //3.Create statement Obj
                stm = con.prepareStatement(url);
                stm.setString(1, username);
                //4. Exexcute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    return true;
                }
            }//end connection is available
            
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con!= null) {
                con.close();
            }
        }
        return false;
        
    }
}
