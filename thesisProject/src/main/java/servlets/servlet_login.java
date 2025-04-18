/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditUserTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gerry
 */
public class servlet_login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet_login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servlet_login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    request.setCharacterEncoding("UTF-8");  

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Logger.getLogger(servlet_login.class.getName()).log(Level.INFO, "Received username: " + username);
    Logger.getLogger(servlet_login.class.getName()).log(Level.INFO, "Received password: " + password);
    
    HttpSession session = request.getSession(true);
    try (PrintWriter out = response.getWriter()) {
        EditUserTable user_table = new EditUserTable();
        if (user_table.databaseUserToJSON(username, password) == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("Invalid credentials");
        } else {
            session.setAttribute("loggedIn", username);
            response.setStatus(HttpServletResponse.SC_OK);
            out.write("Login successful");
        }
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(servlet_login.class.getName()).log(Level.SEVERE, null, ex);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        
    }
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
