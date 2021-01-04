/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefito
 */
public class Initializer {

    private Connection CreateDataBaseConnection(String databaseName) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost";
        int port = 3306;
        String username = "root";
        String password = "";
        Connection con;

        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        return con;
    }

    private void CreateDataBaseTables(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String[] createTables;
        createTables = new String[12];

        createTables[0] = "CREATE TABLE IF NOT EXISTS Ασθενείς ("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "ΑΜΚΑ INT, "
                + "Ασφαλιστικός_Φορέας VARCHAR(30), "
                + "Τηλέφωνο INT, "
                + "Οδός VARCHAR(15), "
                + "Πόλη VARCHAR(10), "
                + "Αριθμός INT, "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[1] = "CREATE TABLE IF NOT EXISTS Γιατροί("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "Ειδικότητα VARCHAR(10), "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[2] = "CREATE TABLE IF NOT EXISTS Νοσηλευτές("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[3] = "CREATE TABLE IF NOT EXISTS Διοικητικό_Προσωπικό("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[4] = "CREATE TABLE IF NOT EXISTS Χρήστες_Πληροφοριακού_Συστήματος("
                + "Username VARCHAR(30), "
                + "Password VARCHAR(40), "
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "CONSTRAINT Login PRIMARY KEY(Username, Password)"
                + ");";

        createTables[5] = "CREATE TABLE IF NOT EXISTS Εφημερίες("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "Τύπος VARCHAR(10), "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[6] = "CREATE TABLE IF NOT EXISTS Ασθένειες("
                + "Όνομα VARCHAR(15) PRIMARY KEY"
                + ");";

        createTables[7] = "CREATE TABLE IF NOT EXISTS Χρόνια_Νοσίματα("
                + "Επώνυμο_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθένειας VARCHAR(15)"
                + ");";

        createTables[8] = "CREATE TABLE IF NOT EXISTS Φάρμακα("
                + "Όνομα VARCHAR(15) PRIMARY KEY, "
                + "Τύπος VARCHAR(10), "
                + "Περιεκτικότητα_Σε_Δραστική_Ουσία INT, "
                + "Όνομα_Ασθένειας VARCHAR(15)"
                + ");";

        createTables[9] = "CREATE TABLE IF NOT EXISTS Εξετάσεις("
                + "Τύπος VARCHAR(15) PRIMARY KEY"
                + ");";

        createTables[10] = "CREATE TABLE IF NOT EXISTS Νοσηλείες("
                + "ID INT PRIMARY KEY, "
                + "Επώνυμο_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθενούς VARCHAR(15), "
                + "Ημέρα_Εισιτηρίου VARCHAR(10), "
                + "Μήνας_Εισιτηρίου VARCHAR(10), "
                + "Έτος_Εισιτηρίου INT, "
                + "Ημέρα_Εξιτηρίου VARCHAR(10), "
                + "Μήνας_Εξιτηρίου VARCHAR(10), "
                + "Έτος_Εξιτηρίου INT"
                + ");";

        createTables[11] = "CREATE TABLE IF NOT EXISTS Επισκέψεις("
                + "ID INT PRIMARY KEY, "
                + "Επώνυμο_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθενούς VARCHAR(15), "
                + "Ημέρα VARCHAR(10), "
                + "Μήνας VARCHAR(10), "
                + "Έτος INT, "
                + "Τύπος_Εξέτασης VARCHAR(15), "
                + "Όνομα_Φαρμάκου VARCHAR(15), "
                + "ID_Νοσηλείας INT"
                + ");";

        for (int i = 0; i < 12; i++) {
            stmt.executeUpdate(createTables[i]);
        }
    }

    private void FillDummies() {

    }

    public Initializer(String databaseName) {
        try {
            Connection con = CreateDataBaseConnection(databaseName);
            CreateDataBaseTables(con);
        } catch (Exception e) {
            if (e instanceof ClassNotFoundException) {
                Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, e);
            } else if (e instanceof SQLException) {
                Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
