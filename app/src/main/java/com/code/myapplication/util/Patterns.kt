package com.code.myapplication.util

import java.util.regex.Pattern


object Patterns {

    val JORDANIAN_PHONE_NUMBER: Pattern =
        Pattern.compile("^(00962|962|\\+962|0)(7)([789])([0-9]{7})\$")
    val ONLY_ENGLISH_LETTER_PATTERN_WITH_SPACE: Pattern = Pattern.compile("^[a-zA-Z ]+$")
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" + "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{8,}" +  //at least 8 characters
                "$"
    )
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"
    )

    val NATIONAL_ID_PATTERN : Pattern = Pattern.compile("^[0-9]+\$")
}