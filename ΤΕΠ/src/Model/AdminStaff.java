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
public class AdminStaff {
    private final String surname;
    private final String name;

    public AdminStaff(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

}
