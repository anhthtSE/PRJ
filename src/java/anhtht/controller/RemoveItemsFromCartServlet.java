/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import anhtht.cart.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RemoveItemsFromCartServlet", urlPatterns = {"/RemoveItemsFromCartServlet"})
public class RemoveItemsFromCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            //1. Cust gors to his/her cart's place
            
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Cust takes his/her cart
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Remove items from cart
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            for (String selectedItem : selectedItems) {
                                cart.removeItemFromCart(selectedItem);
                            }//end for remove each item
                            
                            //giỏ trong tay mình
                            //update attribute
                            session.setAttribute("CART", cart);
                        }//end items must be selected
                    }//end items have not existed
                }//end cart has existed
            }//end session has existed
        } finally {
            //5. Call the View cart function again using URL rewriting
            String urlRewriting = "cartController?"
                    + "btAction=View Your Cart";
            //SD sendRedict do ko muốn trùm
            response.sendRedirect(urlRewriting);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
