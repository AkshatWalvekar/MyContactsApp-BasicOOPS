package com.seveneleven.mycontact.user.validation;

public class EmailValidator {

	//Validating email
    public static boolean isValid(String email) {
        return email.contains("@") && email.contains(".");
    }
}