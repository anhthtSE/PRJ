/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import anhtht.registration.RegistrationDAO;
import anhtht.util.MyAppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
//    private final String ERROR_PAGE = "error.html";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        boolean role = false;
        if (admin != null) {
            role = true;
        }
        
        //1. Get context scope
        ServletContext context = this.getServletContext();
        //2. Get SITEMAPS
        Properties siteMaps =(Properties) context.getAttribute("SITEMAPS");
        
        String url = siteMaps.getProperty(MyAppConstants.DispatchFeature.ERROR_PAGE);
        String SearchValue = request.getParameter("lastSearchValue");
        
        try {
            //1. Call DAO
            //1.1 New DAO
            RegistrationDAO dao = new RegistrationDAO();
            //1.2 Call method of DAO
            boolean result = dao.updateAccount(username, password, role);
           
            //2. Process result
            if (result) {
                url = "DispatcherServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + SearchValue;
            }
        } catch (NamingException e) {
            log("UpdateServlet _ SQL " + e.getMessage());
        } catch (SQLException e){
//            e.printStackTrace();
            log("UpdateServlet _ Naming " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            
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
