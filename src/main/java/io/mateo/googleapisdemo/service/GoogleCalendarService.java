package io.mateo.googleapisdemo.service;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service to perform API operations for Google Calendar API.
 */
@Service
public class GoogleCalendarService {
    private com.google.api.services.calendar.Calendar googleCalendar;

    public GoogleCalendarService(com.google.api.services.calendar.Calendar googleCalendar) {
        this.googleCalendar = googleCalendar;
    }

    /**
     * Returns metadata for a calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Calendar}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Calendar getMetaInfoForCalendar(@NonNull String calendarId) throws IOException {
        return googleCalendar.calendars().get(calendarId).execute();
    }

    /**
     * Returns events on the specified calendar.
     *
     * @param calendarId Calendar identifier
     * @return {@link Events}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Events getEventsForCalendar(@NonNull String calendarId) throws IOException {
        return googleCalendar.events().list(calendarId).execute();
    }

    /**
     * Returns an event.
     *
     * @param calendarId Calendar identifier
     * @param eventId Event identifier
     * @return {@link Event}
     * @throws IOException when an error occurs in the HTTP request
     */
    public Event getEventInfoFromCalendar(@NonNull String calendarId, @NonNull String eventId) throws IOException {
        return googleCalendar.events().get(calendarId, eventId).execute();
    }
}
