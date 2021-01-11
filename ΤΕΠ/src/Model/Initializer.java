/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefito
 */
public final class Initializer {

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
                + "Τηλέφωνο VARCHAR(15), "
                + "Οδός VARCHAR(15), "
                + "Πόλη VARCHAR(10), "
                + "Αριθμός INT, "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[1] = "CREATE TABLE IF NOT EXISTS Γιατροί("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "Ειδικότητα VARCHAR(20), "
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
                + "Τύπος CHAR(1), "
                + "CONSTRAINT Ονοματεπώνυμο PRIMARY KEY(Επώνυμο, Όνομα)"
                + ");";

        createTables[6] = "CREATE TABLE IF NOT EXISTS Ασθένειες("
                + "Όνομα VARCHAR(15) PRIMARY KEY"
                + ");";

        createTables[7] = "CREATE TABLE IF NOT EXISTS Χρόνια_Νοσήματα("
                + "Επώνυμο_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθενούς VARCHAR(15), "
                + "Όνομα_Ασθένειας VARCHAR(15)"
                + ");";

        createTables[8] = "CREATE TABLE IF NOT EXISTS Φάρμακα("
                + "Όνομα VARCHAR(30) PRIMARY KEY, "
                + "Τύπος VARCHAR(30), "
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
                + "Όνομα_Φαρμάκου VARCHAR(30), "
                + "ID_Νοσηλείας INT"
                + ");";

