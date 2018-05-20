package io.mateo.googleapisdemo.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Auto-configuration for {@link Drive}. */
@Configuration
@Profile("!test")
public class GoogleDriveConfig {
    private GoogleCredential googleCredential;
    private NetHttpTransport netHttpTransport;
    private JacksonFactory jacksonFactory;

    public GoogleDriveConfig(
            GoogleCredential googleCredential,
            NetHttpTransport netHttpTransport,
            JacksonFactory jacksonFactory) {
        this.googleCredential = googleCredential;
        this.netHttpTransport = netHttpTransport;
        this.jacksonFactory = jacksonFactory;
    }

    /**
     * Provides a Google Drive client.
     *
     * @return {@link Drive}
     */
    @Bean
    public Drive googleDrive() {
        return new Drive(netHttpTransport, jacksonFactory, googleCredential);
    }
}
