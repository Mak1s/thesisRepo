/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;


import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
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
    
    public void addUserFromJSON(String json) throws ClassNotFoundException, SQLException{
        User user=jsonToUser(json);
        addNewUser(user);
    }
    
    public User jsonToUser(String json){
         Gson gson = new Gson();

        User user = gson.fromJson(json, User.class);
        return user;
    }
    
    public String UserToJSON(User user){
         Gson gson = new Gson();

        String json = gson.toJson(user, User.class);
        return json;
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

            String insertQuery = "INSERT INTO "
                    + " User (FName,LName,username,password)"
                    + " VALUES ("
                    + "'" + user.getFName() + "',"
                    + "'" + user.getLName() + "',"
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getPassword() + "',"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The user was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditUserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
