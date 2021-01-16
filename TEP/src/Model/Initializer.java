/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Stefito
 */
public final class Initializer {

    private void CreateDataBaseTables(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        String[] createTables;
        createTables = new String[12];

        createTables[0] = "CREATE TABLE IF NOT EXISTS Ασθενείς ("
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "ΑΜΚΑ INT PRIMARY KEY, "
                + "Ασφαλιστικός_Φορέας VARCHAR(30), "
                + "Τηλέφωνο VARCHAR(15), "
                + "Οδός VARCHAR(15), "
                + "Πόλη VARCHAR(10), "
                + "Αριθμός INT"
                + ");";

        createTables[1] = "CREATE TABLE IF NOT EXISTS Γιατροί("
                + "ID INT PRIMARY KEY, "
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15), "
                + "Ειδικότητα VARCHAR(20)"
                + ");";

        createTables[2] = "CREATE TABLE IF NOT EXISTS Νοσηλευτές("
                + "ID INT PRIMARY KEY, "
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15)"
                + ");";

        createTables[3] = "CREATE TABLE IF NOT EXISTS Διοικητικό_Προσωπικό("
                + "ID INT PRIMARY KEY, "
                + "Επώνυμο VARCHAR(15), "
                + "Όνομα VARCHAR(15)"
                + ");";

        createTables[4] = "CREATE TABLE IF NOT EXISTS Χρήστες_Πληροφοριακού_Συστήματος("
                + "ID_Υπαλλήλου INT, "
                + "AMKA_Ασθενούς INT, "
                + "password VARCHAR(30)"
                + ");";

        createTables[5] = "CREATE TABLE IF NOT EXISTS Εφημερίες("
                + "ID INT, "
                + "ID_Υπαλλήλου INT, "
                + "Τύπος CHAR(1)"
                + ");";

        createTables[6] = "CREATE TABLE IF NOT EXISTS Ασθένειες("
                + "Όνομα VARCHAR(15) PRIMARY KEY"
                + ");";

        createTables[7] = "CREATE TABLE IF NOT EXISTS Χρόνια_Νοσήματα("
                + "AMKA_Ασθενούς INT, "
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
                + "AMKA_Ασθενούς INT, "
                + "Ημέρα_Εισιτηρίου INT, "
                + "Μήνας_Εισιτηρίου INT, "
                + "Έτος_Εισιτηρίου INT, "
                + "Ημέρα_Εξιτηρίου INT, "
                + "Μήνας_Εξιτηρίου INT, "
                + "Έτος_Εξιτηρίου INT"
                + ");";

        createTables[11] = "CREATE TABLE IF NOT EXISTS Επισκέψεις("
                + "ID INT PRIMARY KEY, "
                + "AMKA_Ασθενούς INT, "
                + "Συμπτώματα_Ασθενούς VARCHAR(60), "
                + "Ημέρα INT, "
                + "Μήνας INT, "
                + "Έτος INT, "
                + "ID_Εφημερίας INT, "
                + "ID_Γιατρού_Εξέτασης INT, "
                + "Τύπος_Εξέτασης VARCHAR(15), "
                + "Όνομα_Φαρμάκου VARCHAR(30), "
                + "ID_Νοσηλευτή_Εξέτασης INT, "
                + "Όνομα_Ασθένειας VARCHAR(15), "
                + "ID_Γιατρού_Επανεξέτασης INT, "
                + "ID_Νοσηλείας INT"
                + ");";

        for (int i = 0; i < 12; i++) {
            stmt.executeUpdate(createTables[i]);
        }
    }

    private void FillDummies(Connection con, Generator generator) throws SQLException {
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

        String sql = "INSERT INTO Γιατροί VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyDoctors()[i].getID());
            stmt.setString(2, generator.getDummyDoctors()[i].getSurname());
            stmt.setString(3, generator.getDummyDoctors()[i].getName());
            stmt.setString(4, generator.getDummyDoctors()[i].getSpeciality());

            stmt.executeUpdate();
        }
    }

    private void FillNurseDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Νοσηλευτές;");

        String sql = "INSERT INTO Νοσηλευτές VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyNurses()[i].getID());
            stmt.setString(2, generator.getDummyNurses()[i].getSurname());
            stmt.setString(3, generator.getDummyNurses()[i].getName());

            stmt.executeUpdate();
        }
    }

    private void FillAdminDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Διοικητικό_Προσωπικό;");

        String sql = "INSERT INTO Διοικητικό_Προσωπικό VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyAdmins()[i].getID());
            stmt.setString(2, generator.getDummyAdmins()[i].getSurname());
            stmt.setString(3, generator.getDummyAdmins()[i].getName());

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

        String sql = "INSERT INTO Χρήστες_Πληροφοριακού_Συστήματος VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 20; i++) {
            stmt.setInt(1, generator.getDummyInfoSysUsers()[i].getEmployeeID());
            stmt.setInt(2, generator.getDummyInfoSysUsers()[i].getPatientAMKA());
            stmt.setString(3, generator.getDummyInfoSysUsers()[i].getPassword());

            stmt.executeUpdate();
        }
    }

    private void FillVigilDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Εφημερίες;");

        String sql = "INSERT INTO Εφημερίες VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 10; i++) {
            stmt.setInt(1, generator.getDummyVigils()[i].getID());
            stmt.setInt(2, generator.getDummyVigils()[i].getEmployeeID());
            stmt.setString(3, Character.toString(generator.getDummyVigils()[i].getType()));

            stmt.executeUpdate();
        }
    }

    private void FillChronicDiseasesDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Χρόνια_Νοσήματα;");

        String sql = "INSERT INTO Χρόνια_Νοσήματα VALUES(?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 6; i++) {
            stmt.setInt(1, generator.getDummyChronicDiseases()[i].getPatientAMKA());
            stmt.setString(2, generator.getDummyChronicDiseases()[i].getDiseaseNAme());

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

        String sql = "INSERT INTO Νοσηλείες VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyHospitalizations()[i].getID());
            stmt.setInt(2, generator.getDummyHospitalizations()[i].getPatientAMKA());
            stmt.setInt(3, generator.getDummyHospitalizations()[i].getInsertDay());
            stmt.setInt(4, generator.getDummyHospitalizations()[i].getInsertMonth());
            stmt.setInt(5, generator.getDummyHospitalizations()[i].getInsertYear());
            stmt.setInt(6, generator.getDummyHospitalizations()[i].getExitDay());
            stmt.setInt(7, generator.getDummyHospitalizations()[i].getExitMonth());
            stmt.setInt(8, generator.getDummyHospitalizations()[i].getExitYear());

            stmt.executeUpdate();
        }
    }

    private void FillVisitDummies(Connection con, Generator generator) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Επισκέψεις;");

        String sql = "INSERT INTO Επισκέψεις VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, generator.getDummyVisits()[i].getID());
            stmt.setInt(2, generator.getDummyVisits()[i].getPatientAMKA());
            stmt.setString(3, generator.getDummyVisits()[i].getPatientSymptoms());
            stmt.setInt(4, generator.getDummyVisits()[i].getDay());
            stmt.setInt(5, generator.getDummyVisits()[i].getMonth());
            stmt.setInt(6, generator.getDummyVisits()[i].getYear());
            stmt.setInt(7, generator.getDummyVisits()[i].getVigilID());
            stmt.setInt(8, generator.getDummyVisits()[i].getExaminationDoctorID());
            stmt.setString(9, generator.getDummyVisits()[i].getExaminationType());
            stmt.setString(10, generator.getDummyVisits()[i].getMedicineName());
            stmt.setInt(11, generator.getDummyVisits()[i].getExaminationNurseID());
            stmt.setString(12, generator.getDummyVisits()[i].getDiagnosedDisease());
            stmt.setInt(13, generator.getDummyVisits()[i].getReexaminationDoctorID());
            stmt.setInt(14, generator.getDummyVisits()[i].getHospitalizationID());

            stmt.executeUpdate();
        }

    }

    public Initializer(Connection con, Generator generator) throws SQLException {
        CreateDataBaseTables(con);
        FillDummies(con, generator);
    }
}
