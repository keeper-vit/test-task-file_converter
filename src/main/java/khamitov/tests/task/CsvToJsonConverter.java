package khamitov.tests.task;

import khamitov.tests.task.csv.PayloadParser;
import khamitov.tests.task.json.PayloadWriter;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
public class CsvToJsonConverter {
    private final PayloadParser parser;
    private final PayloadWriter writer;

    public CsvToJsonConverter(PayloadParser parser, PayloadWriter writer) {

        this.parser = parser;
        this.writer = writer;
    }

    public void convert(Reader csv, Path json) throws IOException {
        List<Payload> payloads = parser.pars(csv).readAll();
        writer.writePayloadsToFile(payloads, json.toFile());
    }
}
