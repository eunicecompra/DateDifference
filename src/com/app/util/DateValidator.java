package com.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class DateValidator {



    public DateValidatorMessagesEnum validateInput(String dates) {
        DateValidatorMessagesEnum msg = null;

        if ( dates == null || dates.trim().length() == 0 ) {
            msg = DateValidatorMessagesEnum.INVALID_ARGUMENTS;
        } else {
            Pattern patternRequiredFormat =
                    Pattern.compile("\\d\\d\\s\\d\\d\\s\\d\\d\\d\\d\\,\\s\\d\\d\\s\\d\\d\\s\\d\\d\\d\\d");
            if ( !patternRequiredFormat.matcher(dates).matches() ) {
                msg = DateValidatorMessagesEnum.INVALID_ARGUMENTS;
            } else {
                Pattern patternComma = Pattern.compile(",");
                String[] datesArray = patternComma.split(dates);
                msg = validateDateFormat(datesArray[0]);
                if ( msg == null ) {
                    msg = validateDateFormat(datesArray[1].trim());
                }
            }

        }

        return msg;
    }

    private DateValidatorMessagesEnum validateDateFormat(String date) {
        DateValidatorMessagesEnum msg = null;

        Pattern patternSpace = Pattern.compile(" ");
        String[] dateArray = patternSpace.split(date);
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        boolean leapYear = year % 4 == 0;

        if ( year == 0 || year < DateConstants.START_YEAR || year > DateConstants.END_YEAR ) {
            msg = DateValidatorMessagesEnum.INVALID_YEAR;
        } else if ( month == 0 || month > DateConstants.MONTHS_DAY_ARRAY.length ) { //Month can't be more than 12!
            msg = DateValidatorMessagesEnum.INVALID_DATE_FORMAT;
        } else if ( day == 0 || day > DateConstants.MONTHS_DAY_ARRAY[month-1]
                || ( month == 2 && !leapYear && day > 29 )
                ) {
            msg = DateValidatorMessagesEnum.INVALID_DATE_FORMAT;
        }

        return msg;

    }

}
