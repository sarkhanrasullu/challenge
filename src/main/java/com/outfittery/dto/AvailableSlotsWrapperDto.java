package com.outfittery.dto;

import com.outfittery.dto.UserDto;
import com.outfittery.entity.User;
import com.outfittery.dto.UserDto;
import java.util.Date;
import java.util.List;

public class AvailableSlotsWrapperDto {

    private UserDto stylist;
    private List<Date> availableDates;

    public AvailableSlotsWrapperDto() {
    }

    public AvailableSlotsWrapperDto(User u, List<Date> availableDates) {
        this.stylist = new UserDto(u);
        this.availableDates = availableDates;
    }

    public List<Date> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<Date> availableDates) {
        this.availableDates = availableDates;
    }

    public UserDto getStylist() {
        return stylist;
    }

    public void setStylist(UserDto stylist) {
        this.stylist = stylist;
    }

}
