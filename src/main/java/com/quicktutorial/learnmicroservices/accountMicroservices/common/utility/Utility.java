package com.quicktutorial.learnmicroservices.accountMicroservices.common.utility;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.quicktutorial.learnmicroservices.accountMicroservices.common.Constant.LAST_UPDATE_CLOSE_FORMAT;
import static com.quicktutorial.learnmicroservices.accountMicroservices.common.Constant.LAST_UPDATE_TIMESTAMP_FORMAT;

@Component
@Slf4j
@NoArgsConstructor
public class Utility {

    public static String getNumberFormatted(Double value, int minFraDig, int maxFraDig) {
        DecimalFormat formater = new DecimalFormat();
        formater.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALIAN));
        formater.setGroupingUsed(true);
        formater.setMinimumIntegerDigits(1);
        formater.setMinimumFractionDigits(minFraDig);
        formater.setMaximumFractionDigits(maxFraDig);
        DecimalFormatSymbols symbols = formater.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        formater.setDecimalFormatSymbols(symbols);
        return formater.format(value);
    }

    public static String fromLocalDateTimeToLastUpdate(LocalDateTime dateTime){
        if (dateTime.getHour()==0 && dateTime.getMinute()==0){
            return fromLocalDateTimeToString(dateTime,LAST_UPDATE_CLOSE_FORMAT);
        } else {
            return fromLocalDateTimeToString(dateTime,LAST_UPDATE_TIMESTAMP_FORMAT);
        }
    }

    public static String fromLocalDateTimeToString(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static String fromDateToString(Date date, String format) {
        SimpleDateFormat dateParser = new SimpleDateFormat(format);
        return dateParser.format(date);
    }


}
