/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditFileTable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import mainClasses.JSON_Converter;
import mainClasses.ModelLoader;

/**
 *
 * @author gerry
 */
@WebServlet("/uploadServletRDF")
@MultipartConfig
public class uploadServletRDF extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadServletRDF</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadServletRDF at " + request.getContextPath() + "</h1>");
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
        EditFileTable editFile= new EditFileTable();
        
        PrintWriter out = response.getWriter();
        try {
            
            // Handle the file upload
            Part filePart = request.getPart("fileUpload1");
            String fileName = filePart.getSubmittedFileName();

            // Define the path to save the uploaded file
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // Save the file to the server
            File file = new File(uploadPath + File.separator + fileName);
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
           List<String> fileContentLines = Files.readAllLines(file.toPath());
           
              editFile.addNewFile(1,fileContentLines);
       
            ModelLoader modelLoader = new ModelLoader(file.getAbsolutePath());
            Map<String,String> classesMap=modelLoader.listClassesMap();
            Map<String,String> propertiesMap=modelLoader.listPropertiesMap();
            for(String className : classesMap.keySet()) {
                out.write(className+"\t"+classesMap.get(className)+"\n");
                
            }
            out.write("classes");
            for(String propertyName : propertiesMap.keySet()) {
                out.write(propertyName+"\t"+propertiesMap.get(propertyName)+"\n");
            }
            
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter n = response.getWriter()) {
                n.write("{\"error\":\"" + e.getMessage() + "\"}");
            }
            e.printStackTrace();
       
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
