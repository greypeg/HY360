/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import Controller.Controller;
import View.login;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefito
 */
public class Run {

    public static void main(String[] args) {
        try {
            Controller cont = new Controller("τεπ");
            new login().setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
