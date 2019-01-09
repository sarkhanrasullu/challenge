/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.form;

import com.outfittery.entity.Appointment;
import java.util.Date;

/**
 *
 * @author sarkhanrasullu
 */
public class AppointmentForm {

    private int stylistId;
    private int customerId;
    private int inNextDays;
    private Date date;

    public AppointmentForm() {

    }


    public AppointmentForm(int stylistId, int customerId, Date date) {
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

    public int getInNextDays() {
        return inNextDays;
    }

    public void setInNextDays(int inNextDays) {
        this.inNextDays = inNextDays;
    }

}
