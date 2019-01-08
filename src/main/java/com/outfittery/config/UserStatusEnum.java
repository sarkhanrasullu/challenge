/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.config;

/**
 *
 * @author sarkhanrasullu
 */
public enum UserStatusEnum {

    AVAILABLE(3),
    NOT_AVAILABLE(4),
    BLOCKED(5);

    public int id;

    UserStatusEnum(int id) {
        this.id = id;
    }

}
