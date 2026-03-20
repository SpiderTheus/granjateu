package com.spider.granjateu.services.exceptions;

public class StatusInvalidExecption extends RuntimeException {

    public StatusInvalidExecption(String message) {
        super("Status inválido: " + message);
    }

}
