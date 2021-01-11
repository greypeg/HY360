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
    private final String surname;
    private final String name;
    private final char type;

    public Vigil(String surname, String name, char type) {
        this.surname = surname;
        this.name = name;
        this.type = type;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public char getType() {
        return this.type;
    }

}
