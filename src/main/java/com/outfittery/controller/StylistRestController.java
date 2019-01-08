/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.controller;

import com.outfittery.config.UserGroupEnum;
import com.outfittery.config.UserStatusEnum;
import com.outfittery.dto.AvailableSlotsWrapperDTO;
import com.outfittery.dto.ResponseDTO;
import com.outfittery.dto.UserDto;
import com.outfittery.entity.User;
import com.outfittery.entity.UserGroup;
import com.outfittery.entity.UserStatus;
import com.outfittery.service.AppointmentServiceInterface;
import com.outfittery.service.UserServiceInterface;
import com.outfittery.util.UserHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StylistRestController {

    @Autowired
    UserServiceInterface userService;

    @RequestMapping("/stylist/all")
    public ResponseEntity getAll() {
        List<User> users = userService.getAllByGroupId(UserGroupEnum.STYLIST.id);
        List<UserDto> result = UserHelper.convertToDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(result));
    }

    @PostMapping("/stylist/add")
    public ResponseEntity add(@RequestBody UserDto userDto) {
        User user = new User(null, userDto.getName(), userDto.getSurname(), userDto.getUsername(), null, null);
        user.setGroupId(new UserGroup(UserGroupEnum.STYLIST.id));
        user.setStatusId(new UserStatus(UserStatusEnum.AVAILABLE.id));
        int id = userService.add(user);
        userDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(userDto));
    }

}
