package servlets;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadServlet1")
@MultipartConfig
public class uploadServlet1 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadServlet1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadServlet1 at " + request.getContextPath() + "</h1>");
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
        Part filePart = request.getPart("fileUpload2");
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
        String fileContent = String.join("\n", fileContentLines);
        
        // Return JSON response        
        try (PrintWriter out = response.getWriter()) {
            out.write("File uploaded successfully, fileName: " + fileName + "\n Contents : "+fileContent);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet handling file upload";
    }
}
