/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;


import database.DB_Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gerry
 */
public class EditUserTable {
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
}
