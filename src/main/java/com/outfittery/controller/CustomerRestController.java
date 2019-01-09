package com.outfittery.controller;

import com.outfittery.enums.UserGroupEnum;
import com.outfittery.enums.UserStatusEnum;
import com.outfittery.dto.ResponseDto;
import com.outfittery.dto.UserDto;
import com.outfittery.entity.User;
import com.outfittery.entity.UserGroup;
import com.outfittery.entity.UserStatus;
import com.outfittery.form.UserForm;
import com.outfittery.service.UserServiceInterface;
import com.outfittery.util.DTOHelper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    UserServiceInterface userService;

    @RequestMapping("/customer")
    public ResponseEntity getAll() {
        List<User> users = userService.getAllByGroupId(UserGroupEnum.CUSTOMER.id);
        List<UserDto> result = DTOHelper.convertUserToDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instance(result));
    }

    @PostMapping("/customer")
    public ResponseEntity add(@RequestBody UserForm userForm) {
        User user = userForm.user();
        user.setGroupId(new UserGroup(UserGroupEnum.CUSTOMER.id));
        user.setStatusId(new UserStatus(UserStatusEnum.AVAILABLE.id));
        user.setRegisterDt(new Date());
        userService.add(user);

        UserDto result = new UserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instanceSuccess(result));
    }

}
