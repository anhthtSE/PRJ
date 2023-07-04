/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import anhtht.util.MyAppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {
    //PAGE
//    private final String LOGIN_PAGE = "login.html";
//    private final String LOGIN_PAGE = " ";
    //JSP PAGE
//    private final String VIEW_CART_PAGE = "viewCart.jsp";
//    //CONTROLLER
////    private final String LOGIN_SERVLET = "LoginServlet";
////    private final String LOGIN_SERVLET = "loginController";
////    private final String SEARCH_RESULT_SERVLET = "SearchLastNameServlet";
////    private final String SEARCH_RESULT_SERVLET = "searchController";
//    private final String DELETE_SERVLET = "DeleteServlet";
//    private final String UPDATE_SERVLET = "UpdateServlet";
//    private final String START_UP_CONTROLLER = "StartUpServlet";
//    private final String LOG_OUT_SERVLET = "LogoutServlet";
//    private final String ADD_TO_CART_SERVLET = "AddItemToCartServlet";
//    private final String REMOVE_ITEM_FROM_CART_SERVLET ="RemoveItemsFromCartServlet";
//    private final String CREATE_NEW_ACCOUNT_SERVLET = "CreateNewAccountServlet";
//    private final String SEARCH_ITEMS_PRODUCT_SERVLET = "SearchItemProductServlet";
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
        PrintWriter out = response.getWriter();
        
        String button = request.getParameter("btAction");
        //1. Get context scope
        ServletContext context = this.getServletContext();
        //2. Get SITEMAPS
        Properties siteMaps =(Properties) context.getAttribute("SITEMAPS");
        
//        String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(MyAppConstants.DispatchFeature.LOGIN_PAGE);
        try {
            if (button == null) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.START_UP_SERVLET);
            } else if (button.equals("Login")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.LOGIN_SERVLET);
            } else if (button.equals("Search")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.SEARCH_RESULT_SERVLET);
            } else if (button.equals("Delete")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.DETELET_SERVLET);
            } else if (button.equals("Update")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.UPDATE_SERVLET);
            } else if (button.equals("Logout")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.LOGOUT_SERVLET);
            } else if (button.equals("Add Book to your Cart")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.ADD_ITEM_TO_CART_SERVLET);
            } else if (button.equals("View Your Cart")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.VIEW_CART_CONTROL);
            } else if (button.equals("Remove Select Items")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.REMOVE_ITEM_FROM_CART_SERVLET);
            } else if (button.equals("Create New Account")) {
                url = siteMaps.getProperty(MyAppConstants.DispatchFeature.CREATE_NEW_ACCOUNT_SERVLET);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
            out.close();
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
