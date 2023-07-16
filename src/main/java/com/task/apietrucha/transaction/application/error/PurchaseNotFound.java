package com.task.apietrucha.transaction.application.error;

public class PurchaseNotFound extends RuntimeException {

    public PurchaseNotFound(String message) {
        super(message);
    }
}
