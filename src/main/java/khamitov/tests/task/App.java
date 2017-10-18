package khamitov.tests.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import khamitov.tests.task.cli.Options;
import khamitov.tests.task.cli.OptionsException;
import khamitov.tests.task.csv.PayloadParser;
import khamitov.tests.task.json.PayloadWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CSV to JSON file converter
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Options options = new Options();

        try {
            options.resolve(args);
        } catch (OptionsException e) {
            System.out.println("Error: " + e.getMessage());
            e.printHelp();
            System.exit(1);

            return;
        }

        Path csvPath = options.getInputFile();
        Path jsonPath = options.getOutputFile();
        PayloadParser parser = new PayloadParser();
        PayloadWriter writer = new PayloadWriter((new ObjectMapper()).writer());
        CsvToJsonConverter converter = new CsvToJsonConverter(parser, writer);

        try(BufferedReader csvReader = Files.newBufferedReader(csvPath, Charset.forName("UTF-8"))) {
            converter.convert(csvReader, jsonPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);

            return;
        }
    }
}
