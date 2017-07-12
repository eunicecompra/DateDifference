package com.app.test;

import com.app.DateDifferenceRunner;
import com.app.util.DateValidator;
import com.app.util.DateValidatorMessagesEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DateValidatorTest {

    DateValidator validator;

    @Before
    public void init() {
        validator = new DateValidator();
    }

    @Test
    public void checkNullInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_ARGUMENTS,
                validator.validateInput(null));
    }

    @Test
    public void checkEmptyInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_ARGUMENTS,
                validator.validateInput(""));
    }

    @Test
    public void checkStringInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_ARGUMENTS,
                validator.validateInput("12 cdesfg, hi20flmn"));
    }

    @Test
    public void checkInvalidYearInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_YEAR,
                validator.validateInput("22 02 1895, 20 14 2010"));
    }

    @Test
    public void checkInvalidMonthInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_DATE_FORMAT,
                validator.validateInput("02 22 1995, 20 14 2010"));
    }

    @Test
    public void checkInvalidDayInput() {
        assertEquals(DateValidatorMessagesEnum.INVALID_DATE_FORMAT,
                validator.validateInput("29 02 2003, 31 04 2010"));
    }


}
