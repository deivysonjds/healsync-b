package com.pi.healsync.exceptions;

public class ObjectNotCreated extends RuntimeException {

    public ObjectNotCreated(Throwable e){
        super("Erro ao inserir objeto no banco de dados", e);
    }
}
