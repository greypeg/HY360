/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Stefito
 */
public class Visit {

    private final int ID;
    private final int patientAMKA;
    private final String patientSymptoms;
    private final String Day;
    private final String Month;
    private final int Year;
    private final int examinationDoctorID;
    private final String examinationType;
    private final String medicineName;
    private int examinationNurseID;
    private String diagnosedDisease;
    private int reexaminationDoctorID;
    private int hospitalizationID;

    public Visit(int ID, int patientAMKA, String patientSymptoms, String Day, String Month, int Year, int examinationDoctorID, String examinationType, String medicineName, int examinationNurseID, String diagnosedDisease, int reexaminationDoctorID, int hospitalizationID) {
        this.ID = ID;
        this.patientAMKA = patientAMKA;
        this.patientSymptoms = patientSymptoms;
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.examinationDoctorID = examinationDoctorID;
        this.examinationType = examinationType;
        this.medicineName = medicineName;
        this.examinationNurseID = examinationNurseID;
        this.diagnosedDisease = diagnosedDisease;
        this.reexaminationDoctorID = reexaminationDoctorID;
        this.hospitalizationID = hospitalizationID;
    }

    public int getID() {
        return this.ID;
    }

    public int getPatientAMKA() {
        return this.patientAMKA;
    }

    public String getPatientSymptoms() {
        return this.patientSymptoms;
    }

    public String getDay() {
        return this.Day;
    }

    public String getMonth() {
        return this.Month;
    }

    public int getYear() {
        return this.Year;
    }

    public int getExaminationDoctorID() {
        return this.examinationDoctorID;
    }

    public String getExaminationType() {
        return this.examinationType;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public int getExaminationNurseID() {
        return this.examinationNurseID;
    }

    public String getDiagnosedDisease() {
        return this.diagnosedDisease;
    }

    public int getReexaminationDoctorID() {
        return this.reexaminationDoctorID;
    }

    public int getHospitalizationID() {
        return this.hospitalizationID;
    }

    public void setExaminationNurseID(int examinationNurseID) {
        this.examinationNurseID = examinationNurseID;
    }

    public void setDiagnosedDisease(String diagnosedDisease) {
        this.diagnosedDisease = diagnosedDisease;
    }

    public void setReExaminationDoctorID(int reexaminationDoctorID) {
        this.reexaminationDoctorID = reexaminationDoctorID;
    }

    public void setHospitalizationID(int hospitalizationID) {
        this.hospitalizationID = hospitalizationID;
    }

}
