/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.dto;

import java.util.Date;

/**
 *
 * @author sarkhanrasullu
 */
public class AppointmentDto {

    private int id;
    private int stylistId;
    private int customerId;
    private Date date;

    public AppointmentDto() {

    }

    public AppointmentDto(int stylistId, int customerId, Date date) {
        this.stylistId = stylistId;
        this.customerId = customerId;
        this.date = date;
    }

    public int getStylistId() {
        return stylistId;
    }

    public void setStylistId(int stylistId) {
        this.stylistId = stylistId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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
