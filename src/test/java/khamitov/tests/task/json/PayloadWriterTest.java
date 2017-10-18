package khamitov.tests.task.json;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
class PayloadWriterTest {
    @Test
    void writePayloadsToFile() throws IOException {
        ObjectWriter objectWriter = mock(ObjectWriter.class);
        PayloadWriter writer = new PayloadWriter(objectWriter);

        writer.writePayloadsToFile(mock(List.class), mock(File.class));

        ArgumentCaptor<File> file = ArgumentCaptor.forClass(File.class);
        ArgumentCaptor<List> payloads = ArgumentCaptor.forClass(List.class);
        verify(objectWriter).writeValue(file.capture(), payloads.capture());
    }

}