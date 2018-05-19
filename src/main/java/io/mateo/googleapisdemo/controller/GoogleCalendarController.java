package io.mateo.googleapisdemo.controller;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import io.mateo.googleapisdemo.advice.GoogleApiClientExceptionHandler;
import io.mateo.googleapisdemo.service.GoogleCalendarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/calendars")
@RestController
public class GoogleCalendarController {
    private GoogleCalendarService googleCalendarService;

    public GoogleCalendarController(GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }

    /**
     * Retrieve information for a calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Calendar}
     * @throws IOException when the Google API returned an error code; handled by {@link GoogleApiClientExceptionHandler#handleGoogleApiErrors(GoogleJsonResponseException)}.
     */
    @GetMapping("/{calendarId}")
    public Calendar getCalendarInfo(@PathVariable String calendarId) throws IOException {
        return googleCalendarService.getMetaInfoForCalendar(calendarId);
    }

    /**
     * Retrieve list of events for a calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Events}
     * @throws IOException when the Google API returned an error code; handled by {@link GoogleApiClientExceptionHandler#handleGoogleApiErrors(GoogleJsonResponseException)}.
     */
    @GetMapping("/{calendarId}/events")
    public Events getEventsForCalendar(@PathVariable String calendarId) throws IOException {
        return googleCalendarService.getEventsForCalendar(calendarId);
    }

    /**
     * Retrieve list of events for a calendar.
     *
     * @param calendarId Calendar identifier
     * @param eventId Event identifier
     * @return {@link Event}
     * @throws IOException when the Google API returned an error code; handled by {@link GoogleApiClientExceptionHandler#handleGoogleApiErrors(GoogleJsonResponseException)}.
     */
    @GetMapping("/{calendarId}/events/{eventId}")
    public Event getEventsForCalendar(@PathVariable String calendarId, @PathVariable String eventId) throws IOException {
        return googleCalendarService.getEventInfoFromCalendar(calendarId, eventId);
    }
}
