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
public class Doctor {
    private final int ID;
    private final String surname;
    private final String name;
    private final String speciality;

    public Doctor(int ID, String surname, String name, String speciality) {
        this.ID = ID;
        this.surname = surname;
        this.name = name;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return this.speciality;
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
