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
public class Hospitalization {

    private final int ID;
    private final int patientAMKA;
    private final String insertDay;
    private final String insertMonth;
    private final int insertYear;
    private final String exitDay;
    private final String exitMonth;
    private final int exitYear;

    public Hospitalization(int ID, int patientAMKA, String insertDay, String insertMonth, int insertYear, String exitDay, String exitMonth, int exitYear) {
        this.ID = ID;
        this.patientAMKA = patientAMKA;
        this.insertDay = insertDay;
        this.insertMonth = insertMonth;
        this.insertYear = insertYear;
        this.exitDay = exitDay;
        this.exitMonth = exitMonth;
        this.exitYear = exitYear;
    }

    public int getID() {
        return this.ID;
    }

    public int getPatientAMKA() {
        return this.patientAMKA;
    }

    public String getInsertDay() {
        return this.insertDay;
    }

    public String getInsertMonth() {
        return this.insertMonth;
    }

    public int getInsertYear() {
        return this.insertYear;
    }

    public String getExitDay() {
        return this.exitDay;
    }

    public String getExitMonth() {
        return this.exitMonth;
    }

    public int getExitYear() {
        return this.exitYear;
    }

}
