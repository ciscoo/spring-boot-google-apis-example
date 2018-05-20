package io.mateo.googleapisdemo.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Service to perform API operations for Google Calendar API.
 */
@Service
public class GoogleDriveService {
    private Drive googleDrive;

    public GoogleDriveService(Drive googleDrive) {
        this.googleDrive = googleDrive;
    }

    /**
     * Get a file's metadata by ID.
     *
     * @param fileId The ID of the file
     * @return {@link File}
     * @throws IOException when an error occurs in the HTTP request
     */
    public File getFileMetaData(@NonNull String fileId) throws IOException {
        return googleDrive.files().get(fileId).execute();
    }


    /**
     * Get the raw file.
     *
     * @param fileId The ID of the file
     * @return {@link ResponseEntity}
     * @throws IOException when an error occurs in the HTTP request
     */
    public byte[] getFile(@NonNull String fileId) throws IOException {
        byte[] media;
        try (InputStream data = googleDrive.files().get(fileId).executeMediaAsInputStream()) {
            media = IOUtils.toByteArray(data);
        }
        return media;
    }
}
