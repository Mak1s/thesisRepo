/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.init;

import database.init.InitDatabase;
import static database.DB_Connection.getInitialConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import database.tables.EditUserTable;
import database.tables.EditProjectTable;
import database.tables.EditFileTable;
import database.tables.EditClassOnlyTable;
import database.tables.EditClassPropertyTable;

public class InitDatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InitDatabase init = new InitDatabase();
        init.initDatabase();
        init.initTables();
    

        // init.deleteRecords();
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE DATABASE thesisDB");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {

      
        EditUserTable user = new EditUserTable();
        user.createUserTable();
        
        EditProjectTable project=new EditProjectTable();
        project.createProjectTable();

        EditFileTable file=new EditFileTable();
        file.createFileTable();
        
        EditClassOnlyTable classonly=new EditClassOnlyTable();
        classonly.createClassOnlyTable();
        
        EditClassPropertyTable classproperty=new EditClassPropertyTable();
        classproperty.createClassPropertyTable();


    }

}
