/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.controller;

import com.outfittery.dto.AppointmentDto;
import com.outfittery.form.AppointmentForm;
import com.outfittery.dto.ResponseDto;
import com.outfittery.entity.Appointment;
import com.outfittery.entity.User;
import com.outfittery.service.AppointmentServiceInterface;
import com.outfittery.service.UserServiceInterface;
import com.outfittery.util.DTOHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentRestController {

    @Autowired
    AppointmentServiceInterface appointmentService;

    @Autowired
    UserServiceInterface userService;

    @RequestMapping("/appointment/slot")
    public ResponseEntity getAvailableAppointmentSlots(
            @RequestParam("stylistId") Integer stylistId,
            @RequestParam("nextDays") int nextDays) {
        Object res = null;
        if (stylistId != null) {
            User stylist = userService.findUserById(stylistId);
            if (stylist == null) {
                throw new IllegalArgumentException("No such stylist");
            }
            res = appointmentService.getAvailableSlotsByStylistId(stylist, nextDays);
        } else {
            res = appointmentService.getAvailableSlots(nextDays);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instance(res));
    }

    @RequestMapping("/appointment")
    public ResponseEntity getAllAppointment() {
        List<Appointment> list = appointmentService.getAllAppointment();
        List<AppointmentDto> res = DTOHelper.convertAppointmentToDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instance(res));
    }

    @PostMapping("/appointment")
    public ResponseEntity add(@RequestBody AppointmentForm appointmentForm) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDt(appointmentForm.getDate());
        User stylist = userService.findUserById(appointmentForm.getStylistId());
        if (stylist == null) {
            throw new IllegalArgumentException("No such stylist");
        }
        appointment.setStylistId(stylist);

        //here we need to check if logged in user customer then customer id only can be logged in user
        //if logged in user admin or moderator then customer id can be any customer. 
        //Assume moderator wants to make an appointment for somebody with any stylist on phone call
        //OR WE COULD SPLIT THE URLS TO 2 PARTS: CLIENT API AND ADMIN API.
        User customer = userService.findUserById(appointmentForm.getCustomerId());
        if (customer == null) {
            throw new IllegalArgumentException("No such customer");
        }
        appointment.setCustomerId(customer);

        appointmentService.addAppointment(appointment, appointmentForm.getInNextDays());

        AppointmentDto result = new AppointmentDto(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.instanceSuccess(result));
    }

}
