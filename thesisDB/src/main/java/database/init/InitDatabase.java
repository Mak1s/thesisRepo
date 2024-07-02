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
/*import database.tables.EditContractorAdmTable;
import database.tables.EditContractorInstructorTable;
import database.tables.EditPermanentAdmTable;
import database.tables.EditPermanentInstructorTable;
import database.tables.EditFamilyRelationshipTable;
import database.tables.EditFamilyRelationshipTable2;
import database.tables.EditFamilyRelationshipTable3;
import database.tables.EditFamilyRelationshipTable4;
import database.tables.EditKidsAgesTable;
import database.tables.EditPaymentTable;
import database.tables.EditPaymentTable2;
import database.tables.EditPaymentTable3;
import database.tables.EditPaymentTable4;
import database.tables.EditWageTable;
import database.tables.EditWageTable2;
import database.tables.EditWageTable3;
import database.tables.EditWageTable4;
*/

/*
 *
 * @author micha
 */
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

      /*
        EditContractorAdmTable ecat = new EditContractorAdmTable();
        ecat.createContractorAdmTable();

        EditContractorInstructorTable ecit = new EditContractorInstructorTable();
        ecit.createContractorInstructorTable();

        EditPermanentAdmTable epat = new EditPermanentAdmTable();
        epat.createPermanentAdmTable();

        EditPermanentInstructorTable epit = new EditPermanentInstructorTable();
        epit.createPermanentInstructorTable();
        

        EditKidsAgesTable ekat = new EditKidsAgesTable();
        ekat.createKidsAgesTable();

        EditFamilyRelationshipTable efrt = new EditFamilyRelationshipTable();
        efrt.createFamilyRelationshipTable();

        EditFamilyRelationshipTable2 efrt2 = new EditFamilyRelationshipTable2();
        efrt2.createFamilyRelationshipTable();

        EditFamilyRelationshipTable3 efrt3 = new EditFamilyRelationshipTable3();
        efrt3.createFamilyRelationshipTable();

        EditFamilyRelationshipTable4 efrt4 = new EditFamilyRelationshipTable4();
        efrt4.createFamilyRelationshipTable();

        EditWageTable ewt = new EditWageTable();
        ewt.createWageTable();
        EditWageTable2 ewt2 = new EditWageTable2();
        ewt2.createWageTable2();
        EditWageTable3 ewt3 = new EditWageTable3();
        ewt3.createWageTable3();
        EditWageTable4 ewt4 = new EditWageTable4();
        ewt4.createWageTable4();

        EditPaymentTable ept = new EditPaymentTable();
        ept.createPaymentTable();

        EditPaymentTable2 ept2 = new EditPaymentTable2();
        ept2.createPaymentTable2();
        EditPaymentTable3 ept3 = new EditPaymentTable3();
        ept3.createPaymentTable3();
        EditPaymentTable4 ept4 = new EditPaymentTable4();
        ept4.createPaymentTable4();
*/




    }

}
