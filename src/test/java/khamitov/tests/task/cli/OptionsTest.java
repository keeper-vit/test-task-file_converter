package khamitov.tests.task.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Vitaliy Kh. <khamitov.vitaliy@filmon.com> on 10/18/17.
 */
class OptionsTest {

    private Options options;

    static Stream<Arguments> resolveProvider() {
        return Stream.of(
                Arguments.of((new String[]{"--in", "test-input1.csv", "--out", "test-output1.json"}),
                        Paths.get("test-input1.csv"),
                        Paths.get("test-output1.json")),
                Arguments.of((new String[]{"--in", "test2.csv"}),
                        Paths.get("test2.csv"),
                        Paths.get("test2.json")),
                Arguments.of((new String[]{"--in", "test3"}),
                        Paths.get("test3"),
                        Paths.get("test3.json")),
                Arguments.of((new String[]{"--in", "/tmp/files-input/test-input4.csv", "--out", "/tmp/files-output/test-output4.json"}),
                        Paths.get("/tmp/files-input/test-input4.csv"),
                        Paths.get("/tmp/files-output/test-output4.json")),
                Arguments.of((new String[]{"--in", "/tmp/file-converter/test5.csv"}),
                        Paths.get("/tmp/file-converter/test5.csv"),
                        Paths.get("/tmp/file-converter/test5.json")),
                Arguments.of((new String[]{"--in", "/tmp/file-converter/test6"}),
                        Paths.get("/tmp/file-converter/test6"),
                        Paths.get("/tmp/file-converter/test6.json"))
        );
    }

    @Test
    void getInputFile() {
        assertNull(options.getInputFile(), "Input file should be NULL");
    }

    @Test
    void getOutputFile() {
        assertNull(options.getOutputFile(), "Output file should be NULL");
    }

    @ParameterizedTest
    @MethodSource("resolveProvider")
    void resolve(String[] args, Path inputFile, Path outputFile) throws OptionsException {
        options.resolve(args);

        assertEquals(inputFile, options.getInputFile(), "Input file should be resolved to " + inputFile);
        assertEquals(outputFile, options.getOutputFile(), "Output file should be resolved to " + outputFile);
    }

    @Test
    void resolveExceptionMissingRequiredOption() {
        Throwable exception = assertThrows(OptionsException.class, () -> options.resolve(new String[0]));

        assertEquals("Option 'in' is required.", exception.getMessage());
    }

    @Test
    void resolveExceptionWrongOption() {
        Throwable exception = assertThrows(OptionsException.class,
                () -> options.resolve(new String[]{"--in", "input.csv", "--wrong-option"}));

        assertEquals("Unrecognized option: --wrong-option", exception.getMessage());
    }

    @BeforeEach
    void setUp() {
        options = new Options();
    }
}