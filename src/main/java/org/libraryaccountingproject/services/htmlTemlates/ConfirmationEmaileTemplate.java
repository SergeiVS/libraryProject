package org.libraryaccountingproject.services.htmlTemlates;

import constatnts.UrlPaths;

public class ConfirmationEmaileTemplate {

    public static String confirmationMessage(String confirmationMessage, String firstName, String lastName) {
        return "<p>Dear\"" + firstName + "\" \"" + lastName + "\"</p>" +
                "<p>We are glad that you registered in our library.</p>" +
                "<p>To complete your registration please confirm it on link below</p>" +
                "<p><a href=" + UrlPaths.CONFIRMATION_MESSAGE_PATH + confirmationMessage + ">Confirm your registration here.</a></p>" +
                "<p>Your Library Team</p>";
    }
}
