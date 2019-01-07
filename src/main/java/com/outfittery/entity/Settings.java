/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sarkhanrasullu
 */
@Entity
@Table(name = "settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s")
    , @NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id")
    , @NamedQuery(name = "Settings.findByAppointmentSlotLength", query = "SELECT s FROM Settings s WHERE s.appointmentSlotLength = :appointmentSlotLength")
    , @NamedQuery(name = "Settings.findByAppointmentSlotCount", query = "SELECT s FROM Settings s WHERE s.appointmentSlotCount = :appointmentSlotCount")
    , @NamedQuery(name = "Settings.findByAppointmentSlotStartTime", query = "SELECT s FROM Settings s WHERE s.appointmentSlotStartTime = :appointmentSlotStartTime")
    , @NamedQuery(name = "Settings.findByAppointmentMaxDays", query = "SELECT s FROM Settings s WHERE s.appointmentMaxDays = :appointmentMaxDays")})
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "appointment_slot_length")
    @Temporal(TemporalType.TIME)
    private Date appointmentSlotLength;
    @Column(name = "appointment_slot_count")
    private Integer appointmentSlotCount;
    @Column(name = "appointment_slot_start_time")
    @Temporal(TemporalType.TIME)
    private Date appointmentSlotStartTime;
    @Column(name = "appointment_max_days")
    private Integer appointmentMaxDays;

    public Settings() {
    }

    public Settings(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppointmentSlotLength() {
        return appointmentSlotLength;
    }

    public void setAppointmentSlotLength(Date appointmentSlotLength) {
        this.appointmentSlotLength = appointmentSlotLength;
    }

    public Integer getAppointmentSlotCount() {
        return appointmentSlotCount;
    }

    public void setAppointmentSlotCount(Integer appointmentSlotCount) {
        this.appointmentSlotCount = appointmentSlotCount;
    }

    public Date getAppointmentSlotStartTime() {
        return appointmentSlotStartTime;
    }

    public void setAppointmentSlotStartTime(Date appointmentSlotStartTime) {
        this.appointmentSlotStartTime = appointmentSlotStartTime;
    }

    public Integer getAppointmentMaxDays() {
        return appointmentMaxDays;
    }

    public void setAppointmentMaxDays(Integer appointmentMaxDays) {
        this.appointmentMaxDays = appointmentMaxDays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.outfittery.entity.Settings[ id=" + id + " ]";
    }
    
}
