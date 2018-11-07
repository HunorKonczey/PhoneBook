/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hunor
 */
public class Person {
    private final SimpleStringProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    
    public Person(){
    this.firstName=new SimpleStringProperty("");
    this.lastName=new SimpleStringProperty("");
    this.email=new SimpleStringProperty("");
    this.id=new SimpleStringProperty("");
    }
    
    public Person(String lName, String fName, String email){
    this.firstName= new SimpleStringProperty(fName);
    this.lastName = new SimpleStringProperty(lName);
    this.email = new SimpleStringProperty(email);
    this.id=new SimpleStringProperty("");
    }
    
    public Person(Integer id, String lName, String fName, String email){
    this.firstName= new SimpleStringProperty(fName);
    this.lastName = new SimpleStringProperty(lName);
    this.email = new SimpleStringProperty(email);
    this.id=new SimpleStringProperty(String.valueOf(id));
    }

    public String getFirstName() {
        return firstName.get();
    }
    
    public void setFirstName(String fName){
        this.firstName.set(fName);
    }
    
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lName){
        this.lastName.set(lName);
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email){
        this.email.set(email);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id){
        this.id.set(id);
    }
}
