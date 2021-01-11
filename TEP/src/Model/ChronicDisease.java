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

    private final Patient patient;
    private final Disease disease;

    public ChronicDisease(Patient patient, Disease disease) {
        this.patient = patient;
        this.disease = disease;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Disease getDisease() {
        return this.disease;
    }

}