        for (int i = 0; i < 12; i++) {
            stmt.executeUpdate(createTables[i]);
        }
    }

    private void FillDummies(Connection con) throws SQLException {
        Generator generator = new Generator();

        try {
            FillPatientDummies(con, generator);
            FillDoctorDummies(con, generator);
            FillNurseDummies(con, generator);
            FillAdminDummies(con, generator);
            FillDiseaseDummies(con, generator);
            FillMedicineDummies(con, generator);
            FillInfoSysUserDummies(con, generator);
            FillVigilDummies(con, generator);
            FillChronicDiseasesDummies(con, generator);
            FillExaminationDummies(con, generator);
            FillHospitalizationDummies(con, generator);
            FillVisitDummies(con, generator);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                throw (e);
            } else {
                e.printStackTrace();
            }
        }
    }

    private void FillPatientDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Ασθενείς;");

        String sql = "INSERT INTO Ασθενείς VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyPatients()[i].getSurname());
            stmt.setString(2, generator.getDummyPatients()[i].getName());
            stmt.setInt(3, generator.getDummyPatients()[i].getAMKA());
            stmt.setString(4, generator.getDummyPatients()[i].getInsurance_agency());
            stmt.setString(5, generator.getDummyPatients()[i].getPhone());
            stmt.setString(6, generator.getDummyPatients()[i].getStreet());
            stmt.setString(7, generator.getDummyPatients()[i].getCity());
            stmt.setInt(8, generator.getDummyPatients()[i].getNumber());

            stmt.executeUpdate();
        }
    }

    private void FillDoctorDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Γιατροί;");

        String sql = "INSERT INTO Γιατροί VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyDoctors()[i].getSurname());
            stmt.setString(2, generator.getDummyDoctors()[i].getName());
            stmt.setString(3, generator.getDummyDoctors()[i].getSpeciality());

            stmt.executeUpdate();
        }
    }

    private void FillNurseDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Νοσηλευτές;");

        String sql = "INSERT INTO Νοσηλευτές VALUES(?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyNurses()[i].getSurname());
            stmt.setString(2, generator.getDummyNurses()[i].getName());

            stmt.executeUpdate();
        }
    }

    private void FillAdminDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Διοικητικό_Προσωπικό;");

        String sql = "INSERT INTO Διοικητικό_Προσωπικό VALUES(?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyAdmins()[i].getSurname());
            stmt.setString(2, generator.getDummyAdmins()[i].getName());

            stmt.executeUpdate();
        }
    }

    private void FillDiseaseDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Ασθένειες;");

        String sql = "INSERT INTO Ασθένειες VALUES(?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyDiseases()[i].getName());

            stmt.executeUpdate();
        }
    }

    private void FillMedicineDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Φάρμακα;");

        String sql = "INSERT INTO Φάρμακα VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyMedicines()[i].getName());
            stmt.setString(2, generator.getDummyMedicines()[i].getType());
            stmt.setInt(3, generator.getDummyMedicines()[i].getActive_substance_content());
            stmt.setString(4, generator.getDummyMedicines()[i].getIndicatedDisease());

            stmt.executeUpdate();
        }
    }

    private void FillInfoSysUserDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Χρήστες_Πληροφοριακού_Συστήματος;");

        String sql = "INSERT INTO Χρήστες_Πληροφοριακού_Συστήματος VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyInfoSysUsers()[i].getUsername());
            stmt.setString(2, generator.getDummyInfoSysUsers()[i].getPassword());
            stmt.setString(3, generator.getDummyInfoSysUsers()[i].getSurname());
            stmt.setString(4, generator.getDummyInfoSysUsers()[i].getName());

            stmt.executeUpdate();
        }
    }

    private void FillVigilDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Εφημερίες;");

        String sql = "INSERT INTO Εφημερίες VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyVigils()[i].getSurname());
            stmt.setString(2, generator.getDummyVigils()[i].getName());
            stmt.setString(3, Character.toString(generator.getDummyVigils()[i].getType()));

            stmt.executeUpdate();
        }
    }

    private void FillChronicDiseasesDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Χρόνια_Νοσήματα;");

        String sql = "INSERT INTO Χρόνια_Νοσήματα VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyChronicDiseases()[i].getPatientSurname());
            stmt.setString(2, generator.getDummyChronicDiseases()[i].getPatientName());
            stmt.setString(3, generator.getDummyChronicDiseases()[i].getDiseaseName());

            stmt.executeUpdate();
        }
    }

    private void FillExaminationDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Εξετάσεις;");

        String sql = "INSERT INTO Εξετάσεις VALUES(?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setString(1, generator.getDummyExaminations()[i].getType());

            stmt.executeUpdate();
        }
    }

    private void FillHospitalizationDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Νοσηλείες;");

        String sql = "INSERT INTO Νοσηλείες VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyHospitalizations()[i].getID());
            stmt.setString(2, generator.getDummyHospitalizations()[i].getPatientSurname());
            stmt.setString(3, generator.getDummyHospitalizations()[i].getPatientName());
            stmt.setString(4, generator.getDummyHospitalizations()[i].getInsertDay());
            stmt.setString(5, generator.getDummyHospitalizations()[i].getInsertMonth());
            stmt.setInt(6, generator.getDummyHospitalizations()[i].getInsertYear());
            stmt.setString(7, generator.getDummyHospitalizations()[i].getExitDay());
            stmt.setString(8, generator.getDummyHospitalizations()[i].getExitMonth());
            stmt.setInt(9, generator.getDummyHospitalizations()[i].getExitYear());

            stmt.executeUpdate();
        }
    }

    private void FillVisitDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Επισκέψεις;");

        String sql = "INSERT INTO Επισκέψεις VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyVisits()[i].getID());
            stmt.setString(2, generator.getDummyVisits()[i].getPatientSurname());
            stmt.setString(3, generator.getDummyVisits()[i].getPatientName());
            stmt.setString(4, generator.getDummyVisits()[i].getDay());
            stmt.setString(5, generator.getDummyVisits()[i].getMonth());
            stmt.setInt(6, generator.getDummyVisits()[i].getYear());
            stmt.setString(7, generator.getDummyVisits()[i].getExaminationType());
            stmt.setString(8, generator.getDummyVisits()[i].getMedicineName());
            stmt.setInt(9, generator.getDummyVisits()[i].getHospitalizationID());

            stmt.executeUpdate();
        }

    }

    public Initializer(String databaseName) {
        try {
            Connection con = CreateDataBaseConnection(databaseName);
            CreateDataBaseTables(con);
            FillDummies(con);
        } catch (Exception e) {
            if (e instanceof ClassNotFoundException) {
                Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, e);
            } else if (e instanceof SQLException) {
                Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
