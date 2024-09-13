/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import database.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerry
 */
public class EditClassOnlyTable {
    public void createClassOnlyTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE ClassOnly "
                + "(typeID INTEGER not NULL AUTO_INCREMENT, "
                + " pid INTEGER not NULL , "
                + "    classFrom VARCHAR(30) not Null,"
                + "    classTo VARCHAR(30) not Null,"               
                + "    FOREIGN KEY ( pid) REFERENCES Project(pid),"
                + " PRIMARY KEY ( typeID))";

        stmt.execute(query);
        query = "INSERT INTO ClassOnly (typeID ,pid,classFrom,classTo) VALUES('1','1','E22-Man-Made', 'E22-Human-made')";
        stmt.execute(query);
        stmt.close();
        
    }   
    public void addClasses(String classFrom, String classTo) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

           String insertQuery = "INSERT INTO ClassOnly (pid , classFrom, classTo) VALUES ("
                + "'" + 1+ "', "
                + "'" + classFrom+ "', "
                + "'" + classTo + "'"
                + ")";
            
            stmt.executeUpdate(insertQuery);
            
            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditClassOnlyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(String classFrom) throws ClassNotFoundException{
        try {
            Connection con = DB_Connection.getConnection();

             String deleteQuery = "DELETE FROM ClassOnly WHERE classFrom = ?";
             PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        
             pstmt.setString(1, classFrom);
             pstmt.executeUpdate();
        
             pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditClassOnlyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
