package org.libraryaccountingproject.services.exeptions;

public class NotCreatedException extends RuntimeException {

    public NotCreatedException(String message) {
        super(message);
    }
}
