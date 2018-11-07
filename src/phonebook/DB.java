/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hunor
 */
public class DB {

    final String URL = "jdbc:derby:sampleDB;create=true";
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;

    // kapcsolat létrehozása
    public DB() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("The bridge is OK");
        } catch (SQLException ex) {
            System.out.println("Baj van a kapcsolat letrehozasaval");
            System.out.println("" + ex);
        }
        // teherautó megpakolása
        if (conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Baj van a teherauto megpakolasaval");
                System.out.println("" + ex);
            }
        }
        try {
            //üres-e az adatbázis
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Baj van a teherauto letrehozasaval");
            System.out.println("" + ex);
        }
        //ha ures letrehozunk egy tablat benne 
        try {
            ResultSet rsl = dbmd.getTables(null, "APP", "CONTACTS", null);
            if (!rsl.next()) {
                createStatement.execute("CREATE TABLE contacts (id INT not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), lastname varchar(20), firstname varchar(20),email varchar(30))");
            }
        } catch (SQLException ex) {
            System.out.println("Baj van a tabla letrehozasaval");
            System.out.println("" + ex);
        }
    }

    public ArrayList<Person> getAllUser() {
        String sql = "SELECT * FROM contacts";
        ArrayList<Person> users = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            
            users = new ArrayList<>();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String email = rs.getString("email");
                Person actuser = new Person(id,lastname, firstname, email);
                users.add(actuser);
            }
        } catch (SQLException ex) {
            System.out.println("Baj van a user lekeresevel");
            System.out.println("" + ex);
        }
        return users;
    }

    public void addContacts(Person person) {
        try {
            String sql = "INSERT INTO contacts (lastname,firstname,email) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.execute();
            System.out.println("Sikeres volt a hozzáadás");
        } catch (SQLException ex) {
            System.out.println("Baj van a user hasznalataval");
            System.out.println("" + ex);
        }

    }
    
    public void updateContacts(Person person) {
        try {
            String sql = "UPDATE contacts SET lastname = ?, firstname = ?, email = ? WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, Integer.parseInt(person.getId()));
            preparedStatement.execute();
            System.out.println("Sikeres volt a hozzáadás");
        } catch (SQLException ex) {
            System.out.println("Baj van a user hozzáadásakor");
            System.out.println("" + ex);
        }

    }
    
      public void removeContacts(Person person) {
        try {
            String sql = "DELETE FROM contacts WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);          
            preparedStatement.setInt(1, Integer.parseInt(person.getId()));
            preparedStatement.execute();
            System.out.println("Sikeres volt a törlés");
        } catch (SQLException ex) {
            System.out.println("Baj van a user törlésekor");
            System.out.println("" + ex);
        }

    }

}
