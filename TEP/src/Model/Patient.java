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
public class Patient {

    private final String surname;
    private final String name;
    private final int AMKA;
    private final String insurance_agency;
    private final String phone;
    private final String street;
    private final String city;
    private final int number;

    public Patient(String surname, String name, int AMKA, String insurance_agency, String phone, String street,
            String city, int number) {
        this.surname = surname;
        this.name = name;
        this.AMKA = AMKA;
        this.insurance_agency = insurance_agency;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.number = number;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public int getAMKA() {
        return this.AMKA;
    }

    public String getInsurance_agency() {
        return this.insurance_agency;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public int getNumber() {
        return this.number;
    }
}
