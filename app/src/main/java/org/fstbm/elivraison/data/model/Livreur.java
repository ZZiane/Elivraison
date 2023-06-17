package org.fstbm.elivraison.data.model;

import java.io.Serializable;
import java.util.List;

public class Livreur implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String password;
    private List<Command> commands;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Command> getCommands() {
        return commands;
    }
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }




}

