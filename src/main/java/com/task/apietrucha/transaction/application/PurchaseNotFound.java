package com.task.apietrucha.transaction.application;

public class PurchaseNotFound extends RuntimeException {

    public PurchaseNotFound(String message) {
        super(message);
    }
}
