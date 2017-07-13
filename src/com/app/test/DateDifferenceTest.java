package com.app.test;

import com.app.DateDifferenceRunner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DateDifferenceTest {

    DateDifferenceRunner runner;

    @Before
    public void init() {
        runner = new DateDifferenceRunner();
    }

    @Test
    public void checkNullInput() {
        String diff = runner.calculateDifference(null);
        System.out.println("\nTest Case: Null Input");
        System.out.println("Input: " + null);
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"Please input 2 dates i.e. DD MM YYYY, DD MM YYYY");
    }

    @Test
    public void checkStringInput() {
        String diff = runner.calculateDifference("12 cdesfg, hi20flmn");
        System.out.println("\nTest Case: Invalid String Input");
        System.out.println("Input: " + "12 cdesfg, hi20flmn");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"Please input 2 dates i.e. DD MM YYYY, DD MM YYYY");
    }

    @Test
    public void checkInvalidDatesInput() {
        String diff = runner.calculateDifference("01 01 1990, 29 02 1991");
        System.out.println("\nTest Case: Invalid Dates");
        System.out.println("Input: " + "01 01 1990, 29 02 1991");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"Please input the dates in DD MM YYYY format i.e. 30 07 2010");
    }

    @Test
    public void checkInvalidStartYearInput() {
        String diff = runner.calculateDifference("22 02 1895, 20 14 2010");
        System.out.println("\nTest Case: Invalid Start Year");
        System.out.println("Input: " + "22 02 1895, 20 14 2010");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"Oops sorry, you can only input dates between 1900 and 2010.");
    }

    @Test
    public void checkInvalidEndYearInput() {
        String diff = runner.calculateDifference("22 02 1990, 20 14 2017");
        System.out.println("\nTest Case: Invalid Year");
        System.out.println("Input: " + "22 02 1990, 20 14 2017");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"Oops sorry, you can only input dates between 1900 and 2010.");
    }

    @Test
    public void checkStartDateBeforeEndDate() {
        String diff = runner.calculateDifference("08 01 1995, 24 12 2005");
        System.out.println("\nTest Case: Start Date is before the End Date.");
        System.out.println("Input: " + "08 01 1995, 24 12 2005");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"08 01 1995, 24 12 2005, 4003");
    }

    @Test
    public void checkStartDateAfterEndDate() {
        String diff = runner.calculateDifference("15 04 1969, 12 09 1945");
        System.out.println("\nTest Case: Start Date is after the End Date.");
        System.out.println("Input: " + "15 04 1969, 12 09 1945");
        System.out.println("Output: " + diff + "\n");

        assertEquals(diff,"12 09 1945, 15 04 1969, 8616");
    }

    @Test
    public void checkEqualDates() {
        String diff = runner.calculateDifference("06 06 2010, 06 06 2010");
        System.out.println("\nTest Case: Start Date and End Date are the same.");
        System.out.println("Input: " + "06 06 2010, 06 06 2010");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"06 06 2010, 06 06 2010, 0");
    }

    @Test
    public void checkLeapYearStartDateNormalEndDate() {
        String diff = runner.calculateDifference("20 04 1980, 12 11 1997");
        System.out.println("\nTest Case: Start Date is a leap year and End Date is a normal year.");
        System.out.println("Input: " + "20 04 1980, 12 11 1997");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"20 04 1980, 12 11 1997, 6415");
    }

    @Test
    public void checkStartDateLeapYearEndDate() {
        String diff = runner.calculateDifference("01 04 1975, 12 11 1984");
        System.out.println("\nTest Case: Start Date is a normal year and End Date is a leap year.");
        System.out.println("Input: " + "01 04 1975, 12 11 1984");
        System.out.println("Output: " + diff + "\n");
        assertEquals(diff,"01 04 1975, 12 11 1984, 3513");
    }
}
