package khamitov.tests.task;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import khamitov.tests.task.cli.Options;
import khamitov.tests.task.cli.OptionsException;
import khamitov.tests.task.csv.PayloadParser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Options options = new Options();

        try {
            options.resolve(args);
        } catch (OptionsException e) {
            System.out.println("Error: " + e.getMessage());
            e.printHelp();

            System.exit(1);
        }

        String csv = "123,Jack,Daniels,true\n45,Johnny,Walker,false\n6,John,Jameson,true";

        PayloadParser parser = new PayloadParser();
        MappingIterator<Payload> it = parser.pars(csv);

        while (it.hasNextValue()) {
            System.out.println(it.nextValue().toString());
        }
    }
}
