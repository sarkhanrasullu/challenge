/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.entity;

import com.outfittery.util.TimeUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sarkhanrasullu
 */
@Entity
@Table(name = "settings")
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "appointment_slot_count")
    private Integer appointmentSlotCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "appointment_slot_length")
    private String appointmentSlotLength;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "appointment_slot_start_time")
    private String appointmentSlotStartTime;

    public Settings() {
    }

    public Settings(String name) {
        this.name = name;
    }

    public Date getAppointmentSlotLength() {
        return TimeUtil.parse(appointmentSlotLength, "HH:mm:dd");
    }

    public void setAppointmentSlotLength(String appointmentSlotLength) {
        this.appointmentSlotLength = appointmentSlotLength;
    }

    public Integer getAppointmentSlotCount() {
        return appointmentSlotCount;
    }

    public void setAppointmentSlotCount(Integer appointmentSlotCount) {
        this.appointmentSlotCount = appointmentSlotCount;
    }

    public Date getAppointmentSlotStartTime() {
        return TimeUtil.parse(appointmentSlotStartTime, "HH:mm:dd");
    }

    public void setAppointmentSlotStartTime(String appointmentSlotStartTime) {
        this.appointmentSlotStartTime = appointmentSlotStartTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.outfittery.entity.Settings[ name=" + name + " ]";
    }

}
