package com.seveneleven.mycontact.user.validation;

public class PasswordValidator {

    public static boolean isValid(String password) {
        return password.length() >= 6;
    }
}
