package com.quicktutorial.learnmicroservices.accountMicroservices.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Component
public class Utility {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    public Utility() {
    }

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
