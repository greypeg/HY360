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
    private final Employee employee;
    private final Patient patient;

    public InfoSysUser(String username, String password, Employee employee, Patient patient) {
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.patient = patient;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Patient getPatient() {
        return this.patient;
    }

}
