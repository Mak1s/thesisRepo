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
public class EditProjectTable {
    public void createProjectTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE Project "
                + "(pid INTEGER not NULL AUTO_INCREMENT, "
                + "    userID INTEGER not Null,"
                + "    name VARCHAR(30) not Null,"
                + "    date VARCHAR(30) not Null,"               
                + "    FOREIGN KEY ( userID) REFERENCES User(userID),"
                + " PRIMARY KEY ( pid))";

        stmt.execute(query);
        query = "INSERT INTO Project (pid ,userID,name,date) VALUES('1','1','testproject', '19/06/2024')";
        stmt.execute(query);
        stmt.close();
        
    }
}
