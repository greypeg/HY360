/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author stefito
 */
public class Employee {
    private final int ID;
    private final String surname;
    private final String name;

    public Employee(int ID, String surname, String name) {
        this.ID = ID;
        this.surname = surname;
        this.name = name;
    }

    public int getID() {
        return this.ID;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

}
