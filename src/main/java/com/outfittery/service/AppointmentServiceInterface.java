package com.outfittery.service;

import com.outfittery.dto.AvailableSlotsWrapperDto;
import com.outfittery.entity.Appointment;
import com.outfittery.entity.User;
import java.util.List;

public interface AppointmentServiceInterface {

    public AvailableSlotsWrapperDto getAvailableSlotsByStylistId(User stylist, int nextXDays);

    public int addAppointment(Appointment appointment, int nextXDays);

    public List<AvailableSlotsWrapperDto> getAvailableSlots(int nextXDays);

    public List<Appointment> getAllAppointment();
}
