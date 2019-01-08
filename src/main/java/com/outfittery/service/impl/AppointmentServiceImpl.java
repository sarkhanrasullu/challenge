package com.outfittery.service.impl;

import com.outfittery.dto.AvailableSlotsWrapperDTO;
import com.outfittery.entity.Appointment;
import com.outfittery.entity.Settings;
import com.outfittery.entity.User;
import com.outfittery.repository.AppointmentRepository;
import com.outfittery.repository.SettingsRepository;
import com.outfittery.repository.UserRepository;
import com.outfittery.service.AppointmentServiceInterface;
import com.outfittery.util.TimeUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentServiceInterface {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SettingsRepository settingsRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public AvailableSlotsWrapperDTO getAvailableSlots(int userId, int nextXDays) {
        User user = userRepo.getOne(userId);
        List<Appointment> appointments = user.getAppointmentListAsStylist();

        Settings settings = settingsRepo.findByName("appointment");
        int appointmentSlotCount = settings.getAppointmentSlotCount();
        Date appointmentSlotLength = settings.getAppointmentSlotLength();
        Date appointmentSlotStartTime = settings.getAppointmentSlotStartTime();

        int restAppointmentSlotForToDay = appointmentSlotCount - appointments.size();

        ArrayList<Date> availableAppointmentDates = new ArrayList<>();
        Date beginDateTime = TimeUtil.addDate(TimeUtil.toDayWithoutTime(), appointmentSlotStartTime);
        Date nextDateTime = beginDateTime;//today 09:00

        Date now = new Date();
        for (int j = 0; j < nextXDays; j++) {
            nextDateTime = DateUtils.addDays(beginDateTime, j);
            for (int i = 0; i < restAppointmentSlotForToDay; i++) {
                if (TimeUtil.isGreater(nextDateTime, now)) {
                    availableAppointmentDates.add(nextDateTime);
                }
                nextDateTime = TimeUtil.addDate(nextDateTime, appointmentSlotLength);
            }
            restAppointmentSlotForToDay = appointmentSlotCount;
        }

        List<Date> existingAppointmentDate = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            existingAppointmentDate.add(new Date(a.getAppointmentDt().getTime()));
        }

        availableAppointmentDates.removeAll(existingAppointmentDate);

        AvailableSlotsWrapperDTO result = new AvailableSlotsWrapperDTO(user, availableAppointmentDates);
        return result;
    }

    @Override
    public int addAppointment(Appointment appointment) {
        return appointmentRepo.save(appointment).getId();
    }

}
