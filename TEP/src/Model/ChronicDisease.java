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
public class ChronicDisease {
    private final String patientSurname;
    private final String patientName;
    private final String diseaseName;

    public ChronicDisease(String patientSurname, String patientName, String diseaseName) {
        this.patientSurname = patientSurname;
        this.patientName = patientName;
        this.diseaseName = diseaseName;
    }

    public String getPatientSurname() {
        return this.patientSurname;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getDiseaseName() {
        return this.diseaseName;
    }

}
