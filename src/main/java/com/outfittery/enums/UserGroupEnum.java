/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.enums;

/**
 *
 * @author sarkhanrasullu
 */
public enum UserGroupEnum {

    STYLIST(1),
    ADMIN(2),
    CUSTOMER(3),
    DEVELOPER(4);
    
    public int id;

    UserGroupEnum(int id) {
        this.id = id;
    }

}
