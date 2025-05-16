package com.pi.healsync.exceptions;

public class ObjectNotCreated extends RuntimeException {

    public ObjectNotCreated(){
        super("Erro ao inserir objeto no banco de dados");
    }
}
