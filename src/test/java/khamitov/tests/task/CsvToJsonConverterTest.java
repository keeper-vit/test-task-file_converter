package khamitov.tests.task;

import com.fasterxml.jackson.databind.MappingIterator;
import khamitov.tests.task.csv.PayloadParser;
import khamitov.tests.task.json.PayloadWriter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
class CsvToJsonConverterTest {
    @Test
    void convert() throws IOException {
        PayloadParser parser = mock(PayloadParser.class);
        ArgumentCaptor<Reader> reader = ArgumentCaptor.forClass(Reader.class);
        when(parser.pars(reader.capture())).thenReturn(mock(MappingIterator.class));

        PayloadWriter writer = mock(PayloadWriter.class);
        CsvToJsonConverter converter = new CsvToJsonConverter(parser, writer);

        converter.convert(mock(Reader.class), mock(Path.class));

        ArgumentCaptor<List<Payload>> payloads = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<File> file = ArgumentCaptor.forClass(File.class);
        verify(writer).writePayloadsToFile(payloads.capture(), file.capture());
    }

}