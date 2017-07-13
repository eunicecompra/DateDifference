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
                " and we will calculate the difference. Or you may key in 'q' to quit.");
        System.out.println("Supported dates are between 1900 and 2010.\n");

        String input = null;
        while (input == null || !input.trim().equalsIgnoreCase("q")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if ( input.trim().equalsIgnoreCase("q") ) {
                break;
            }
            String displayDateDifference = new DateDifferenceRunner().calculateDifference(input);
            System.out.println(displayDateDifference);
        }


    }

    public String calculateDifference(String datesInput) {
        String displayDateDifference = null;

        DateValidator validator = new DateValidator();
        DateValidatorMessagesEnum msg = validator.validateInput(datesInput);
        if ( msg != null ) {
            displayDateDifference = msg.getDisplayMsg();
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
            //Interchange if not in the correct sequence
            boolean reorder = false;
            if (startYear > endYear ) { //Reorder dates
                reorder = true;
            } else if ( startYear == endYear ) {
                int noOfDaysStartDate = calculateDaysBeforeEndMonth(startMonth, startYear) + startDay;
                int noOfDaysEndDate = calculateDaysBeforeEndMonth(endMonth, endYear) + endDay;
                if ( noOfDaysStartDate > noOfDaysEndDate ) {
                    reorder = true;
                }
            }
            if ( reorder ) {
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


            //Calculate the date difference by getting the following:
            //1. No. of days from the start date to the end day of the start month
            int daysOfStartMonth = DateConstants.MONTHS_DAY_ARRAY[startMonth - 1] - startDay + 1;
            //2. No. of days from next month of the start date to the rest of the year
            int daysRemainingOfStartYear = calculateDaysRestOfYear(startMonth, startYear);
            //3. No. of days in between the start date year and the end date year
            int daysInBetweenYears = calculateDaysInBetweenYears(startYear, endYear);
            //4. No. of days from the start of the year to the month before the end date
            int daysBeforeEndMonth = calculateDaysBeforeEndMonth(endMonth, endYear);
            //5. No. of days of the end month
            int daysOfEndMonth = endDay - 1;


            //Sum up everything
            dateDifference = daysOfStartMonth + daysRemainingOfStartYear + daysInBetweenYears
                    + daysBeforeEndMonth + daysOfEndMonth;

            //For the same year, need to subtract the total no. of days of a given year.
            if ( startYear == endYear ) {
                if (startYear % 4 == 0) {
                    dateDifference -= 366;
                } else {
                    dateDifference -= 365;
                }
            }


            displayDateDifference = String.format("%02d",startDay) + " " +  String.format("%02d",startMonth) + " " + startYear + ", " +
                            String.format("%02d",endDay) + " " +  String.format("%02d",endMonth) + " " + endYear +
                            ", " + dateDifference;

        }

        return displayDateDifference;
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
                int noOfLeapYears = ((endYear-1) / 4) - (startYear / 4);
                daysInBetweenYears += noOfLeapYears;
            }

        }

        return daysInBetweenYears;
    }
}
