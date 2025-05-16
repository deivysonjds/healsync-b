package com.pi.healsync.exceptions;

public class NoSuchException extends RuntimeException {

    public NoSuchException(String objectName){
        super(objectName + " não localizado!");
    }
}
