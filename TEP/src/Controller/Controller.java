/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Generator;
import Model.Initializer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Stefito
 */
public class Controller {

    private final Connection con;
    private final Generator generator;

    private int[] getVigilIDs() throws SQLException {
        int[] IDs = new int[5];

        Statement stmt = this.con.createStatement();

        String query = "SELECT ID_Υπαλλήλου FROM Εφημερίες;";
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;
        while (rs.next()) {
            IDs[i++] = rs.getInt("ID_Υπαλλήλου");
        }

        return IDs;
    }

    private void updateVigils(int[] IDs) throws SQLException {
        Statement st = this.con.createStatement();
        st.executeUpdate("TRUNCATE TABLE Εφημερίες;");

        String sql = "INSERT INTO Εφημερίες VALUES(?, ?);";
        PreparedStatement stmt = con.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            stmt.setInt(1, IDs[i]);
            stmt.setString(2, Character.toString(this.generator.getDummyVigils()[i].getType()));

            stmt.executeUpdate();
        }
    }

    private Connection CreateDataBaseConnection(String databaseName) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost";
        int port = 3306;
        String username = "root";
        String password = "";
        Connection connection;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);

        return connection;
    }

    public Controller(String DBName) throws ClassNotFoundException, SQLException {
        this.con = CreateDataBaseConnection(DBName);
        this.generator = new Generator();

        Initializer in = new Initializer(con, generator);
    }

    public void setVigils() throws SQLException {
        int[] IDs = getVigilIDs();
        int[] newIDs = new int[5];
        int i;

        for (i = 0; i < 2; i++) {
            newIDs[i] = (((IDs[i] + 2) % 10) % 5) + 5;
        }

        for (; i < 4; i++) {
            newIDs[i] = (((IDs[i] + 2) % 10) % 5) + 10;
        }

        newIDs[4] = (((IDs[4] + 2) % 10) % 5) + 15;

        updateVigils(newIDs);

    }
}
