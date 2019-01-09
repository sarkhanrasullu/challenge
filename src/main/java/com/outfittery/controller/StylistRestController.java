/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.controller;

import java.util.List;
import com.outfittery.dto.UserDto;
import com.outfittery.entity.User;
import com.outfittery.form.UserForm;
import com.outfittery.util.DTOHelper;
import com.outfittery.dto.ResponseDto;
import com.outfittery.entity.UserGroup;
import com.outfittery.entity.UserStatus;
import com.outfittery.enums.UserGroupEnum;
import com.outfittery.enums.UserStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.outfittery.service.UserServiceInterface;
import java.util.Date;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StylistRestController {

    @Autowired
    UserServiceInterface userService;

    @RequestMapping("/stylist")
    public ResponseEntity getAll() {
        List<User> users = userService.getAllByGroupId(UserGroupEnum.STYLIST.id);
        List<UserDto> result = DTOHelper.convertUserToDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instance(result));
    }

    @PostMapping("/stylist")
    public ResponseEntity add(@RequestBody UserForm userForm) {
        User user = new User(null, userForm.getName(), userForm.getSurname(), userForm.getUsername(), userForm.getPassword(), null);
        user.setGroupId(new UserGroup(UserGroupEnum.STYLIST.id));
        user.setStatusId(new UserStatus(UserStatusEnum.AVAILABLE.id));
        user.setRegisterDt(new Date());
        userService.add(user);
        UserDto result = new UserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instanceSuccess(result));
    }

}
