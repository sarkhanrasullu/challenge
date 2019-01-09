package com.outfittery.test.unit;

import com.outfittery.entity.Appointment;
import com.outfittery.entity.Settings;
import com.outfittery.entity.User;
import com.outfittery.entity.UserGroup;
import com.outfittery.entity.UserStatus;
import com.outfittery.enums.UserStatusEnum;
import com.outfittery.repository.AppointmentRepository;
import com.outfittery.repository.SettingsRepository;
import com.outfittery.repository.UserRepository;
import com.outfittery.service.impl.AppointmentServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by jt on 6/17/17.
 */
public class AppointmentServiceImplTest {

    AppointmentServiceImpl appointmentService;

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SettingsRepository settingsRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        appointmentService = new AppointmentServiceImpl(
                appointmentRepository,
                userRepository,
                settingsRepository);
    }

    @Test
    public void getAllAppointment() throws Exception {

        Appointment dummyAppointment = new Appointment();
        dummyAppointment.setAppointmentDt(new Date());
        dummyAppointment.setCustomerId(new User(1));
        dummyAppointment.setStylistId(new User(1));

        List<Appointment> dummyList = new ArrayList();
        dummyList.add(dummyAppointment);

        when(appointmentRepository.findAll()).thenReturn(dummyList);

        List<Appointment> result = appointmentService.getAllAppointment();
        assertEquals(result, dummyList);
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void getAvailableSlotsByStylistId() throws Exception {
        User dummyUser = dummyUser();

        Settings dummySettings = new Settings("appointment");
        dummySettings.setAppointmentSlotCount(16);
        dummySettings.setAppointmentSlotLength("00:30:00");
        dummySettings.setAppointmentSlotStartTime("09:00:00");
        when(settingsRepository.findByName(anyString())).thenReturn(dummySettings);

        appointmentService.getAvailableSlotsByStylistId(dummyUser, 1);
        verify(settingsRepository, times(1)).findByName(anyString());
    }

    @Test
    public void getAvailableSlots() throws Exception {
        User dummyUser = dummyUser();
        when(userRepository.getOne(anyInt())).thenReturn(dummyUser);

        List<User> dummyUserList = new ArrayList<>();
        dummyUserList.add(dummyUser);
        when(userRepository.findByGroupId(any(UserGroup.class))).thenReturn(dummyUserList);

        Settings dummySettings = new Settings("appointment");
        dummySettings.setAppointmentSlotCount(16);
        dummySettings.setAppointmentSlotLength("00:30:00");
        dummySettings.setAppointmentSlotStartTime("09:00:00");
        when(settingsRepository.findByName(anyString())).thenReturn(dummySettings);

        appointmentService.getAvailableSlots(1);

        verify(userRepository, times(1)).findByGroupId(any(UserGroup.class));
        verify(settingsRepository, times(1)).findByName(anyString());
    }
    
    private User dummyUser() {
        List<Appointment> dummyList = new ArrayList();
        User dummyUser = new User(1, "Sarkhan", "Rasullu", "srasullu", "111111", new Date());
        dummyUser.setStatusId(new UserStatus(UserStatusEnum.AVAILABLE.id));
        dummyUser.setAppointmentListAsStylist(dummyList);
        return dummyUser;
    }
}
