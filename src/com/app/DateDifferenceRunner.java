package com.app;

import com.app.util.DateConstants;
import com.app.util.DateValidator;
import com.app.util.DateValidatorMessagesEnum;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DateDifferenceRunner {

    public static void main(String[] args) {
        System.out.println("Hi, there! \n" +
                "Please input 2 dates in 'DD MM YYYY, DD MM YYYY' format," +
                " and we will calculate the difference. Or you may key in 'q' to quit.\n\n");

        String input = null;
        while (input == null || !input.trim().equalsIgnoreCase("q")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if ( input.trim().equalsIgnoreCase("q") ) {
                break;
            }
            new DateDifferenceRunner().calculateDifference(input);
        }


    }

    public int calculateDifference(String datesInput) {
        DateValidator validator = new DateValidator();
        DateValidatorMessagesEnum msg = validator.validateInput(datesInput.trim());
        if ( msg != null ) {
            System.out.println ( msg.getDisplayMsg() );
        } else {
            Pattern patternComma = Pattern.compile(",");
            String[] datesArray = patternComma.split(datesInput.trim());

            String startDate = datesArray[0];
            String endDate = datesArray[1].trim(); //With leading space

            Pattern patternSpace = Pattern.compile(" ");

            String[] startDateArray = patternSpace.split(startDate);
            int startDay = Integer.parseInt(startDateArray[0]);
            int startMonth = Integer.parseInt(startDateArray[1]);
            int startYear = Integer.parseInt(startDateArray[2]);

            String[] endDateArray = patternSpace.split(endDate);
            int endDay = Integer.parseInt(endDateArray[0]);
            int endMonth = Integer.parseInt(endDateArray[1]);
            int endYear = Integer.parseInt(endDateArray[2]);

            int dateDifference = 0;

            //Determine the correct date sequence
            int day1 = calculateDaysBeforeEndMonth(startMonth, startYear) + startDay + startYear;
            int day2 = calculateDaysBeforeEndMonth(endMonth, endYear) + endDay + endYear;
            if (day1 > day2) { //Reorder dates
                int tmpDay = startDay;
                int tmpMonth = startMonth;
                int tmpYear = startYear;

                startDay = endDay;
                startMonth = endMonth;
                startYear = endYear;

                endDay = tmpDay;
                endMonth = tmpMonth;
                endYear = tmpYear;
            }


            //Calculate date difference
            int daysOfStartMonth = DateConstants.MONTHS_DAY_ARRAY[startMonth - 1] - startDay + 1;
            int daysRemainingOfStartYear = calculateDaysRestOfYear(startMonth, startYear);
            int daysInBetweenYears = calculateDaysInBetweenYears(startYear, endYear);
            int daysBeforeEndMonth = calculateDaysBeforeEndMonth(endMonth, endYear);
            int daysOfEndMonth = endDay - 1;

            dateDifference = daysOfStartMonth + daysRemainingOfStartYear + daysInBetweenYears
                    + daysBeforeEndMonth + daysOfEndMonth;
            if ( startYear == endYear ) {
                dateDifference -= 365;
            }

            System.out.println (
                    String.format("%02d",startDay) + " " +  String.format("%02d",startMonth) + " " + startYear + ", " +
                            String.format("%02d",endDay) + " " +  String.format("%02d",endMonth) + " " + endYear +
                            ", " + dateDifference
            );

        }

        return 0;
    }

    private int calculateDaysBeforeEndMonth(int month, int year){
        int daysBeforeEndMonth = 0;

        if ( month == 1 ) {
            return 0;
        }

        boolean leapYear = year % 4 == 0;

        int[] daysBeforeEndMonthArray =
                Arrays.copyOfRange(DateConstants.MONTHS_DAY_ARRAY,0,(month-1));

        for ( int days : daysBeforeEndMonthArray ) {
            if ( days == 28 && leapYear ) {
                daysBeforeEndMonth += 1;
            }
            daysBeforeEndMonth += days;
        }

        return daysBeforeEndMonth;
    }

    private int calculateDaysRestOfYear(int month, int year) {
        int daysRestOfYear = 0;

        if ( month == 12 ) {
            return 0;
        }

        boolean leapYear = year % 4 == 0;

        int[] remDaysOfYearArray =
                Arrays.copyOfRange(DateConstants.MONTHS_DAY_ARRAY,month,12);

        for ( int days : remDaysOfYearArray ) {
            if ( days == 28 && leapYear ) {
                daysRestOfYear += 1;
            }
            daysRestOfYear += days;
        }

        return daysRestOfYear;
    }

    private int calculateDaysInBetweenYears(int startYear, int endYear) {
        int daysInBetweenYears = 0;

        if ( startYear == endYear ) {
            daysInBetweenYears = 0;
        } else {
            int noOfYearsDiff = (endYear - 1) - startYear;
            daysInBetweenYears = noOfYearsDiff * 365;

            if ( daysInBetweenYears != 0 ) {
                int noOfLeapYears = ((endYear-1) / 4) - ((startYear-1) / 4);
                daysInBetweenYears += noOfLeapYears;
            }

        }

        return daysInBetweenYears;
    }
}
