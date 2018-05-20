package io.mateo.googleapisdemo.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.drive.DriveScopes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

/**
 * Auto-configuration for {@link GoogleCredential}, {@link NetHttpTransport}, and {@link
 * JacksonFactory}.
 */
@Configuration
@Profile("!test")
public class GoogleApiClientConfig {
    /**
     * Provides a unmodifiable {@link Set<String>} of Google OAuth 2.0 scopes to be used.
     *
     * @return An unmodifiable {@link Set<String>}
     */
    private Set<String> googleOAuth2Scopes() {
        Set<String> googleOAuth2Scopes = new HashSet<>();
        googleOAuth2Scopes.add(CalendarScopes.CALENDAR_READONLY);
        googleOAuth2Scopes.add(DriveScopes.DRIVE_READONLY);
        return Collections.unmodifiableSet(googleOAuth2Scopes);
    }

    /**
     * Provides a global {@link GoogleCredential} instance for use for all Google API calls.
     *
     * @return {@link GoogleCredential}
     */
    @Bean
    public GoogleCredential googleCredential() throws IOException {
        File serviceAccount = new ClassPathResource("serviceAccount.json").getFile();
        return GoogleCredential.fromStream(new FileInputStream(serviceAccount))
                .createScoped(googleOAuth2Scopes());
    }

    /**
     * A preconfigured HTTP client for calling out to Google APIs.
     *
     * @return {@link NetHttpTransport}
     */
    @Bean
    public NetHttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    /**
     * Abstract low-level JSON factory.
     *
     * @return {@link JacksonFactory}
     */
    @Bean
    public JacksonFactory jacksonFactory() {
        return JacksonFactory.getDefaultInstance();
    }
}
