package com.treetrunk.trek.exception;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String error) {
        super(error);
    }
}
