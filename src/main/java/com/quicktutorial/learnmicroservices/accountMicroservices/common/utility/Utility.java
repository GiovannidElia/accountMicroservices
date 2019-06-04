package com.quicktutorial.learnmicroservices.accountMicroservices.common.utility;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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


}
