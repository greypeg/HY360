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
    private final int insertDay;
    private final int insertMonth;
    private final int insertYear;
    private final int exitDay;
    private final int exitMonth;
    private final int exitYear;

    public Hospitalization(int ID, int patientAMKA, int insertDay, int insertMonth, int insertYear, int exitDay, int exitMonth,
            int exitYear) {
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

    public int getInsertDay() {
        return this.insertDay;
    }

    public int getInsertMonth() {
        return this.insertMonth;
    }

    public int getInsertYear() {
        return this.insertYear;
    }

    public int getExitDay() {
        return this.exitDay;
    }

    public int getExitMonth() {
        return this.exitMonth;
    }

    public int getExitYear() {
        return this.exitYear;
    }

}
