/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.dto;

import com.outfittery.form.*;
import com.outfittery.entity.Appointment;
import java.util.Date;

/**
 *
 * @author sarkhanrasullu
 */
public class AppointmentDto {

    private int id;
    private UserDto stylistId;
    private UserDto customerId;
    private Date date;

    public AppointmentDto() {

    }

    public AppointmentDto(Appointment appointment) {
        this.stylistId = new UserDto(appointment.getStylistId());
        this.customerId = new UserDto(appointment.getCustomerId());
        this.date = appointment.getAppointmentDt();
    }

    public UserDto getStylistId() {
        return stylistId;
    }

    public void setStylistId(UserDto stylistId) {
        this.stylistId = stylistId;
    }

    public UserDto getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UserDto customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
