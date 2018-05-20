package io.mateo.googleapisdemo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GoogleCalendarServiceTest {
    @Mock private com.google.api.services.calendar.Calendar googleCalendar;

    @Mock private com.google.api.services.calendar.Calendar.Calendars calendars;

    @Mock private com.google.api.services.calendar.Calendar.Calendars.Get calendarsGet;

    @Mock private com.google.api.services.calendar.Calendar.Events events;

    @Mock private com.google.api.services.calendar.Calendar.Events.List eventsList;

    @Mock private com.google.api.services.calendar.Calendar.Events.Get eventsGet;

    private GoogleCalendarService googleCalendarService;

    @Before
    public void setUp() {
        googleCalendarService = new GoogleCalendarService(googleCalendar);
    }

    @Test
    public void shouldGetMetaInfoForCalendar() throws IOException {
        when(googleCalendar.calendars()).thenReturn(calendars);
        when(calendars.get(anyString())).thenReturn(calendarsGet);
        when(calendarsGet.execute()).thenReturn(new Calendar());
        assertThat(googleCalendarService.getMetaInfoForCalendar("")).isInstanceOf(Calendar.class);
    }

    @Test
    public void shouldGetEventsForCalendar() throws IOException {
        when(googleCalendar.events()).thenReturn(events);
        when(events.list(anyString())).thenReturn(eventsList);
        when(eventsList.execute()).thenReturn(new Events());
        assertThat(googleCalendarService.getEventsForCalendar("")).isInstanceOf(Events.class);
    }

    @Test
    public void shouldGetEventInfoFromCalendar() throws IOException {
        when(googleCalendar.events()).thenReturn(events);
        when(events.get(anyString(), anyString())).thenReturn(eventsGet);
        when(eventsGet.execute()).thenReturn(new Event());
        assertThat(googleCalendarService.getEventInfoFromCalendar("", ""))
                .isInstanceOf(Event.class);
    }
}
