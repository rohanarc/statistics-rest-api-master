package com.n26.statistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class StaleTransactionException extends RuntimeException {

	private static final long serialVersionUID = -7395830904705579118L;

	public StaleTransactionException(String message) {
        super(message);
    }
}