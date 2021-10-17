package com.dimasguilherme.exception;

public class MyApplicationException extends Exception {
    public MyApplicationException() {
        super();
    }

    public MyApplicationException(String msg) {
        super(msg);
    }

    public MyApplicationException(String msg, Exception e) {
        super(msg, e);
    }
}