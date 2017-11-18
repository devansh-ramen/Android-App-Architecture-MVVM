package com.devanshramen.loginapplication.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by devanshramen on 11/19/17.
 */

public class EmailUtils {
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
