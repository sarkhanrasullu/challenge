package com.outfittery.service.impl;

import com.outfittery.enums.UserGroupEnum;
import com.outfittery.dto.AvailableSlotsWrapperDto;
import com.outfittery.entity.Appointment;
import com.outfittery.entity.Settings;
import com.outfittery.entity.User;
import com.outfittery.entity.UserGroup;
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

    public AppointmentServiceImpl() {

    }

    public AppointmentServiceImpl(AppointmentRepository appointmentRepo,
            UserRepository userRepo,
            SettingsRepository settingsRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.settingsRepo = settingsRepo;
    }

    @Override
    public AvailableSlotsWrapperDto getAvailableSlotsByStylistId(User stylist, int nextXDays) {
        List<Appointment> appointments = stylist.getAppointmentListAsStylist();
        Settings settings = settingsRepo.findByName("appointment");
        int appointmentSlotCount = settings.getAppointmentSlotCount();
        Date appointmentSlotLength = settings.getAppointmentSlotLength();
        Date appointmentSlotStartTime = settings.getAppointmentSlotStartTime();
        int existingAppointmenCount = 0;
        if(appointments!=null){
            existingAppointmenCount = appointments.size();
        }
        int restAppointmentSlotForToDay = appointmentSlotCount - existingAppointmenCount;

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
        for (int i = 0; i < existingAppointmenCount; i++) {
            Appointment a = appointments.get(i);
            existingAppointmentDate.add(new Date(a.getAppointmentDt().getTime()));
        }

        availableAppointmentDates.removeAll(existingAppointmentDate);

        AvailableSlotsWrapperDto result = new AvailableSlotsWrapperDto(stylist, availableAppointmentDates);
        return result;
    }

    @Override
    public int addAppointment(Appointment appointment, int nextXDays) {
        //we should create an index to avoid same stylist with same client at the same time
        //stylist_id, appointment_dt UNIQUE <- stylist can't be joined to any customer at the same time

        /*
            customer_id, stylist_id, appointment_dt UNIQUE <- customer can't make an appointment 
            to same stylist at the same time.
            Here customer can make appointment with DIFFERENT stylists at the same time. It depends on the bussiness.
            My opinion is client can make an appointment at the same time with A and B. After that can remove one of them.
            
            Or we can force user can't add another appointment at the same with B and show message.
         */
        AvailableSlotsWrapperDto dto = getAvailableSlotsByStylistId(appointment.getStylistId(), nextXDays);
        List<Date> availableDates = dto.getAvailableDates();
        /*
            checks that this appointment date exists or not. It can be fake time or not available anymore.
            
         */
        if (availableDates.contains(appointment.getAppointmentDt())) {
            return appointmentRepo.save(appointment).getId();
        } else {
            return -1;
        }
    }

    @Override
    public List<AvailableSlotsWrapperDto> getAvailableSlots(int nextXDays) {
        List<User> stylists = userRepo.findByGroupId(new UserGroup(UserGroupEnum.STYLIST.id));
        List<AvailableSlotsWrapperDto> result = new ArrayList<>();
        for (int i = 0; i < stylists.size(); i++) {
            User stylist = stylists.get(i);
            AvailableSlotsWrapperDto availableSlots = getAvailableSlotsByStylistId(stylist, nextXDays);
            result.add(availableSlots);
        }
        return result;
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }

}
