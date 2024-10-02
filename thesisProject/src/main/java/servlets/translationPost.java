/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import mainClasses.mainTranslation;
/**
 *
 * @author gerry
 */
public class translationPost extends HttpServlet {

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
            out.println("<title>Servlet translationPost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet translationPost at " + request.getContextPath() + "</h1>");
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

    Logger LOGGER = Logger.getLogger(translationPost.class.getName());
    
    try {
        // Reading the JSON input from the request body
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        String fullContent = stringBuilder.toString();
        LOGGER.log(Level.INFO, "Received content: " + fullContent);

        // Parse the input JSON using Gson
        Gson gson = new Gson();
        DataInput dataInput;
        try {
           
            dataInput = gson.fromJson(fullContent, DataInput.class);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error parsing JSON: ", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Invalid input format\"}");
            return;
        }

        String x3mlContent = dataInput.getX3mlfile();
        String jsonContent = dataInput.getNewBlob();

        LOGGER.log(Level.INFO, "x3mlContent: " + x3mlContent);
        LOGGER.log(Level.INFO, "jsonContent: " + jsonContent);

        // Call your translation logic
        mainTranslation mt = new mainTranslation();
        mt.runTranslationAppFromString(x3mlContent, jsonContent);
        File outputFile = new File("newX3ML_out.x3ml");
        if (!outputFile.exists()) {
            response.getWriter().write("{\"status\":\"error\", \"message\":\"File not found\"}");
            return;
        }

        // Set response to download the file
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + outputFile.getName() + "\"");
        response.setContentLength((int) outputFile.length());

        // Write file content to response
        try (FileInputStream fileIn = new FileInputStream(outputFile);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        }

        // Send success response
        //response.getWriter().write("{\"status\":\"success\"}");
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error during translation process: ", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("{\"status\":\"error\", \"message\":\"Internal server error\"}");
    }
}

// Helper class to represent the expected input structure
public static class DataInput {
    private String x3mlfile;
    private String newBlob;

    public String getX3mlfile() {
        return x3mlfile;
    }

    public void setX3mlfile(String x3mlfile) {
        this.x3mlfile = x3mlfile;
    }

    public String getNewBlob() {
        return newBlob;
    }

    public void setNewBlob(String newBlob) {
        this.newBlob = newBlob;
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
