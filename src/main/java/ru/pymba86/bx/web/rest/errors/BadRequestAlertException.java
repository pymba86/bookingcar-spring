package ru.pymba86.bx.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadRequestAlertException extends AbstractThrowableProblem {


    private final String errorKey;

    public BadRequestAlertException(String defaultMessage, String errorKey) {
        super(null, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters( errorKey));
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters( String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
         return parameters;
    }
}
