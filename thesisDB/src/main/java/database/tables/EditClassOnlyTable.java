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
}
