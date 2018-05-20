package io.mateo.googleapisdemo.advice;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleApiClientExceptionHandlerTest {
    private GoogleApiClientExceptionHandler googleApiClientExceptionHandler;

    @Before
    public void setup() {
        googleApiClientExceptionHandler = new GoogleApiClientExceptionHandler();
    }

    @Test
    public void testShouldHandleJsonErrors() {
        GoogleJsonResponseException googleJsonResponseException = mock(GoogleJsonResponseException.class);
        GoogleJsonError googleJsonError = mock(GoogleJsonError.class);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        when(googleJsonResponseException.getDetails()).thenReturn(googleJsonError);
        when(googleJsonError.getCode()).thenReturn(httpStatus.value());

        ResponseEntity<GoogleJsonError> responseEntity = googleApiClientExceptionHandler.handleGoogleApiErrors(googleJsonResponseException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(httpStatus);
    }

    @Test
    public void testShouldHandleStreamErrors() {
        IOException ioException = mock(IOException.class);
        when(ioException.getMessage()).thenReturn("");

        ResponseEntity<Map<String, String>> responseEntity = googleApiClientExceptionHandler.handleGoogleApiErrors(ioException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        Map<String, String> body = Objects.requireNonNull(responseEntity.getBody());
        assertThat(body.containsKey("message")).isTrue();
        assertThat(body.get("message")).isEmpty();
    }
}
