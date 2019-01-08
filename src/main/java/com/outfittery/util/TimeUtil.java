/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author sarkhanrasullu
 */
public class TimeUtil {

    public static Date addDate(Date target, Date add) {
        Calendar calendarTarget = Calendar.getInstance();
        calendarTarget.setTime(target);

        Calendar calendarAdd = Calendar.getInstance();
        calendarAdd.setTime(add);

        calendarTarget.add(Calendar.HOUR, calendarAdd.get(Calendar.HOUR));
        calendarTarget.add(Calendar.MINUTE, calendarAdd.get(Calendar.MINUTE));
        calendarTarget.add(Calendar.SECOND, calendarAdd.get(Calendar.SECOND));

        return calendarTarget.getTime();
    }

    public static boolean isGreater(Date date1, Date date2) {
        return date1.getTime() > date2.getTime();
    }

    public static Date toDayWithoutTime() {
        return dateWithoutTime(new Date());
    }

    public static Date dateWithoutTime(Date date) {
        return DateUtils.truncate(date, java.util.Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        System.out.println(toDayWithoutTime());
    }

    public static Date parse(String stringDate, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(stringDate);
        } catch (ParseException ex) {
            Logger.getLogger(TimeUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
