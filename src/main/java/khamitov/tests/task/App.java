package khamitov.tests.task;

import com.fasterxml.jackson.databind.MappingIterator;
import khamitov.tests.task.cli.Options;
import khamitov.tests.task.cli.OptionsException;
import khamitov.tests.task.csv.PayloadParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Hello world!
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
        }

        PayloadParser parser = new PayloadParser();
        MappingIterator<Payload> it;

        try(BufferedReader reader = Files.newBufferedReader(options.getInputFile(), Charset.forName("UTF-8"))) {
            it = parser.pars(reader);
        } catch (IOException e) {
            e.printStackTrace();

            System.exit(1);

            return;
        }

        try {
            while (it.hasNextValue()) {
                System.out.println(it.nextValue().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
