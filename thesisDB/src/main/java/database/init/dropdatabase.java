package database.init;
import database.init.dropdatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static database.DB_Connection.getInitialConnection;

public class dropdatabase {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        dropdatabase drop = new dropdatabase();
        drop.drop();
    }

    public void drop() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP DATABASE thesisDB");
        stmt.close();
        conn.close();
    }
}
