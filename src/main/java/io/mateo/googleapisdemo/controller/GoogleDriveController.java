package io.mateo.googleapisdemo.controller;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.drive.model.File;
import io.mateo.googleapisdemo.advice.GoogleApiClientExceptionHandler;
import io.mateo.googleapisdemo.service.GoogleDriveService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/drive/files")
@RestController
public class GoogleDriveController {
    private GoogleDriveService googleDriveService;

    public GoogleDriveController(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    /**
     * Retrieves a file's metadata by ID.
     *
     * @param fileId The ID of the file
     * @return {@link File}
     * @throws IOException when the Google API returned an error code; handled by {@link GoogleApiClientExceptionHandler#handleGoogleApiErrors(GoogleJsonResponseException)}.
     */
    @GetMapping("/{fileId}")
    public File getFileInfo(@PathVariable String fileId) throws IOException {
        return googleDriveService.getFileMetaData(fileId);
    }

    /**
     * Retrieves the raw file from Google Drive.
     *
     * @param fileId The ID of the file
     * @return {@link File}
     * @throws IOException when the Google API returned an error code; handled by {@link GoogleApiClientExceptionHandler#handleGoogleApiErrors(GoogleJsonResponseException)}.
     */
    @GetMapping("/{fileId}/media")
    @ResponseBody
    public byte[] getFile(@PathVariable String fileId) throws IOException {
        return googleDriveService.getFile(fileId);
    }
}
