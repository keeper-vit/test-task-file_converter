package khamitov.tests.task.cli;

import com.sun.istack.internal.Nullable;
import org.apache.commons.cli.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
public class Options {
    private final CommandLineParser parser;
    private final org.apache.commons.cli.Options options;

    private Path inputFile;
    private Path outputFile;

    @Nullable
    public Path getInputFile() {
        return inputFile;
    }

    @Nullable
    public Path getOutputFile() {
        return outputFile;
    }

    public Options() {
        parser = new DefaultParser();
        options = new org.apache.commons.cli.Options();
        options.addOption(Option.builder().longOpt("in").hasArg(true).argName("INPUT-FILE")
                .desc("REQUIRED. Absolute or relative path to input CSV file.").build());
        options.addOption(Option.builder().longOpt("out").hasArg(true).argName("OUTPUT-FILE")
                .desc("Absolute or relative path to output JSON file.").build());
    }

    public void resolve(String[] args) throws OptionsException {
        CommandLine line;

        try {
            line = parser.parse(options, args, false);
        } catch (ParseException e) {
            throw new OptionsException(e.getMessage(), options);
        }

        if (!line.hasOption("in")) {
            throw new OptionsException("Option 'in' is required.", options);
        }

        inputFile = Paths.get(line.getOptionValue("in"));
        outputFile = (line.hasOption("out"))
                ? Paths.get(line.getOptionValue("out"))
                : createJsonPathFromCsvPath(inputFile);
    }

    private Path createJsonPathFromCsvPath(Path inputFile) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.csv");
        Path inputFileName = inputFile.getFileName();
        Path outputFileName = (!matcher.matches(inputFileName))
                ? Paths.get(inputFileName.toString() + ".json")
                : Paths.get(inputFileName.toString().substring(0, inputFileName.toString().lastIndexOf('.')) + ".json");

        Path parentPath = inputFile.getParent();

        return (parentPath == null) ? outputFileName : parentPath.resolve(outputFileName);
    }
}
