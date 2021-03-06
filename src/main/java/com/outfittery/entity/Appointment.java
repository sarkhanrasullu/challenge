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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sarkhanrasullu
 */
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "appointment_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDt;
    @JoinColumn(name = "stylist_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User stylistId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User customerId;

    public Appointment() {
    }

    public Appointment(Integer id) {
        this.id = id;
    }

    public Appointment(Integer id, Date appointmentDt) {
        this.id = id;
        this.appointmentDt = appointmentDt;
    }

    public Appointment(Date appointmentDt, User stylistId) {
        this.appointmentDt = appointmentDt;
        this.stylistId = stylistId;
    }

    public Appointment(Date appointmentDt, User stylistId, User customerId) {
        this.appointmentDt = appointmentDt;
        this.stylistId = stylistId;
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAppointmentDt() {
        return appointmentDt;
    }

    public void setAppointmentDt(Date appointmentDt) {
        this.appointmentDt = appointmentDt;
    }

    public User getStylistId() {
        return stylistId;
    }

    public void setStylistId(User stylistId) {
        this.stylistId = stylistId;
    }

    public User getCustomerId() {
        return customerId;
    }

    public void setCustomerId(User customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.outfittery.entity.Appointment[ id=" + id + " ]";
    }

}
