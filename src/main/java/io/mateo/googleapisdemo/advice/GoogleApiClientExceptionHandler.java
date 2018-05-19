package io.mateo.googleapisdemo.advice;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import io.mateo.googleapisdemo.controller.GoogleCalendarController;
import io.mateo.googleapisdemo.controller.GoogleDriveController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for handling any errors that occur when making requests to Google APIs. Typically this controller advice
 * is invoked when the HTTP request succeeded, but the Google API returned an error.
 */
@RestControllerAdvice(assignableTypes = {GoogleDriveController.class, GoogleCalendarController.class})
public class GoogleApiClientExceptionHandler {
    /**
     * Handle errors returned from a HTTP request to a Google API.
     *
     * @param e {@link GoogleJsonResponseException}
     * @return {@link GoogleJsonError}
     */
    @ExceptionHandler(GoogleJsonResponseException.class)
    public ResponseEntity<GoogleJsonError> handleGoogleApiErrors(GoogleJsonResponseException e) {
        GoogleJsonError googleJsonError = e.getDetails();
        HttpStatus httpStatus = HttpStatus.valueOf(googleJsonError.getCode());
        return new ResponseEntity<>(googleJsonError, httpStatus);
    }

    /**
     * Handle errors when trying to get the raw response.
     *
     * @param e {@link IOException}
     * @return {@link Map<String, String>}
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, String>> handleGoogleApiErrors(IOException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
