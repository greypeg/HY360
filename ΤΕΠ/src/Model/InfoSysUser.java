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
    private final String surname;
    private final String name;

    public InfoSysUser(String username, String password, String surname, String name) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

}
