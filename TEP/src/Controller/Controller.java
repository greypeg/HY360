/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Generator;
import Model.Initializer;
import Model.Visit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Stefito
 */
public class Controller {

    private final Connection con;
    private final Generator generator;

    private Visit visit;

    private int[] getVigilIDs() throws SQLException {
        int[] IDs = new int[5];

        Statement stmt = this.con.createStatement();

        String query = "SELECT ID_Υπαλλήλου FROM Εφημερίες;";
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;
        while (rs.next()) {
            IDs[i++] = rs.getInt("ID_Υπαλλήλου");
        }

        return IDs;
    }

    private void updateVigils(int[] IDs) throws SQLException {
        Statement st = this.con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Εφημερίες;");

        String sql = "INSERT INTO Εφημερίες VALUES(?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, IDs[i]);
            stmt.setString(2, Character.toString(this.generator.getDummyVigils()[i].getType()));

            stmt.executeUpdate();
        }
    }

    private int getLastVisitID() throws SQLException {
        int lastVisitID = -1;
        Statement stmt = this.con.createStatement();

        String query = "SELECT ID FROM Επισκέψεις ORDER BY ID DESC LIMIT 1;";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            lastVisitID = rs.getInt("ID");
        }

        return lastVisitID;
    }

    private void createExaminationFromDoctor() throws SQLException {
        String sql = "INSERT INTO Επισκέψεις(ID, AMKA_Ασθενούς, Συμπτώματα_Ασθενούς, Ημέρα, Μήνας, Έτος, ID_Γιατρού_Εξέτασης,"
                + "Τύπος_Εξέτασης, Όνομα_Φαρμάκου) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = this.con.prepareStatement(sql);

        stmt.setInt(1, this.visit.getID());
        stmt.setInt(2, this.visit.getPatientAMKA());
        stmt.setString(3, this.visit.getPatientSymptoms());
        stmt.setString(4, this.visit.getDay());
        stmt.setString(5, this.visit.getMonth());
        stmt.setInt(6, this.visit.getYear());
        stmt.setInt(7, this.visit.getExaminationDoctorID());
        stmt.setString(8, this.visit.getExaminationType());
        stmt.setString(9, this.visit.getMedicineName());

        stmt.executeUpdate();
    }

    private void createExaminationFromNurse() throws SQLException {
        String sql = "UPDATE Επισκέψεις SET ID_Νοσηλευτή_Εξέτασης = ?, Όνομα_Ασθένειας = ?  WHERE ID = ?";

        PreparedStatement stmt = this.con.prepareStatement(sql);

        stmt.setInt(1, this.visit.getExaminationNurseID());
        stmt.setString(2, this.visit.getDiagnosedDisease());
        stmt.setInt(3, this.visit.getID());

        stmt.executeUpdate();
    }

    private void createReExaminationFromDoctor() throws SQLException {
        String sql = "UPDATE Επισκέψεις SET ID_Γιατρού_Επανεξέτασης = ?, ID_Νοσηλείας = ?  WHERE ID = ?";

        PreparedStatement stmt = this.con.prepareStatement(sql);

        stmt.setInt(1, this.visit.getReexaminationDoctorID());
        stmt.setInt(2, this.visit.getHospitalizationID());
        stmt.setInt(3, this.visit.getID());

        stmt.executeUpdate();
    }

    private Connection CreateDataBaseConnection(String databaseName) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost";
        int port = 3306;
        String username = "root";
        String password = "";
        Connection connection;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        return connection;
    }

    public Controller(String DBName) throws ClassNotFoundException, SQLException {
        this.con = CreateDataBaseConnection(DBName);
        this.generator = new Generator();

        Initializer in = new Initializer(con, generator);
    }

    public void setVigils() throws SQLException {
        int[] IDs = getVigilIDs();
        int[] newIDs = new int[5];
        int i;

        for (i = 0; i < 2; i++) {
            newIDs[i] = (((IDs[i] + 2) % 10) % 5) + 5;
        }

        for (; i < 4; i++) {
            newIDs[i] = (((IDs[i] + 2) % 10) % 5) + 10;
        }

        newIDs[4] = (((IDs[4] + 2) % 10) % 5) + 15;

        updateVigils(newIDs);
    }

    public void takeExaminationFromDoctor(int AMKA, String symptoms) throws SQLException {
        int lastVisitID = getLastVisitID();
        int visitID = lastVisitID + 1;

        int rnd = new Random().nextInt(5);
        int examinationDoctorID = this.generator.getDummyDoctors()[rnd].getID();

        rnd = new Random().nextInt(5);
        String examinationType = this.generator.getDummyExaminations()[rnd].getType();

        rnd = new Random().nextInt(5);
        String medicineName = this.generator.getDummyMedicines()[rnd].getName();

        LocalDateTime now = LocalDateTime.now();
        /* DO in View */
        this.visit = new Visit(visitID, AMKA, symptoms, Integer.toString(now.getDayOfMonth()),
                Integer.toString(now.getMonthValue()), now.getYear(), examinationDoctorID, examinationType,
                medicineName, -1, "-1", -1, -1);

        createExaminationFromDoctor();
    }

    public void takeExaminationFromNurse(int AMKA) throws SQLException {
        int rnd = new Random().nextInt(5);
        int examinationNurseID = this.generator.getDummyNurses()[rnd].getID();

        rnd = new Random().nextInt(5);
        String diagnosedDisease = this.generator.getDummyDiseases()[rnd].getName();

        this.visit.setExaminationNurseID(examinationNurseID);
        this.visit.setDiagnosedDisease(diagnosedDisease);

        createExaminationFromNurse();
    }

    public void takeReExaminationFromDoctor(int AMKA) throws SQLException {
        int rnd = new Random().nextInt(5);
        int reexaminationDoctorID = this.generator.getDummyDoctors()[rnd].getID();

        rnd = new Random().nextInt(5);
        int hospitalizationID = this.generator.getDummyHospitalizations()[rnd].getID();

        this.visit.setReExaminationDoctorID(reexaminationDoctorID);
        this.visit.setHospitalizationID(hospitalizationID);

        createReExaminationFromDoctor();
    }
}
