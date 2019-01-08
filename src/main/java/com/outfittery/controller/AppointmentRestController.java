/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.controller;

import com.outfittery.dto.AppointmentDto;
import com.outfittery.dto.AvailableSlotsWrapperDTO;
import com.outfittery.dto.ResponseDTO;
import com.outfittery.entity.Appointment;
import com.outfittery.entity.User;
import com.outfittery.service.AppointmentServiceInterface;
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
    AppointmentServiceInterface service;

    @RequestMapping("/appointment/all")
    public ResponseEntity getAvailableAppointmentSlotsByStylist(
            @RequestParam("stylistId") int stylistId,
            @RequestParam("nextDays") int nextDays) {
        AvailableSlotsWrapperDTO res = service.getAvailableSlots(stylistId, nextDays);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(res));
    }

    @PostMapping("/appointment/add")
    public ResponseEntity add(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDt(appointmentDto.getDate());
        appointment.setStylistId(new User(appointmentDto.getStylistId()));

        //here we need to check if logged in user customer then customer id only can be logged in user
        //if logged in user admin or moderator then customer id can be any customer. 
        //Assume moderator wants to make an appointment for somebody with any stylist on phone call
        //OR WE COULD SPLIT THE URLS TO 2 PARTS: CLIENT API AND ADMIN API.
        appointment.setCustomerId(new User(appointmentDto.getCustomerId()));

        int id = service.addAppointment(appointment);
        appointmentDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(appointmentDto));
    }

}
