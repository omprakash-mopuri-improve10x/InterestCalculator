package com.prakash.simpleinterest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getDiffDate(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate localDate1 = LocalDate.parse(fromDate, formatter);
        LocalDate localDate2 = LocalDate.parse(toDate, formatter);
        long yearsDifference = ChronoUnit.YEARS.between(localDate2, localDate1);
        localDate2 = localDate2.plusYears(yearsDifference);
        long monthsDifference = ChronoUnit.MONTHS.between(localDate2, localDate1);
        localDate2 = localDate2.plusMonths(monthsDifference);
        long daysDifference = ChronoUnit.DAYS.between(localDate2, localDate1);
        String dateFormat = Math.abs(yearsDifference) + " years " + Math.abs(monthsDifference) + " months " + Math.abs(daysDifference) + " days";
        return dateFormat;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static long getDiffDays(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate localDate1 = LocalDate.parse(fromDate, formatter);
        LocalDate localDate2 = LocalDate.parse(toDate, formatter);
        long diffDays = ChronoUnit.DAYS.between(localDate2, localDate1);
        return Math.abs(diffDays);
    }
}
