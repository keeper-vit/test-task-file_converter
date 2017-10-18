package khamitov.tests.task.json;

import com.fasterxml.jackson.databind.ObjectWriter;
import khamitov.tests.task.Payload;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
public class PayloadWriter {

    private final ObjectWriter writer;

    public PayloadWriter(ObjectWriter writer) {
        this.writer = writer;
    }

    public void writePayloadsToFile(List<Payload> payloads, File file) throws IOException {
        writer.writeValue(file, payloads);
    }
}
