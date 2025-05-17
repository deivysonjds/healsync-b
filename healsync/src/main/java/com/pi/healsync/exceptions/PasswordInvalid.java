package com.pi.healsync.exceptions;

public class PasswordInvalid extends RuntimeException {
    public PasswordInvalid(){
        super("Senha incorreta");
    }
}
