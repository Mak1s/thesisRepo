/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditClassOnlyTable;
import database.tables.EditClassPropertyTable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerry
 */
public class removePost extends HttpServlet {

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
            out.println("<title>Servlet removePost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet removePost at " + request.getContextPath() + "</h1>");
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
    Logger LOGGER = Logger.getLogger(removePost.class.getName());

    LOGGER.log(Level.INFO, "Starting the doPost method");

    try (BufferedReader br = request.getReader();
         PrintWriter out = response.getWriter()) {

        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        
        while ((line = br.readLine()) != null) {
            jsonBuilder.append(line);
        }
        
        String json = jsonBuilder.toString();
        LOGGER.log(Level.INFO, "Received JSON: " + json);

        json = json.replace("\\", "");
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        Map<String, String> map = new HashMap<>();
        String[] keyValuePairs = json.split(",(?=\")");

        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":", 2);
            if (entry.length == 2) {
                String key = entry[0].trim().replace("\"", "").replace("{", "").replace("}", "");
                String value = entry[1].trim().replace("\"", "").replace("}", "");
                map.put(key, value);
                LOGGER.log(Level.INFO, "Parsed key: " + key + ", value: " + value);
            }
        }

        // Extract values from the map
        String classBefore = map.get("classBefore");
        String classAfter = map.get("classAfter");
        String propertyBefore = map.get("propertyBefore");
        String propertyAfter = map.get("propertyAfter");
        String additionalClass = map.get("additionalClass");

        LOGGER.log(Level.INFO, "classBefore: " + classBefore);
        LOGGER.log(Level.INFO, "classAfter: " + classAfter);
        LOGGER.log(Level.INFO, "propertyBefore: " + propertyBefore);
        LOGGER.log(Level.INFO, "propertyAfter: " + propertyAfter);
        LOGGER.log(Level.INFO, "additionalClass: " + additionalClass);

        if (classBefore != null) {
            if (propertyBefore != null) {
                EditClassPropertyTable classProperty = new EditClassPropertyTable();
                try {
                    LOGGER.log(Level.INFO, "Removing from ClassProperty table");
                    classProperty.removeClassProperty(classBefore);
                } catch (ClassNotFoundException ex) {
                    LOGGER.log(Level.SEVERE, "Class not found", ex);
                }
            } else {
                EditClassOnlyTable classOnly = new EditClassOnlyTable();
                try {
                    LOGGER.log(Level.INFO, "Removing from ClassOnly table");
                    classOnly.remove(classBefore);
                } catch (ClassNotFoundException ex) {
                    LOGGER.log(Level.SEVERE, "Class not found", ex);
                }
            }
        } else if (propertyBefore != null) {
            EditClassPropertyTable propertyOnly = new EditClassPropertyTable();
            try {
                LOGGER.log(Level.INFO, "Removing from PropertyOnly table");
                propertyOnly.removeProperty(propertyBefore);
            } catch (ClassNotFoundException ex) {
                LOGGER.log(Level.SEVERE, "Class not found", ex);
            }
        }

    } catch (IOException e) {
        Logger.getLogger(removePost.class.getName()).log(Level.SEVERE, "IOException occurred", e);
    } catch (Exception e) {
        Logger.getLogger(removePost.class.getName()).log(Level.SEVERE, "An unexpected error occurred", e);
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
