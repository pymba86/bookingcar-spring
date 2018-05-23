package ru.pymba86.bx.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Alert", message);
        headers.add("Params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String param) {
        return createAlert("Create: " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlert( String param) {
        return createAlert("Update: " + param, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String param) {
        return createAlert("Delete: " + param, param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error", defaultMessage);
        headers.add("Params", entityName);
        return headers;
    }
}