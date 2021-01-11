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

    private final String username;
    private final String password;
    private final int employeeID;
    private final int patientAMKA;

    public InfoSysUser(String username, String password, int employeeID, int patientAMKA) {
        this.username = username;
        this.password = password;
        this.employeeID = employeeID;
        this.patientAMKA = patientAMKA;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public int getPatientAMKA() {
        return this.patientAMKA;
    }

}
