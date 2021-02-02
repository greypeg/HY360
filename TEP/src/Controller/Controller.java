/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ChronicDisease;
import Model.Generator;
import Model.Initializer;
import Model.Patient;
import Model.Vigil;
import Model.Visit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Stefito
 */
public class Controller {

    private final Connection con;
    private final Generator generator;

    private Visit visit;
    private Vigil[] vigils;

    private int examinationDoctorID;

    private int getVigilID() throws SQLException {
        int vigilID = -1;

        Statement stmt = this.con.createStatement();
        String query = "SELECT MAX(ID) FROM Εφημερίες;";

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            vigilID = rs.getInt(1);
        }

        return vigilID;
    }

    private int[] getVigilIEmployeesIDs(int vigilID) throws SQLException {
        int[] IDs = new int[5];

        String query = "SELECT ID_Υπαλλήλου FROM Εφημερίες WHERE ID = (SELECT MAX(ID) FROM Εφημερίες);";
        PreparedStatement stmt = this.con.prepareStatement(query);

        ResultSet rs = stmt.executeQuery(query);

        int i = 0;
        while (rs.next()) {
            IDs[i++] = rs.getInt("ID_Υπαλλήλου");
        }

        return IDs;
    }

    private void updateVigils() throws SQLException {
        String sql = "INSERT INTO Εφημερίες VALUES(?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, this.vigils[i].getID());
            stmt.setInt(2, this.vigils[i].getEmployeeID());
            stmt.setString(3, Character.toString(this.vigils[i].getType()));

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

    public Connection getCon() {
        return this.con;
    }

    private int getLastHospitalizationID() throws SQLException {
        int lastHospitalization = -1;

        Statement stmt = this.con.createStatement();

        String query = "SELECT ID FROM Νοσηλείες ORDER BY ID DESC LIMIT 1;";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            lastHospitalization = rs.getInt("ID");
        }

        return lastHospitalization;
    }

    private void hospitalizePatient(int hospitalizationID, int AMKA) throws SQLException {
        String sql = "INSERT INTO Νοσηλείες VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1, hospitalizationID);
        stmt.setInt(2, AMKA);
        stmt.setInt(3, generator.getNow().getDayOfMonth());
        stmt.setInt(4, generator.getNow().getMonthValue());
        stmt.setInt(5, generator.getNow().getYear());
        stmt.setInt(6, generator.getNow().getDayOfMonth());
        stmt.setInt(7, generator.getNow().getMonthValue());
        stmt.setInt(8, generator.getNow().getYear() + 1);

        stmt.executeUpdate();
    }

    private void createExaminationFromDoctor() throws SQLException {
        String sql = "INSERT INTO Επισκέψεις(ID, AMKA_Ασθενούς, Συμπτώματα_Ασθενούς, Ημέρα, Μήνας, Έτος, ID_Εφημερίας,"
                + " ID_Γιατρού_Εξέτασης, Τύπος_Εξέτασης, Όνομα_Φαρμάκου) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement stmt = this.con.prepareStatement(sql);

        stmt.setInt(1, this.visit.getID());
        stmt.setInt(2, this.visit.getPatientAMKA());
        stmt.setString(3, this.visit.getPatientSymptoms());
        stmt.setInt(4, this.visit.getDay());
        stmt.setInt(5, this.visit.getMonth());
        stmt.setInt(6, this.visit.getYear());
        stmt.setInt(7, this.visit.getVigilID());
        stmt.setInt(8, this.visit.getExaminationDoctorID());
        stmt.setString(9, this.visit.getExaminationType());
        stmt.setString(10, this.visit.getMedicineName());

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

    private void initializeVigils() {
        this.vigils = new Vigil[5];

        for (int i = 0; i < 5; i++) {
            this.vigils[i] = this.generator.getDummyVigils()[i + 5];
        }
    }

    public Controller(String DBName) throws ClassNotFoundException, SQLException {
        this.con = CreateDataBaseConnection(DBName);
        this.generator = new Generator();

        Initializer in = new Initializer(con, generator);
        initializeVigils();
    }

    public void setVigils() throws SQLException {
        int vigilID = getVigilID();
        int[] EmployeesIDs = getVigilIEmployeesIDs(vigilID);

        int[] newIDs = new int[5];
        int newVigilID = vigilID + 1;

        int i;
        for (i = 0; i < 2; i++) {
            newIDs[i] = (((EmployeesIDs[i] + 2) % 10) % 5) + 5;
        }

        for (; i < 4; i++) {
            newIDs[i] = (((EmployeesIDs[i] + 2) % 10) % 5) + 10;
        }

        newIDs[4] = (((EmployeesIDs[4] + 2) % 10) % 5) + 15;

        for (int j = 0; j < 5; j++) {
            this.vigils[j] = new Vigil(newVigilID, newIDs[j], this.generator.getDummyVigils()[j].getType());
        }

        updateVigils();
    }

    public void takeExaminationFromDoctor(int AMKA, String symptoms) throws SQLException {
        int lastVisitID = getLastVisitID();
        int visitID = lastVisitID + 1;

        int rnd = new Random().nextInt(2);
        this.examinationDoctorID = this.vigils[rnd].getEmployeeID();

        rnd = new Random().nextInt(5);
        String examinationType = this.generator.getDummyExaminations()[rnd].getType();

        rnd = new Random().nextInt(5);
        String medicineName = this.generator.getDummyMedicines()[rnd].getName();

        /* DO in View */
        this.visit = new Visit(visitID, AMKA, symptoms, this.generator.getNow().getDayOfMonth(),
                this.generator.getNow().getMonthValue(), this.generator.getNow().getYear(), this.vigils[0].getID(),
                this.examinationDoctorID, examinationType, medicineName, -1, "-1", -1, -1);

        createExaminationFromDoctor();
    }

    public void takeExaminationFromNurse(int AMKA) throws SQLException {
        int rnd = new Random().nextInt(2);
        int examinationNurseID = this.vigils[rnd + 2].getEmployeeID();

        rnd = new Random().nextInt(5);
        String diagnosedDisease = this.generator.getDummyDiseases()[rnd].getName();

        this.visit.setExaminationNurseID(examinationNurseID);
        this.visit.setDiagnosedDisease(diagnosedDisease);

        createExaminationFromNurse();
    }

    public void takeReExaminationFromDoctor(int AMKA) throws SQLException {
        int rnd = new Random().nextInt(2);
        int hospitalizationID;
        int newHospitalizationID = -1;
        /* 50 - 50 */
        if (rnd == 1) {
            hospitalizationID = getLastHospitalizationID();
            newHospitalizationID = hospitalizationID + 1;
            hospitalizePatient(newHospitalizationID, AMKA);
        }

        this.visit.setReExaminationDoctorID(this.examinationDoctorID);
        this.visit.setHospitalizationID(newHospitalizationID);

        createReExaminationFromDoctor();
    }

    public void executeSQL(String sql) throws SQLException {
        Statement st = this.con.createStatement();
        st.executeUpdate(sql);
    }

    public String getVisitsReport() throws SQLException {
        String visitsString = "";
        ArrayList<Visit> visits;
        visits = new ArrayList<>();

        Statement stmt = this.con.createStatement();
        String query = "SELECT * FROM Επισκέψεις WHERE ID_Εφημερίας = (SELECT MAX(ID) FROM Εφημερίες);";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Visit vi = new Visit(rs.getInt("ID"), rs.getInt("AMKA_Ασθενούς"), rs.getString("Συμπτώματα_Ασθενούς"),
                    rs.getInt("Ημέρα"), rs.getInt("Μήνας"), rs.getInt("Έτος"), rs.getInt("ID_Εφημερίας"),
                    rs.getInt("ID_Γιατρού_Εξέτασης"), rs.getString("Τύπος_Εξέτασης"), rs.getString("Όνομα_Φαρμάκου"),
                    rs.getInt("ID_Νοσηλευτή_Εξέτασης"), rs.getString("Όνομα_Ασθένειας"), rs.getInt("ID_Γιατρού_Επανεξέτασης"),
                    rs.getInt("ID_Νοσηλείας"));

            visits.add(vi);
        }

        for (Visit obj : visits) {
            visitsString += "\n" + obj.getID() + " " + obj.getPatientAMKA() + " "
                    + obj.getPatientSymptoms() + " " + obj.getDay() + " " + obj.getMonth() + " "
                    + obj.getYear() + " " + obj.getVigilID() + " " + obj.getExaminationDoctorID()
                    + " " + obj.getExaminationType() + " " + obj.getMedicineName() + " "
                    + obj.getExaminationNurseID() + " " + obj.getDiagnosedDisease() + " "
                    + obj.getReexaminationDoctorID() + " " + obj.getHospitalizationID() + "\n";
        }

        return visitsString;
    }

    public String getStatsPerMonthReport() throws SQLException {
        String statsString = "\nStats per month/vigil:\n";
        ArrayList<int[]> stats = new ArrayList<>();

        Statement stmt = this.con.createStatement();
        String query = "SELECT COUNT(DISTINCT ID), COUNT(DISTINCT Όνομα_Ασθένειας), COUNT(DISTINCT Τύπος_Εξέτασης),"
                + " COUNT(DISTINCT Όνομα_Φαρμάκου) FROM επισκέψεις WHERE Μήνας = (SELECT MAX(Μήνας) FROM Επισκέψεις)"
                + " GROUP BY ID_Εφημερίας";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int[] statsPerVigil = new int[]{0, 0, 0, 0};

            statsPerVigil[0] = rs.getInt(1);
            statsPerVigil[1] = rs.getInt(2);
            statsPerVigil[2] = rs.getInt(3);
            statsPerVigil[3] = rs.getInt(4);

            stats.add(statsPerVigil);
        }
        int i = 1;
        for (int[] obj : stats) {
            statsString += "  Vigil No" + i + "\n    Number of incidents: " + obj[0] + "\n    Number of diagnosed diseasess: " + obj[1]
                    + "\n    Number of examinations: " + obj[2] + "\n    Number of prescribed medicines: " + obj[3] + "\n\n";
            i++;
        }

        return statsString;
    }

    public String getCOVID_19Report() throws SQLException {
        String COVIDReportString = "";
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<ChronicDisease> cdiseases = new ArrayList<>();

        Statement stmt = this.con.createStatement();
        String query = "SELECT * FROM ασθενείς WHERE ασθενείς.ΑΜΚΑ ="
                + " (SELECT επισκέψεις.AMKA_Ασθενούς FROM επισκέψεις WHERE επισκέψεις.Όνομα_Ασθένειας = 'COVID')";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Patient patient = new Patient(rs.getString("Επώνυμο"), rs.getString("Όνομα"), rs.getInt("ΑΜΚΑ"),
                    rs.getString("Ασφαλιστικός_Φορέας"), rs.getString("Τηλέφωνο"), rs.getString("Οδός"), rs.getString("Πόλη"),
                    rs.getInt("Αριθμός"));

            patients.add(patient);
        }

        query = "SELECT * FROM χρόνια_νοσήματα WHERE χρόνια_νοσήματα.AMKA_Ασθενούς ="
                + " (SELECT επισκέψεις.AMKA_Ασθενούς FROM επισκέψεις WHERE επισκέψεις.Όνομα_Ασθένειας = 'COVID')";

        rs = stmt.executeQuery(query);

        while (rs.next()) {
            ChronicDisease cdisease = new ChronicDisease(rs.getInt("AMKA_Ασθενούς"), rs.getString("Όνομα_Ασθένειας"));

            cdiseases.add(cdisease);
        }
        int i = 1;
        for (Patient patient : patients) {
            COVIDReportString += "\nCOVID_19 case No " + i + "\n" + "  Surname: " + patient.getSurname() + "  Name: "
                    + patient.getName() + " AMKA: " + patient.getAMKA() + " Insurance Agency: " + patient.getInsurance_agency()
                    + " Phone: " + patient.getPhone() + " Street: " + patient.getStreet() + " City: " + patient.getCity()
                    + " Number: " + patient.getNumber() + "\n  Chronic Diseases: ";

            for (ChronicDisease cdisease : cdiseases) {
                if (cdisease.getPatientAMKA() == patient.getAMKA()) {
                    COVIDReportString += cdisease.getDiseaseNAme() + " ";
                }
            }
        }

        return COVIDReportString;
    }

    public String getStaffStats() throws SQLException {
        String staffStatString = "";

        Statement stmt = this.con.createStatement();
        String query = "SELECT d.ID, count(v.ID_Υπαλλήλου) FROM Γιατροί d LEFT OUTER JOIN Εφημερίες"
                + " v ON d.ID = v.ID_Υπαλλήλου GROUP BY ID";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            staffStatString += "Doctor ID: " + rs.getInt(1) + " Number of vigils: " + rs.getInt(2) + "\n";
        }

        query = "SELECT d.ID, count(v.ID_Υπαλλήλου) FROM Νοσηλευτές d LEFT OUTER JOIN Εφημερίες"
                + " v ON d.ID = v.ID_Υπαλλήλου GROUP BY ID";
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            staffStatString += "Nurse ID: " + rs.getInt(1) + " Number of vigils: " + rs.getInt(2) + "\n";
        }

        query = "SELECT d.ID, count(v.ID_Υπαλλήλου) FROM διοικητικό_προσωπικό d LEFT OUTER JOIN"
                + " Εφημερίες v ON d.ID = v.ID_Υπαλλήλου GROUP BY ID";
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            staffStatString += "Admin ID: " + rs.getInt(1) + " Number of vigils: " + rs.getInt(2) + "\n";
        }

        return staffStatString;
    }
}
