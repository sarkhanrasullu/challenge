/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.util;

import com.outfittery.dto.UserDto;
import com.outfittery.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public class UserHelper {

    public static List<UserDto> convertToDto(List<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            result.add(new UserDto(u));
        }
        return result;
    }
}
