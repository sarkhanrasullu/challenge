package com.outfittery.service;

import com.outfittery.dto.AvailableSlotsWrapperDTO;
import com.outfittery.entity.Appointment;

public interface AppointmentServiceInterface {

    public AvailableSlotsWrapperDTO getAvailableSlots(int userId, int nextXDays);

    public int addAppointment(Appointment appointment);

}
