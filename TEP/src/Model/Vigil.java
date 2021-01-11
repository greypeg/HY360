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
public class Vigil {

    private final Employee employee;
    private final char type;

    public Vigil(Employee employee, char type) {
        this.employee = employee;
        this.type = type;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public char getType() {
        return this.type;
    }


}
