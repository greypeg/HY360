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
    private final String patientSurname;
    private final String patientName;
    private final String Day;
    private final String Month;
    private final int Year;
    private final String examinationType;
    private final String medicineName;
    private final int HospitalizationID;

    public Visit(int ID, String patientSurname, String patientName, String Day, String Month, int Year, String examinationType, String medicineName, int HospitalizationID) {
        this.ID = ID;
        this.patientSurname = patientSurname;
        this.patientName = patientName;
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.examinationType = examinationType;
        this.medicineName = medicineName;
        this.HospitalizationID = HospitalizationID;
    }

    public int getID() {
        return this.ID;
    }

    public String getPatientSurname() {
        return this.patientSurname;
    }

    public String getPatientName() {
        return this.patientName;
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

    public String getExaminationType() {
        return this.examinationType;
    }

    public String getMedicineName() {
        return this.medicineName;
    }

    public int getHospitalizationID() {
        return this.HospitalizationID;
    }

}
