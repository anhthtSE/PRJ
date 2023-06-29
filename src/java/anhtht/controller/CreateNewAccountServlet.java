/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtht.controller;

import anhtht.registration.RegistrationCreateError;
import anhtht.registration.RegistrationDAO;
import anhtht.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "createNewAccount.jsp";
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
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        String url = ERROR_PAGE;
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            //1. Check all user's contrainsts
            if (username.trim().length() <6 ||
                    username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthError("Username is required input from 6 to 20 characters");
            } 
            
            if (password.trim().length() <6 ||
                    password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthError("Password is required input from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match Password");
            }
            if (fullName.trim().length() < 2 ||
                    fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthError("Fullname is required input from 2 to 50 characters");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                //2. Call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(account);
                //3. Process Result
                if (result) {
                    url = LOGIN_PAGE;
                }
            }//end errors is not ocurred
            
            
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL "+ msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExised(username + "is existed !!!");
                request.setAttribute("CREATE_ERRORS", errors);
            }//username is existed
        } catch (NamingException ex){
            log("CreateAccountServlet _ Naming" + ex.getMessage());
        }
        finally {
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
