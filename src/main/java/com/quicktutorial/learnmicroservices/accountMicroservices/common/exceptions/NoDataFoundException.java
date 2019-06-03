package com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions;

/**
 * <p>Description:  <!--Insert description here--!></p>
 * <p>Copyright:    Copyright (c) 2018</p>
 * <p>Company:      Sopra Steria</p>
 *
 * @author U0J3718
 * @version $Revision:  $ ($Date:  $)
 * <p>
 * .              ,,__
 * .     ,,,     / o._)
 * .   _/---\_   \-'|
 * .  /       \__/ /
 * . '\  \__\  _.'
 * .  )\ |  )\ |
 * . // \\ // \\
 * .||_  \\|_  \\_
 * .'--' '--'' '--'
 * .MORDI IL CODICE
 */
public class NoDataFoundException extends Exception {
    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
