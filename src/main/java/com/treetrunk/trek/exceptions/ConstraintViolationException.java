package com.treetrunk.trek.exceptions;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String error) {
        super(error);
    }
}
