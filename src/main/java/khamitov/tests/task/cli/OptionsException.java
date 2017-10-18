package khamitov.tests.task.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
public class OptionsException extends Throwable {
    private final Options options;

    public OptionsException(String message, Options options) {
        super(message);
        this.options = options;
    }

    public void printHelp() {
        (new HelpFormatter()).printHelp("File Converter", options);
    }
}
