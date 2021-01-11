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
public class Doctor extends Employee {

    private final String speciality;

    public Doctor(int ID, String surname, String name, String speciality) {
        super(ID, surname, name);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return this.speciality;
    }

}
