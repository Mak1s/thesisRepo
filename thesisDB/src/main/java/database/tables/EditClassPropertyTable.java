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
public class EditClassPropertyTable {
    public void createClassPropertyTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE ClassProperty "
                + "(typeID INTEGER not NULL AUTO_INCREMENT, "
                + " pid INTEGER not NULL, "
                + "    classFrom VARCHAR(255) not Null,"
                + "    classTo VARCHAR(255) not Null,"               
                + "    propertyFrom VARCHAR(255) not Null,"
                + "    propertyTo VARCHAR(255) not Null,"  
                + "    additionalClass VARCHAR(30) not Null,"
                + "    URI BLOB not Null,"  
                + "    FOREIGN KEY ( pid) REFERENCES Project(pid),"
                + " PRIMARY KEY ( typeID))";

        stmt.execute(query);
        query = "INSERT INTO ClassProperty (typeID ,pid,classFrom,classTo,propertyFrom,propertyTo,additionalClass,URI) VALUES('1','1','E22-Man-Made', 'E22-Human-made','P1_is_id','P1_is_recognized','E55_Type','instance_generator name=\"URIwithType\">\n" +
"                              <arg name=\"type\" type=\"constant\">type</arg>\n" +
"                              <arg name=\"id\" type=\"xpath\">name()</arg>\n" +
"                           </instance_generator>\n" +
"                           <label_generator name=\"Literal\">\n" +
"                              <arg name=\"text\" type=\"xpath\">name()</arg>\n" +
"                           </label_generator>')";
       stmt.execute(query);
        stmt.close();
        
    }
}
