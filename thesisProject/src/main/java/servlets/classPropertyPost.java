/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.tables.EditClassPropertyTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerry
 */
public class classPropertyPost extends HttpServlet {

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
            out.println("<title>Servlet classPropertyPost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet classPropertyPost at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Logger LOGGER = Logger.getLogger(classOnlyPost.class.getName());
        LOGGER.log(Level.INFO,"eimai servlet ");

        BufferedReader br = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        Blob b;
        
        while ((line = br.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        LOGGER.log(Level.INFO, json);

        json = json.replace("\\", "");  
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1); 
        }

        String[] keyValuePairs = json.split(",(?=\")");  

        Map<String, String> map = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":", 2);
            String key = entry[0].trim().replace("\"", "").replace("{", "").replace("}", "");
            String value = entry[1].trim().replace("\"", "").replace("}", "");  
            
            
            LOGGER.log(Level.INFO, "key: " + key);
            LOGGER.log(Level.INFO, "value: " + value);
            
            map.put(key, value);
        }

        String classBefore = map.get("classBefore");
        String classAfter = map.get("classAfter");
        String propertyBefore = map.get("propertyBefore");
        String propertyAfter = map.get("propertyAfter");
        String additionalClass = map.get("additionalClass");
        
        LOGGER.log(Level.INFO, "be4: " + classBefore);  
        LOGGER.log(Level.INFO, "after: " + classAfter); 
        LOGGER.log(Level.INFO, "be4: " + propertyBefore);  
        LOGGER.log(Level.INFO, "after: " + propertyAfter); 
        LOGGER.log(Level.INFO, "added: " + additionalClass); 
        

        EditClassPropertyTable classProperty=new EditClassPropertyTable();
    
        try {
            classProperty.addClassesProperty(classBefore, classAfter, propertyBefore, propertyAfter,additionalClass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(classPropertyPost.class.getName()).log(Level.SEVERE, null, ex);
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
