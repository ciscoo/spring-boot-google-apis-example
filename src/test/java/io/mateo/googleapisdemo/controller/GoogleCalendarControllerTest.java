package io.mateo.googleapisdemo.controller;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import io.mateo.googleapisdemo.service.GoogleCalendarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleCalendarControllerTest {
    private GoogleCalendarController googleCalendarController;

    @Mock
    private GoogleCalendarService googleCalendarService;

    @Before
    public void setUp() {
        googleCalendarController = new GoogleCalendarController(googleCalendarService);
    }

    @Test
    public void shouldGetCalendarInfo() throws IOException {
        when(googleCalendarService.getMetaInfoForCalendar(anyString())).thenReturn(new Calendar());
        assertThat(googleCalendarController.getCalendarInfo("")).isInstanceOf(Calendar.class);
    }

    @Test
    public void shouldGetEventsForCalendar() throws IOException {
        when(googleCalendarService.getEventsForCalendar(anyString())).thenReturn(new Events());
        assertThat(googleCalendarController.getEventsForCalendar("")).isInstanceOf(Events.class);
    }

    @Test
    public void shouldGetEventInfoFromCalendar() throws IOException {
        when(googleCalendarService.getEventInfoFromCalendar(anyString(), anyString())).thenReturn(new Event());
        assertThat(googleCalendarController.getEventsForCalendar("", "")).isInstanceOf(Event.class);
    }

}
