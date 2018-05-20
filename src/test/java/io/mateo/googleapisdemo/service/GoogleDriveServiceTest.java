package io.mateo.googleapisdemo.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.model.File;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoogleDriveServiceTest {
    @Mock
    private Drive googleDrive;

    @Mock
    private Files files;

    @Mock
    private Get filesGet;

    private GoogleDriveService googleDriveService;

    @Before
    public void setUp() {
        googleDriveService = new GoogleDriveService(googleDrive);
    }

    @Test
    public void shouldGetFileMetaData() throws IOException {
        when(googleDrive.files()).thenReturn(files);
        when(files.get(anyString())).thenReturn(filesGet);
        when(filesGet.execute()).thenReturn(new File());
        assertThat(googleDriveService.getFileMetaData("")).isInstanceOf(File.class);
    }

    @Test
    public void shouldGetFile() throws IOException {
        InputStream inputStream = mock(InputStream.class);

        when(inputStream.read(any(byte[].class))).thenReturn(-1);
        when(googleDrive.files()).thenReturn(files);
        when(files.get(anyString())).thenReturn(filesGet);
        when(filesGet.executeMediaAsInputStream()).thenReturn(inputStream);

        assertThat(googleDriveService.getFile("")).isInstanceOf(byte[].class);
    }
}
