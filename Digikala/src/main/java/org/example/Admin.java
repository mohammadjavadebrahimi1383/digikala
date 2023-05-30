package org.example;

import java.io.Serializable;

public class Admin implements Serializable {
    private String username, password, email;

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean adminPassCheck(String passInput){
        if (passInput.equals(password)) return true;
        return false;
    }
    public String getEmail() {
        return email;
    }

}
