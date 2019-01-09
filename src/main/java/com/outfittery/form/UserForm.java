package com.outfittery.form;

import com.outfittery.dto.*;
import com.outfittery.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserForm {

    private String name;
    private String surname;
    private String username;
    private String password;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserForm() {
    }
 

    public UserForm(User user) {
        if (user == null) {
            return;
        }
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
    }

    public UserForm(String name, String surname, String username, boolean blocked) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public User user() {
        User user = new User(null, this.getName(), this.getSurname(), this.getUsername(), this.getPassword(), null);
        return user;
    }

}
