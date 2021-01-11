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

    private final int employeeID;
    private final char type;

    public Vigil(int employeeID, char type) {
        this.employeeID = employeeID;
        this.type = type;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public char getType() {
        return this.type;
    }

}
