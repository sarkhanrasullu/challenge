package com.outfittery.dto;

import com.outfittery.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDto {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean blocked;

    public UserDto() {
    }

    public UserDto(int id) {
        this.id = id;
    }

    public UserDto(User user) {
        if (user == null) {
            return;
        }
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.blocked = user.isBlocked();
    }

    public UserDto(int id, String name, String surname, String username, boolean blocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.blocked = blocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getPassword() {
        return password;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

}
