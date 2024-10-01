/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;


import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.User;

/**
 *
 * @author gerry
 */
public class EditUserTable {
    private static final Logger LOGGER = Logger.getLogger(EditUserTable.class.getName());

    public void addUserFromJSON(String json) throws ClassNotFoundException, SQLException{
        User user=jsonToUser(json);
        addNewUser(user);
    }
    
    public User jsonToUser(String json){
         Gson gson = new Gson();

        User user = gson.fromJson(json, User.class);
        LOGGER.log(Level.INFO, "Converted User: " + user);
        return user;
    }
    
    public String UserToJSON(User user){
         Gson gson = new Gson();

        String json = gson.toJson(user, User.class);
        return json;
    }
    public User databaseToUser(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
       
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND password='"+password+"'");
            System.out.println("SELECT * FROM User WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
      return null;
    }
    
    public String databaseUserToJSON(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createUserTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE User "
                + "(userID INTEGER not NULL AUTO_INCREMENT, "
                + "    FName VARCHAR(30) not Null,"
                + "    LName VARCHAR(30) not Null,"
                + "    username VARCHAR(30) not Null,"
                + "    password VARCHAR(30) not Null,"
                + " PRIMARY KEY ( userID))";

        stmt.execute(query);
        query = "INSERT INTO User (userID ,FName,LName,username,password) VALUES('1','Gerasimos','Logiotatopoulos', 'makis2001', '123@abc')";
        stmt.execute(query);
        stmt.close();
        
    }
    public void addNewUser(User user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

           String insertQuery = "INSERT INTO User (FName, LName, username, password) VALUES ("
               + "'" + user.getFName() + "', "
                + "'" + user.getLName() + "', "
                + "'" + user.getUsername() + "', "
                + "'" + user.getPassword() + "'"
                + ")";
            
            stmt.executeUpdate(insertQuery);
            
            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditUserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
