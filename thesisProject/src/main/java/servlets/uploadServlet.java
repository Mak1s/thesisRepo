package servlets;

import com.google.gson.Gson;
import database.tables.EditFileTable;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadServlet")
@MultipartConfig
public class uploadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
       
        // Handle the file upload
        Part filePart = request.getPart("fileUpload0");
        String fileName = filePart.getSubmittedFileName();
       
        EditFileTable editFile=new EditFileTable();
        

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
         
         String specificString = "<type>"; 
         String specificString1="<relationship>";
         String namespace="<namespace prefix=";

        List<String> filteredLines = new ArrayList<>();
        List<String> allNameSpaces = new ArrayList<>();
        for (String line : fileContentLines) {
            if (line.contains(specificString)) {
                filteredLines.add(line);
            }else if(line.contains(namespace)){
                allNameSpaces.add(line);
            }
        }
        
        List<String> filteredLines1 = new ArrayList<>();
        for (String line : fileContentLines) {
            if (line.contains(specificString1)) {
                filteredLines1.add(line);
            }
        }

        
        String filteredContent = String.join("\n", filteredLines);
        String filteredContent1=String.join("\n", filteredLines1);
        try (PrintWriter out = response.getWriter()) {
       
            editFile.addNewFile(0,fileContentLines);
            out.write(String.join("", fileContentLines)+filteredContent+filteredContent1+allNameSpaces);
            Logger.getLogger(uploadServlet.class.getName()).log(Level.INFO,"namespace"+allNameSpaces);
            response.setStatus(HttpServletResponse.SC_OK);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(uploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet handling file upload";
    }
}
