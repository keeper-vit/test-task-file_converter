package khamitov.tests.task.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import khamitov.tests.task.Payload;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
public class PayloadParser {

    private final CsvMapper mapper;
    private final CsvSchema schema;

    public PayloadParser() {
        mapper = new CsvMapper();
        schema = mapper.schemaFor(Payload.class);
    }

    public MappingIterator<Payload> pars(Reader csv) throws IOException {
        return mapper.readerFor(Payload.class).with(schema).readValues(csv);
    }
}
