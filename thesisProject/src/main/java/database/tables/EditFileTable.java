/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import mainClasses.File;

/**
 *
 * @author gerry
 */
public class EditFileTable {
       private static final Logger LOGGER = Logger.getLogger(EditFileTable.class.getName());

       
    public void createFileTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE File "
                + "(fid INTEGER not NULL AUTO_INCREMENT, "
                + "    pid INTEGER not Null,"
                + "    type INTEGER not Null,"
                + "    contents MEDIUMBLOB not Null,"
                + "    FOREIGN KEY ( pid) REFERENCES Project(pid),"
                + " PRIMARY KEY (fid))";

        stmt.execute(query);
        query = "INSERT INTO File (fid ,pid,type,contents) VALUES('1','1','0','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<x3ml xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
"      source_type=\"xpath\"\n" +
"      version=\"1.0\"\n" +
"      xsi:noNamespaceSchemaLocation=\"https://isl.ics.forth.gr/x3ml/schema/x3ml.xsd\">\n" +
"   <info>\n" +
"      <title>My First New Mapping</title>\n" +
"      <general_description/>\n" +
"      <source>\n" +
"         <source_info>\n" +
"            <source_schema schema_file=\"first.xml\" type=\"xml\" version=\"\">firstInput</source_schema>\n" +
"            <namespaces>\n" +
"               <namespace prefix=\"mine\"\n" +
"                          uri=\"https://users.ics.forth.gr/~Gerasimos/rdf/test/data.xml#\"/>\n" +
"            </namespaces>\n" +
"         </source_info>\n" +
"      </source>\n" +
"      <target>\n" +
"         <target_info>\n" +
"            <target_schema schema_file=\"CIDOC_CRM_v7.1.1.rdfs\" type=\"rdfs\" version=\"\">CIDOC CRM</target_schema>\n" +
"            <namespaces>\n" +
"               <namespace prefix=\"owl\" uri=\"http://www.w3.org/2002/07/owl#\"/>\n" +
"               <namespace prefix=\"rdf\" uri=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\n" +
"               <namespace prefix=\"xsd\" uri=\"http://www.w3.org/2001/XMLSchema#\"/>\n" +
"               <namespace prefix=\"rdfs\" uri=\"http://www.w3.org/2000/01/rdf-schema#\"/>\n" +
"               <namespace prefix=\"crm\" uri=\"http://www.cidoc-crm.org/cidoc-crm/\"/>\n" +
"            </namespaces>\n" +
"         </target_info>\n" +
"      </target>\n" +
"      <mapping_info>\n" +
"         <mapping_created_by_org/>\n" +
"         <mapping_created_by_person/>\n" +
"         <in_collaboration_with/>\n" +
"      </mapping_info>\n" +
"      <example_data_info>\n" +
"         <example_data_from/>\n" +
"         <example_data_contact_person/>\n" +
"         <example_data_source_record/>\n" +
"         <generator_policy_info/>\n" +
"         <example_data_target_record/>\n" +
"         <thesaurus_info/>\n" +
"      </example_data_info>\n" +
"   </info>\n" +
"   <namespaces>\n" +
"      <namespace prefix=\"pref\" uri=\"http://users.ics.forth.gr/~marketak/rdf/\"/>\n" +
"      <namespace prefix=\"viaf\" uri=\"https://viaf.org/viaf/\"/>\n" +
"   </namespaces>\n" +
"   <mappings>\n" +
"      <mapping>\n" +
"         <domain>\n" +
"            <source_node>/root/row</source_node>\n" +
"            <target_node>\n" +
"               <entity>\n" +
"                  <type>crm:E65_Creation</type>\n" +
"                  <instance_generator name=\"LocalTermURI\">\n" +
"                     <arg name=\"hierarchy\" type=\"constant\">creation</arg>\n" +
"                     <arg name=\"term\" type=\"xpath\">id/text()</arg>\n" +
"                  </instance_generator>\n" +
"                  <label_generator name=\"CompositeLabel\">\n" +
"                     <arg name=\"term1\" type=\"constant\">Creation of</arg>\n" +
"                     <arg name=\"term2\" type=\"xpath\">title/text()</arg>\n" +
"                  </label_generator>\n" +
"               </entity>\n" +
"            </target_node>\n" +
"         </domain>\n" +
"         <link>\n" +
"            <path>\n" +
"               <source_relation>\n" +
"                  <relation>title</relation>\n" +
"               </source_relation>\n" +
"               <target_relation>\n" +
"                  <relationship>crm:P94_has_created</relationship>\n" +
"               </target_relation>\n" +
"            </path>\n" +
"            <range>\n" +
"               <source_node>title</source_node>\n" +
"               <target_node>\n" +
"                  <entity>\n" +
"                     <type>crm:E31_Document</type>\n" +
"                     <instance_generator name=\"LocalTermURI\">\n" +
"                        <arg name=\"hierarchy\" type=\"constant\">doc</arg>\n" +
"                        <arg name=\"term\" type=\"xpath\">text()</arg>\n" +
"                     </instance_generator>\n" +
"                     <label_generator name=\"SimpleLabel\">\n" +
"                        <arg name=\"label\" type=\"xpath\">text()</arg>\n" +
"                     </label_generator>\n" +
"                  </entity>\n" +
"               </target_node>\n" +
"            </range>\n" +
"         </link>\n" +
"         <link>\n" +
"            <path>\n" +
"               <source_relation>\n" +
"                  <relation>date</relation>\n" +
"               </source_relation>\n" +
"               <target_relation>\n" +
"                  <relationship>crm:P4_has_time-span</relationship>\n" +
"               </target_relation>\n" +
"            </path>\n" +
"            <range>\n" +
"               <source_node>date</source_node>\n" +
"               <target_node>\n" +
"                  <entity>\n" +
"                     <type>crm:E52_Time-Span</type>\n" +
"                     <instance_generator name=\"LocalTermURI\">\n" +
"                        <arg name=\"hierarchy\" type=\"constant\">date</arg>\n" +
"                        <arg name=\"term\" type=\"xpath\">text()</arg>\n" +
"                     </instance_generator>\n" +
"                     <label_generator name=\"SimpleLabel\">\n" +
"                        <arg name=\"label\" type=\"xpath\">text()</arg>\n" +
"                     </label_generator>\n" +
"                  </entity>\n" +
"               </target_node>\n" +
"            </range>\n" +
"         </link>\n" +
"         <link>\n" +
"            <path>\n" +
"               <source_relation>\n" +
"                  <relation>location</relation>\n" +
"               </source_relation>\n" +
"               <target_relation>\n" +
"                  <relationship>crm:P7_took_place_at</relationship>\n" +
"               </target_relation>\n" +
"            </path>\n" +
"            <range>\n" +
"               <source_node>location</source_node>\n" +
"               <target_node>\n" +
"                  <entity>\n" +
"                     <type>crm:E53_Place</type>\n" +
"                     <instance_generator name=\"LocalTermURI\">\n" +
"                        <arg name=\"hierarchy\" type=\"constant\">location</arg>\n" +
"                        <arg name=\"term\" type=\"xpath\">text()</arg>\n" +
"                     </instance_generator>\n" +
"                     <label_generator name=\"SimpleLabel\">\n" +
"                        <arg name=\"label\" type=\"xpath\">text()</arg>\n" +
"                     </label_generator>\n" +
"                  </entity>\n" +
"               </target_node>\n" +
"            </range>\n" +
"         </link>\n" +
"         <link>\n" +
"            <path>\n" +
"               <source_relation>\n" +
"                  <relation>creator_fname</relation>\n" +
"               </source_relation>\n" +
"               <target_relation>\n" +
"                  <relationship>crm:P14_carried_out_by</relationship>\n" +
"               </target_relation>\n" +
"            </path>\n" +
"            <range>\n" +
"               <source_node>creator_fname</source_node>\n" +
"               <target_node>\n" +
"                  <entity>\n" +
"                     <type>crm:E39_Actor</type>\n" +
"                     <instance_generator name=\"LocalTermURI\">\n" +
"                        <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
"                        <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
"                     </instance_generator>\n" +
"                     <label_generator name=\"CompositeLabel\">\n" +
"                        <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
"                        <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
"                     </label_generator>\n" +
"                  </entity>\n" +
"               </target_node>\n" +
"            </range>\n" +
"         </link>\n" +
"      </mapping>\n" +
"      <mapping>\n" +
"         <domain>\n" +
"            <source_node>/root/row/creator_fname</source_node>\n" +
"            <target_node>\n" +
"               <entity>\n" +
"                  <type>crm:E39_Actor</type>\n" +
"                  <instance_generator name=\"LocalTermURI\">\n" +
"                     <arg name=\"hierarchy\" type=\"constant\">person</arg>\n" +
"                     <arg name=\"term\" type=\"xpath\">../creator_viaf/text()</arg>\n" +
"                  </instance_generator>\n" +
"                  <label_generator name=\"CompositeLabel\">\n" +
"                     <arg name=\"term1\" type=\"xpath\">text()</arg>\n" +
"                     <arg name=\"term2\" type=\"xpath\">../creator_lname/text()</arg>\n" +
"                  </label_generator>\n" +
"               </entity>\n" +
"            </target_node>\n" +
"         </domain>\n" +
"         <link>\n" +
"            <path>\n" +
"               <source_relation>\n" +
"                  <relation>../creator_viaf</relation>\n" +
"               </source_relation>\n" +
"               <target_relation>\n" +
"                  <relationship>crm:P1_is_identified_by</relationship>\n" +
"               </target_relation>\n" +
"            </path>\n" +
"            <range>\n" +
"               <source_node>../creator_viaf</source_node>\n" +
"               <target_node>\n" +
"                  <entity>\n" +
"                     <type>crm:E42_Identifier</type>\n" +
"                     <instance_generator name=\"viafURI\">\n" +
"                        <arg name=\"viaf_id\" type=\"xpath\">text()</arg>\n" +
"                     </instance_generator>\n" +
"                     <label_generator name=\"CompositeLabel\">\n" +
"                        <arg name=\"term1\" type=\"constant\">viaf:</arg>\n" +
"                        <arg name=\"term2\" type=\"xpath\">text()</arg>\n" +
"                     </label_generator>\n" +
"                  </entity>\n" +
"               </target_node>\n" +
"            </range>\n" +
"         </link>\n" +
"      </mapping>\n" +
"   </mappings>\n" +
"</x3ml>')";
        stmt.execute(query);
        query = "INSERT INTO File (fid ,pid,type,contents) VALUES('2','1','1','rdf')";
        stmt.execute(query);
        stmt.close();
    }
public void addNewFile(int type, List<String> contents) throws ClassNotFoundException {
    Connection con = null;
    PreparedStatement pstmt = null;
    try {
        con = DB_Connection.getConnection();

        String concatenatedString = String.join("\n", contents);
        byte[] byteArray = concatenatedString.getBytes(StandardCharsets.UTF_8);
        Blob blob = con.createBlob();
        blob.setBytes(1, byteArray);
        LOGGER.log(Level.INFO, "Blob length: " + byteArray.length);
        String insertQuery = "INSERT INTO File (pid, type, contents) VALUES (?, ?, ?)";
        pstmt = con.prepareStatement(insertQuery);
        pstmt.setInt(1, 1); // Assuming '1' is the 'pid' value
        pstmt.setInt(2, type);
        pstmt.setBlob(3, blob);

        pstmt.executeUpdate();
        LOGGER.log(Level.INFO, "File added successfully!");

    } catch (SQLException ex) {
        Logger.getLogger(EditFileTable.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Close resources
        try {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}
