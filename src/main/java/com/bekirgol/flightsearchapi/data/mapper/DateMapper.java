package com.bekirgol.flightsearchapi.data.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateMapper {
    default Date stringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateString, e);
        }
    }


}
