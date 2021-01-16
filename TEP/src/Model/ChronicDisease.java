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

    private final int patientAMKA;
    private final String diseaseNAme;

    public ChronicDisease(int patientAMKA, String diseaseNAme) {
        this.patientAMKA = patientAMKA;
        this.diseaseNAme = diseaseNAme;
    }

    public int getPatientAMKA() {
        return this.patientAMKA;
    }

    public String getDiseaseNAme() {
        return this.diseaseNAme;
    }


}
