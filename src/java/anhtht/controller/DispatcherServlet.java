/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_RESULT_SERVLET = "SearchLastNameServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOG_OUT_SERVLET = "LogoutServlet";
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
        String url = LOGIN_PAGE;
        
        try {
            if (button == null) {
                url = START_UP_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_RESULT_SERVLET;
            } else if (button.equals("Delete")) {
                url = DELETE_SERVLET;
            } else if (button.equals("Update")) {
                url = UPDATE_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOG_OUT_SERVLET;
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
