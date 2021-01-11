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
    private final Patient patient;
    private final String patientSymptoms;
    private final String Day;
    private final String Month;
    private final int Year;
    private final Doctor examinationDoctor;
    private final Examination examination;
    private final Medicine medicine;
    private final Nurse examinationNurse;
    private final Disease diagnosedDisease;
    private final Doctor reexaminationDoctor;
    private final Hospitalization hospitalization;

    public Visit(int ID, Patient patient, String patientSymptoms, String Day, String Month, int Year, Doctor examinationDoctor, Examination examination, Medicine medicine, Nurse examinationNurse, Disease diagnosedDisease, Doctor reexaminationDoctor, Hospitalization hospitalization) {
        this.ID = ID;
        this.patient = patient;
        this.patientSymptoms = patientSymptoms;
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.examinationDoctor = examinationDoctor;
        this.examination = examination;
        this.medicine = medicine;
        this.examinationNurse = examinationNurse;
        this.diagnosedDisease = diagnosedDisease;
        this.reexaminationDoctor = reexaminationDoctor;
        this.hospitalization = hospitalization;
    }

    public int getID() {
        return this.ID;
    }

    public Patient getPatient() {
        return this.patient;
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

    public Doctor getExaminationDoctor() {
        return this.examinationDoctor;
    }

    public Examination getExamination() {
        return this.examination;
    }

    public Medicine getMedicine() {
        return this.medicine;
    }

    public Nurse getExaminationNurse() {
        return this.examinationNurse;
    }

    public Disease getDiagnosedDisease() {
        return this.diagnosedDisease;
    }

    public Doctor getReexaminationDoctor() {
        return this.reexaminationDoctor;
    }

    public Hospitalization getHospitalization() {
        return this.hospitalization;
    }

}
