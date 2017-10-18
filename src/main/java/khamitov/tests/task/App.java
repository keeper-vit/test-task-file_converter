package khamitov.tests.task;

import khamitov.tests.task.cli.Options;
import khamitov.tests.task.cli.OptionsException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Options options = new Options();

        try {
            options.resolve(args);
        } catch (OptionsException e) {
            System.out.println("Error: " + e.getMessage());
            e.printHelp();

            System.exit(1);
        }
    }
}
