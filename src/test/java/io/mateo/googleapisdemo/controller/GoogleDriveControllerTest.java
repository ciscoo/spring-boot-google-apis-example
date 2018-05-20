package io.mateo.googleapisdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.google.api.services.drive.model.File;
import io.mateo.googleapisdemo.service.GoogleDriveService;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GoogleDriveControllerTest {
    private GoogleDriveController googleDriveController;

    @Mock private GoogleDriveService googleDriveService;

    @Before
    public void setUp() {
        googleDriveController = new GoogleDriveController(googleDriveService);
    }

    @Test
    public void shouldGetFileInfo() throws IOException {
        when(googleDriveService.getFileMetaData(anyString())).thenReturn(new File());
        assertThat(googleDriveController.getFileInfo("")).isInstanceOf(File.class);
    }

    @Test
    public void shouldGetRawFile() throws IOException {
        when(googleDriveService.getFile(anyString())).thenReturn(new byte[] {});
        assertThat(googleDriveController.getFile("")).isInstanceOf(byte[].class);
    }
}
