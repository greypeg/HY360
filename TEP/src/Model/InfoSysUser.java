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
public class InfoSysUser {

    private final int employeeID;
    private final int patientAMKA;
    private final String password;

    public InfoSysUser(int employeeID, int patientAMKA, String password) {
        this.employeeID = employeeID;
        this.patientAMKA = patientAMKA;
        this.password = password;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public int getPatientAMKA() {
        return this.patientAMKA;
    }

    public String getPassword() {
        return this.password;
    }

}
