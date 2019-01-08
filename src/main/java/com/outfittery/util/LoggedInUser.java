/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.util;

import com.outfittery.entity.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author sarkhanrasullu
 */
public class LoggedInUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoggedInUser(User user, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, enabled, enabled, enabled, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
