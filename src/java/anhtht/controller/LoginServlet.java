/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import anhtht.registration.RegistrationDAO;
import anhtht.registration.RegistrationDTO;
import anhtht.util.MyAppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_PAGE = "search.jsp";
//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "invalid.html";
//    private final String INVALID_PAGE = "invalidPage";
    /*
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
        //1. Get context scope
        ServletContext context = this.getServletContext();
        //2. Get SITEMAPS
        Properties siteMaps =(Properties) context.getAttribute("SITEMAPS");
        
        String button = request.getParameter("btAction");
        String url = siteMaps.getProperty(MyAppConstants.DispatchFeature.INVALID_PAGE);
        
        try{
            //check value
            if (button.equals("Login")) {
                //đúng button mới lấy được parameter
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                
                //1.call DAO
                //new DAO & call method of DAO
                RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.checkLogin(username, password);
                RegistrationDTO result = dao.checkLogin(username, password);
                //2.process result
                if (result != null) {
                    url = siteMaps.getProperty(MyAppConstants.DispatchFeature.SEARCH_PAGE);
//                    <-- Using cookie -->
//                    //Sau  khi login compeled
//                    Cookie cookie = new Cookie(username, password);
//                    //Set cookie time exist
//                    cookie.setMaxAge(60 * 5);
//                    //add cookie into res obj
//                    response.addCookie(cookie);
                   HttpSession session = request.getSession();
                   session.setAttribute("USER", result);
                }// end user had existed
            }// end if user clicked Loign
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch(NamingException ex){
            ex.printStackTrace();
        }finally{
            response.sendRedirect(url);
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
