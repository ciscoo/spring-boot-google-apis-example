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
     * TODO: Take advantage of ByteArrayHttpMessageConverter instead of manually building the response.
     *
     * @param fileId The ID of the file
     * @return {@link ResponseEntity}
     * @throws IOException when an error occurs in the HTTP request
     */
    public ResponseEntity<byte[]> getFile(@NonNull String fileId) throws IOException {
        File fileMetadata = getFileMetaData(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseEntity<byte[]> response;
        byte[] media;

        httpHeaders.setContentType(MediaType.valueOf(fileMetadata.getMimeType()));
        httpHeaders.setCacheControl(CacheControl.noCache());

        try (InputStream data = googleDrive.files().get(fileId).executeMediaAsInputStream()) {
            media = IOUtils.toByteArray(data);
            response = new ResponseEntity<>(media, httpHeaders, HttpStatus.OK);
        }

        return response;
    }
}
